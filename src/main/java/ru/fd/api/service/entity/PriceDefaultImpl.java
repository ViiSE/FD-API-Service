package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.PricePojo;

@Component("priceDefault")
@Scope("prototype")
public class PriceDefaultImpl implements Price {

    private final String departmentId;
    private final float value;

    public PriceDefaultImpl(String departmentId, float value) {
        this.departmentId = departmentId;
        this.value = value;
    }

    @Override
    public Object formForSend() {
        return new PricePojo(departmentId, value);
    }
}
