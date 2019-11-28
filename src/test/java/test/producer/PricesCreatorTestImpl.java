package test.producer;

import ru.fd.api.service.entity.*;

import java.util.ArrayList;

public class PricesCreatorTestImpl implements PricesCreator {

    @Override
    public Prices createPrices() {
        Price price1 = new PriceDefaultImpl("dep_id_11111", 100.50f);
        Price price2 = new PriceDefaultImpl("dep_id_22222", 160.50f);
        return new PricesDefaultImpl(new ArrayList<>() {{ add(price1); add(price2); }});
    }
}
