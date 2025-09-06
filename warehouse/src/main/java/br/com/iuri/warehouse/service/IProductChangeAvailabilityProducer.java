package br.com.iuri.warehouse.service;

import br.com.iuri.warehouse.dto.StockStatusMessage;

public interface IProductChangeAvailabilityProducer {
    void notifyStatusChange(final StockStatusMessage message);
}
