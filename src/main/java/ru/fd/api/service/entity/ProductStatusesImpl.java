package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductStatusPojo;
import ru.fd.api.service.data.ProductStatusesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("productStatuses")
@Scope("prototype")
public class ProductStatusesImpl implements Statuses {

    private final List<Status> statuses;

    public ProductStatusesImpl(List<Status> statuses) {
        this.statuses = statuses;
    }

    @Override
    public Object formForSend() {
        List<ProductStatusPojo> statusPojos = statuses.stream()
                .map(status -> (ProductStatusPojo) status.formForSend())
                .collect(Collectors.toList());
        return new ProductStatusesPojo(statusPojos);
    }
}
