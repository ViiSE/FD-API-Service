package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fd.api.service.ProductsService;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.ProductsChangedBalancesPojo;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

import java.util.ArrayList;
import java.util.List;

@Api(tags= "Products Controller", description = "Контроллер точек для работы с товарами", authorizations = {@Authorization(value = "Bearer")})
@RestController
public class ProductsController {

    private final ProductsService productsService;
    private final LoggerService logger;

    public ProductsController(
            ProductsService productsService,
            LoggerService logger) {
        this.productsService = productsService;
        this.logger = logger;
    }

    @ApiOperation(value = "Выгружает все товары")
    @GetMapping("/products")
    public ProductsPojo products(
            @ApiParam(value = "При указании данного параметра товары будут выгружаться с указанными зависимостями.\n" +
                    "Доступные зависимости:" +
                    "\n<span style=\"margin-left:2em\"><b>balances</b> - остатки товара," +
                    "\n<span style=\"margin-left:2em\"><b>prices</b> - цены товара,</span>" +
                    "\n<span style=\"margin-left:2em\">[!!!НЕДОСТУПНО!!!] <b>statuses</b> - статусы товара,</span>" +
                    "\n<span style=\"margin-left:2em\">[!!!НЕДОСТУПНО!!!] <b>attributes</b> - атрибуты товара</span>" +
                    "\nНесуществующие зависимости, указанные в запросе, будут проигнорированы.\n___" +
                    "\n<i>Примечание: Выгружаемый товар без зависимости</i> <b>prices</b> <i>может иметь нулевую цену.</i>")
            @RequestParam(required = false) List<String> with) {
        try {
            if(with == null)
                with = new ArrayList<>();
            ProductsCreator productsCreator = productsService.productsCreatorProducer()
                    .getProductsCreatorDefaultInstance(
                            productsService.productsRepositoryProcessorsProducer()
                                    .getProductsRepositoryProcessorsSingletonImpl(),
                            with);
            ProductsPojo productsPojo = (ProductsPojo) productsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request products with " + with.toString());
            return productsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new ProductsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает товары с измененными остатками (ТЕСТОВАЯ ВЕРСИЯ)")
    @GetMapping("/products/balances/changes")
    public ProductsChangedBalancesPojo changedBalances(
            @ApiParam(value = "При указании параметра <code><b>order_id</code></b> метод вернет изменения остатков " +
                    "только тех товаров, которые были указаны в данном заказе. Без этого параметра метод вернет " +
                    "изменения остатков всех товаров." +
                    "\n___" +
                    "\n<i>Примечание. Метод может иметь задержку, начиная от 500 мс.</i>", example = "1")
            @RequestParam(name = "order_id", defaultValue = "-1") long orderId) {
        try {
            ProductsCreator productsCreator = productsService.productsCreatorProducer()
                    .getProductsWithChangedBalancesCreatorInstance(
                            productsService.productsRepositoryProcessorsProducer()
                                    .getProductsChangedBalancesRepositoryProcessorsSingletonImpl(),
                            orderId);

            ProductsChangedBalancesPojo products = (ProductsChangedBalancesPojo) productsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request product balances changes: order_id=" + orderId);
            return products;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + "<CAUSE>: " + ex.getCause());
            return new ProductsChangedBalancesPojo(new ArrayList<>());
        }
    }
}
