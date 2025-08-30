package br.com.iuri.warehouse.service.impl;

import br.com.iuri.warehouse.dto.StockStatusMessage;
import br.com.iuri.warehouse.entity.StockEntity;
import br.com.iuri.warehouse.entity.StockStatus;
import br.com.iuri.warehouse.repository.StockRepository;
import br.com.iuri.warehouse.service.IProductChangeAvailabilityProducer;
import br.com.iuri.warehouse.service.IProductService;
import br.com.iuri.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.iuri.warehouse.entity.StockStatus.AVAILABLE;
import static br.com.iuri.warehouse.entity.StockStatus.UNAVAILABLE;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {

    private final StockRepository repository;
    private final IProductService productService;
    private final IProductChangeAvailabilityProducer producer;

    @Override
    public StockEntity save(StockEntity entity) {
        entity.setProduct(productService.findById(entity.getProduct().getId()));
        return repository.save(entity);
    }

    @Override
    public void release(UUID id) {
        changeStatus(id, AVAILABLE);
    }

    @Override
    public void inactive(UUID id) {
        changeStatus(id, UNAVAILABLE);
    }


    @Override
    public void changeStatus(UUID id, StockStatus status) {
        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(status);
        repository.save(entity);
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(), status));
    }
}
