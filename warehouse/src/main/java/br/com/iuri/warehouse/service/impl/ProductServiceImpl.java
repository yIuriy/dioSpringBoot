package br.com.iuri.warehouse.service.impl;

import br.com.iuri.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.iuri.warehouse.entity.ProductEntity;
import br.com.iuri.warehouse.mapper.IProductMapper;
import br.com.iuri.warehouse.repository.ProductRepository;
import br.com.iuri.warehouse.service.IProductService;
import br.com.iuri.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final IStockService stockService;
    private RestClient storefrontClient;
    private final IProductMapper mapper;


    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStorefront(dto);
        return entity;
    }

    @Override
    public ProductEntity findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void purchase(UUID id) {
        var entity = findById(id);
        var stock = entity.decStock();
        repository.save(entity);
        if (stock.isUnavailable()) {
            stockService.changeStatus(entity.getId(), stock.getStatus());
        }
    }

    private void saveStorefront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/product")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSaveDTO.class);
    }
}
