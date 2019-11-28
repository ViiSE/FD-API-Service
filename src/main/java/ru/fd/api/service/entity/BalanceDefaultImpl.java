package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.BalancePojo;

@Component("balanceDefault")
@Scope("prototype")
public class BalanceDefaultImpl implements Balance {

    private final String departmentId;
    private final int quantity;

    public BalanceDefaultImpl(String departmentId, int quantity) {
        this.departmentId = departmentId;
        this.quantity = quantity;
    }

    @Override
    public Object formForSend() {
        return new BalancePojo(departmentId, quantity);
    }
}
