package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductStatusPojo;

@Component("productStatusDefault")
@Scope("prototype")
public class ProductStatusDefaultImpl implements Status {

    private final String departmentId;
    private final String statusId;

    public ProductStatusDefaultImpl(String departmentId, String statusId) {
        this.departmentId = departmentId;
        this.statusId = statusId;
    }

    @Override
    public Object formForSend() {
        return new ProductStatusPojo(departmentId, statusId);
    }
}
