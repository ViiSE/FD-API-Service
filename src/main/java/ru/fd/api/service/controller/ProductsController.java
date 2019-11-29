package ru.fd.api.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.exception.ProductsCreatorException;
import ru.fd.api.service.log.LoggerServer;
import ru.fd.api.service.producer.ProductsCreatorProducer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired private ProductsCreatorProducer productsCrProducer;
    @Autowired private LoggerServer logger;

    @GetMapping("/product")
    @ResponseBody
    public ProductsPojo products(@RequestParam(required = false) List<String> with) {
        try {
            ProductsCreator productsCreator = productsCrProducer.getProductsCreatorDefaultInstance(with);
            ProductsPojo productsPojo = (ProductsPojo) productsCreator.create().formForSend();
            logger.info(ProductController.class, "Site request products with " + with.toString());
            return productsPojo;
        } catch (ProductsCreatorException ex) {
            logger.error(ProductController.class, ex.getMessage() + "\n" + ex.getCause());
            return new ProductsPojo(new ArrayList<>());
        }
    }

    @GetMapping("/product/")
    @ResponseBody
    public ProductsPojo products(@RequestParam(required = false) List<String> with) {
        try {
            ProductsCreator productsCreator = productsCrProducer.getProductsCreatorDefaultInstance(with);
            ProductsPojo productsPojo = (ProductsPojo) productsCreator.create().formForSend();
            logger.info(ProductController.class, "Site request products with " + with.toString());
            return productsPojo;
        } catch (ProductsCreatorException ex) {
            logger.error(ProductController.class, ex.getMessage() + "\n" + ex.getCause());
            return new ProductsPojo(new ArrayList<>());
        }
    }
}
