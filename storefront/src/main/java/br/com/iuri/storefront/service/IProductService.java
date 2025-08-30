package br.com.iuri.storefront.service;

import br.com.iuri.storefront.dto.ProductInfoDTO;
import br.com.iuri.storefront.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    void changeActivated(final UUID id, final boolean active);

    List<ProductEntity> findAllActive();

    ProductInfoDTO findInfo(final UUID id);

    void purchase(final UUID id);
}
