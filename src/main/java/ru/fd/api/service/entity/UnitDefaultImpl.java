package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.UnitPojo;

@Component("unitDefault")
@Scope("prototype")
public class UnitDefaultImpl implements Unit {

    private final String unitId;
    private final String name;

    public UnitDefaultImpl(String unitId, String name) {
        this.unitId = unitId;
        this.name = name;
    }

    @Override
    public Object formForSend() {
        return new UnitPojo(unitId, name);
    }
}
