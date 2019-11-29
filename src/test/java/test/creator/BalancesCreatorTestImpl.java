package test.creator;

import ru.fd.api.service.entity.*;

import java.util.ArrayList;

public class BalancesCreatorTestImpl implements BalancesCreator {

    @Override
    public Balances createBalances() {
        Balance balance1 = new BalanceDefaultImpl("dep_1", 10);
        Balance balance2 = new BalanceDefaultImpl("dep_2", 20);
        return new BalancesDefaultImpl(new ArrayList<>() {{ add(balance1); add(balance2); }});
    }
}
