package br.com.iuri.warehouse.service;

import br.com.iuri.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    ProductEntity findById(final UUID id);

    void purchase(final UUID id);
}
