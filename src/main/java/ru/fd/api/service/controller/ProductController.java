package ru.fd.api.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsPojo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/product")
    @ResponseBody
    public ProductsPojo products(@RequestParam(required = false) List<String> with) {
        ProductPojo product1 = new ProductPojo();
        product1.setName("hello");
        ProductPojo product2 = new ProductPojo();
        product2.setName("hi");

        return new ProductsPojo(new ArrayList<>() {{add(product1); add(product2);}});
    }

}