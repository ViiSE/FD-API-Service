package ru.fd.api.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerServer;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired private ProductsCreatorProducer productsCrProducer;
//    @Autowired private DepartmentsCreatorProducer departmentsCrProducer;
//    @Autowired private StatusesCreatorProducer statusesCrProducer;
//    @Autowired private AttributesCreatorProducer attributesCrProducer;
//    @Autowired private AttributesGroupsCreatorProducer attributesGrCrProducer;
//    @Autowired private UnitsCreatorProducer unitsCreatorProducer;
    @Autowired private LoggerServer logger;

    @GetMapping("/products")
    @ResponseBody
    public ProductsPojo products(@RequestParam(required = false) List<String> with) {
        try {
            ProductsCreator productsCreator = productsCrProducer.getProductsCreatorDefaultInstance(with);
            ProductsPojo productsPojo = (ProductsPojo) productsCreator.create().formForSend();
            logger.info(ProductsController.class, "Site request products with " + with.toString());
            return productsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsController.class, ex.getMessage() + "\n\t" + ex.getCause());
            return new ProductsPojo(new ArrayList<>());
        }
    }

//    @GetMapping("/products/statuses")
//    @ResponseBody
//    public StatusesPojo statuses() {
//        try {
//            StatusesCreator statusesCreator = statusesCrProducer.getStatusesCreatorDefaultInstance();
//            StatusesPojo statusesPojo = (StatusesPojo) statusesCreator.create().formForSend();
//            logger.info(ProductsController.class, "Site request statuses");
//            return statusesPojo;
//        } catch (ProductsCreatorException ex) {
//            logger.error(ProductsController.class, ex.getMessage() + "\n" + ex.getCause());
//            return new StatusesPojo(new ArrayList<>());
//        }
//    }

//    @GetMapping("/products/attributes")
//    @ResponseBody
//    public AttributesPojo attributes() {
//        try {
//            AttributesCreator attributesCreator = attributesCrProducer.getAttributesCreatorDefaultInstance();
//            AttributesPojo attributesPojo = (AttributesPojo) attributesCreator.create().formForSend();
//            logger.info(ProductsController.class, "Site request attributes");
//            return attributesPojo;
//        } catch (ProductsCreatorException ex) {
//            logger.error(ProductsController.class, ex.getMessage() + "\n" + ex.getCause());
//            return new AttributesPojo(new ArrayList<>());
//        }
//    }

//    @GetMapping("/products/attributes-groups")
//    @ResponseBody
//    public AttributesGroupsPojo attributesGroups() {
//        try {
//            AttributesGroupsCreator attributesGroupsCreator = attributesGrCrProducer.getAttributesGroupsCreatorDefaultInstance();
//            AttributesGroupsPojo attributesGroupsPojo = (AttributesPojo) attributesGroupsCreator.create().formForSend();
//            logger.info(ProductsController.class, "Site request attributes groups");
//            return attributesGroupsPojo;
//        } catch (ProductsCreatorException ex) {
//            logger.error(ProductsController.class, ex.getMessage() + "\n" + ex.getCause());
//            return new AttributesGroupsPojo(new ArrayList<>());
//        }
//    }

//    @GetMapping("/products/units")
//    @ResponseBody
//    public UnitsPojo units() {
//        try {
//            UnitsCreator unitsCreator = unitsCreatorProducer.getUnitsCreatorDefaultInstance();
//            UnitsPojo unitsPojo = (UnitsPojo) unitsCreator.create().formForSend();
//            logger.info(ProductsController.class, "Site request units");
//            return unitsPojo;
//        } catch (ProductsCreatorException ex) {
//            logger.error(ProductsController.class, ex.getMessage() + "\n" + ex.getCause());
//            return new UnitsPojo(new ArrayList<>());
//        }
//    }

//    @GetMapping("/products/categories")
//    @ResponseBody
//    public CategoriesPojo categories() {
//        try {
//            CategoriesCreator categoriesCreator = categoriesCreatorProducer.getCategoriesCreatorDefaultInstance();
//            CategoriesPojo categoriesPojo = (CategoriesPojo) categoriesCreator.create().formForSend();
//            logger.info(ProductsController.class, "Site request categories");
//            return categoriesPojo;
//        } catch (ProductsCreatorException ex) {
//            logger.error(ProductsController.class, ex.getMessage() + "\n" + ex.getCause());
//            return new CategoriesPojo(new ArrayList<>());
//        }
//    }
}
