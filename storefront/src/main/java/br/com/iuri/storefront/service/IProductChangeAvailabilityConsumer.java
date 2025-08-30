package br.com.iuri.storefront.service;

import br.com.iuri.storefront.dto.StockStatusMessage;

public interface IProductChangeAvailabilityConsumer {

    void receive(final StockStatusMessage message);
}
