package br.com.iuri.storefront.service.impl;

import br.com.iuri.storefront.dto.ProductInfoDTO;
import br.com.iuri.storefront.entity.ProductEntity;
import br.com.iuri.storefront.repository.ProductRepository;
import br.com.iuri.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    @Override
    public ProductEntity save(ProductEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void changeActivated(UUID id, boolean active) {

    }

    @Override
    public List<ProductEntity> findAllActive() {
        return List.of();
    }

    @Override
    public ProductInfoDTO findInfo(UUID id) {
        return null;
    }

    @Override
    public void purchase(UUID id) {

    }
}
