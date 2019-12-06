package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Price;
import ru.fd.api.service.entity.Prices;

import java.util.List;

public interface PricesProducer {
    Prices getPricesDefaultInstance(List<Price> prices);
}
