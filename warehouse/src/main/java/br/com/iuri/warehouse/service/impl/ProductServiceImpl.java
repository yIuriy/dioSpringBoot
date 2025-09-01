package br.com.iuri.warehouse.service.impl;

import br.com.iuri.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.iuri.warehouse.entity.ProductEntity;
import br.com.iuri.warehouse.mapper.IProductMapper;
import br.com.iuri.warehouse.repository.ProductRepository;
import br.com.iuri.warehouse.service.IProductQueryService;
import br.com.iuri.warehouse.service.IProductService;
import br.com.iuri.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final IProductQueryService productQueryService;
    private final IStockService stockService;
    private RestClient storefrontClient;
    private final IProductMapper mapper;

    @Override
    public void purchase(UUID id) {
        var entity = productQueryService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);
        if (stock.isUnavailable()) {
            stockService.changeStatus(stock.getId(), stock.getStatus());
        }
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStorefront(dto);
        return entity;
    }

    private void saveStorefront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSaveDTO.class);
    }
}
