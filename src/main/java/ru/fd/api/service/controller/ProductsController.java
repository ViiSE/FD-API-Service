package ru.fd.api.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.creator.*;
import ru.fd.api.service.data.*;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.producer.creator.*;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.*;
import ru.fd.api.service.producer.repository.processor.ProductsRepositoryProcessorsProducer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired private ProductsCreatorProducer productsCrProducer;
    @Autowired private ProductsRepositoryProcessorsProducer productsRepoProsProducer;
    @Autowired private ProductsRepositoryProducer productsRepoProducer;
    @Autowired private ProductProducer productProducer;
    @Autowired private StatusesCreatorProducer statusesCrProducer;
    @Autowired private StatusesRepositoryProducer statusesRepositoryProducer;
    @Autowired private AttributesCreatorProducer attrCrProducer;
    @Autowired private AttributesRepositoryProducer attrRepoProducer;
    @Autowired private AttributeGroupsCreatorProducer attrGrCrProducer;
    @Autowired private AttributeGroupsRepositoryProducer attrGrRepoProducer;
    @Autowired private UnitsCreatorProducer unitsCrProducer;
    @Autowired private UnitsRepositoryProducer unitsRepoProducer;
    @Autowired private CategoriesCreatorProducer categoriesCrProducer;
    @Autowired private CategoriesRepositoryProducer categoriesRepoProducer;
//    @Autowired private BalancesCreatorProducer balancesCrProducer;
//    @Autowired private BalancesRepositoryProducer balancesRepoProducer;

    @Autowired private LoggerService logger;

    @GetMapping("/products")
    @ResponseBody
    public ProductsPojo products(@RequestParam(required = false) List<String> with) {
        try {
            ProductsCreator productsCreator = productsCrProducer.getProductsCreatorDefaultInstance(
                    productsRepoProsProducer.getProductsRepositoryProcessorsSingletonImpl(
                            productsRepoProducer, productProducer),
                    with);
            ProductsPojo productsPojo = (ProductsPojo) productsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request products with " + with.toString());
            return productsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new ProductsPojo(new ArrayList<>());
        }
    }

    @GetMapping("/products/statuses")
    @ResponseBody
    public StatusesPojo statuses() {
        try {
            StatusesCreator statusesCreator = statusesCrProducer.getStatusesCreatorDefaultInstance(
                    statusesRepositoryProducer.getStatusesRepositoryDefaultInstance());
            StatusesPojo statusesPojo = (StatusesPojo) statusesCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request statuses");
            return statusesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new StatusesPojo(new ArrayList<>());
        }
    }

    @GetMapping("/products/attributes")
    @ResponseBody
    public AttributesPojo attributes() {
        try {
            AttributesCreator attributesCreator = attrCrProducer.getAttributesCreatorDefaultInstance(
                    attrRepoProducer.getAttributesRepositoryDefaultInstance());
            AttributesPojo attributesPojo = (AttributesPojo) attributesCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request attributes");
            return attributesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new AttributesPojo(new ArrayList<>());
        }
    }

    @GetMapping("/products/attribute-groups")
    @ResponseBody
    public AttributeGroupsPojo attributesGroups() {
        try {
            AttributeGroupsCreator attributesGroupsCreator = attrGrCrProducer.getAttributeGroupsCreatorDefaultInstance(
                    attrGrRepoProducer.getAttributeGroupsRepositoryDefaultInstance());
            AttributeGroupsPojo attributesGroupsPojo = (AttributeGroupsPojo) attributesGroupsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request attribute groups");
            return attributesGroupsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new AttributeGroupsPojo(new ArrayList<>());
        }
    }

    @GetMapping("/products/units")
    @ResponseBody
    public UnitsPojo units() {
        try {
            UnitsCreator unitsCreator = unitsCrProducer.getUnitsCreatorDefaultInstance(
                    unitsRepoProducer.getUnitsRepositoryDefaultInstance());
            UnitsPojo unitsPojo = (UnitsPojo) unitsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request units");
            return unitsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new UnitsPojo(new ArrayList<>());
        }
    }

    @GetMapping("/products/categories")
    @ResponseBody
    public CategoriesPojo categories() {
        try {
            CategoriesCreator categoriesCreator = categoriesCrProducer.getCategoriesCreatorDefaultInstance(
                    categoriesRepoProducer.getCategoriesRepositoryDefaultInstance());
            CategoriesPojo categoriesPojo = (CategoriesPojo) categoriesCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request categories");
            return categoriesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new CategoriesPojo(new ArrayList<>());
        }
    }

//    @GetMapping("/products/balances")
//    @ResponseBody
//    public BalancesPojo balances() {
//        try {
//            BalancesCreator balancesCreator = balancesCrProducer.getBalancesCreatorDefaultInstance(
//                    balancesRepoProducer.getBalancesRepositoryDefaultInstance());
//            BalancesPojo balancesPojo = (BalancesPojo) balancesCreator.create().formForSend();
//            logger.info(ProductsController.class, "Site request balances");
//            return balancesPojo;
//        } catch (CreatorException ex) {
//            logger.error(ProductsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
//            return new BalancesPojo(new ArrayList<>());
//        }
//    }
}
