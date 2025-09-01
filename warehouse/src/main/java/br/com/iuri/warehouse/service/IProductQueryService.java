package br.com.iuri.warehouse.service;

import br.com.iuri.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductQueryService {
    ProductEntity findById(final UUID id);
}
