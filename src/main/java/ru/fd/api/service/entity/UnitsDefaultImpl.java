package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.UnitPojo;
import ru.fd.api.service.data.UnitsPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("unitsDefault")
@Scope("prototype")
public class UnitsDefaultImpl implements Units {

    private final List<Unit> units;

    public UnitsDefaultImpl(List<Unit> units) {
        this.units = units;
    }

    @Override
    public Object formForSend() {
        List<UnitPojo> unitPojos = units.stream()
                .map(unit -> (UnitPojo) unit.formForSend())
                .collect(Collectors.toList());
        return new UnitsPojo(unitPojos);
    }
}
