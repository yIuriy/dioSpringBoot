package br.com.iuri.storefront.service.impl;

import br.com.iuri.storefront.dto.ProductDetailDTO;
import br.com.iuri.storefront.dto.ProductInfoDTO;
import br.com.iuri.storefront.entity.ProductEntity;
import br.com.iuri.storefront.exceptions.ProductNotFoundException;
import br.com.iuri.storefront.mapper.IProductMapper;
import br.com.iuri.storefront.repository.ProductRepository;
import br.com.iuri.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final RestClient warehouseClient;
    private final IProductMapper mapper;

    @Override
    public ProductEntity save(final ProductEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void changeActivated(final UUID id, final boolean active) {
        var entity = findById(id);
        entity.setActive(active);
        repository.save(entity);
    }

    @Override
    public List<ProductEntity> findAllActive() {
        return repository.findByActiveTrueOrderByNameAsc();
    }

    @Override
    public ProductInfoDTO findInfo(final UUID id) {
        var entity = findById(id);
        var price = requestCurrentAmount(id);
        return mapper.toDTO(entity, price);
    }

    @Override
    public void purchase(UUID id) {
        purchaseWarehouse(id);
    }

    private ProductEntity findById(final UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
    }

    private BigDecimal requestCurrentAmount(final UUID id) {
        var dto = warehouseClient
                .get().uri("/products/" + id)
                .retrieve()
                .body(ProductDetailDTO.class);
        return dto.price();
    }

    private void purchaseWarehouse(final UUID id) {
        var path = String.format("/products/%s/purchase", id);
        warehouseClient.post()
                .uri(path)
                .retrieve()
                .toBodilessEntity();
    }
}
