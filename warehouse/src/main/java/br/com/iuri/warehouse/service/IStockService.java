package br.com.iuri.warehouse.service;

import br.com.iuri.warehouse.entity.StockEntity;
import br.com.iuri.warehouse.entity.StockStatus;

import java.util.UUID;

public interface IStockService {

    StockEntity save(final StockEntity entity);

    void release(final UUID id);

    void inactive(final UUID id);

    void changeStatus(final UUID id, final StockStatus status);


}
