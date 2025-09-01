package br.com.iuri.warehouse.service.impl;

import br.com.iuri.warehouse.entity.ProductEntity;
import br.com.iuri.warehouse.repository.ProductRepository;
import br.com.iuri.warehouse.service.IProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductQueryServiceImpl implements IProductQueryService {

    private final ProductRepository repository;

    @Override
    public ProductEntity findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
