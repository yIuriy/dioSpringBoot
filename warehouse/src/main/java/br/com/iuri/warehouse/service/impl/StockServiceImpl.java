package br.com.iuri.warehouse.service.impl;

import br.com.iuri.warehouse.dto.StockStatusMessage;
import br.com.iuri.warehouse.entity.StockEntity;
import br.com.iuri.warehouse.entity.StockStatus;
import br.com.iuri.warehouse.exceptions.ProductNotFoundException;
import br.com.iuri.warehouse.exceptions.StockNotFoundException;
import br.com.iuri.warehouse.repository.StockRepository;
import br.com.iuri.warehouse.service.IProductChangeAvailabilityProducer;
import br.com.iuri.warehouse.service.IProductQueryService;
import br.com.iuri.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.iuri.warehouse.entity.StockStatus.AVAILABLE;
import static br.com.iuri.warehouse.entity.StockStatus.UNAVAILABLE;

@Slf4j
@AllArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository repository;
    private final IProductQueryService productQueryService;
    private final IProductChangeAvailabilityProducer producer;

    @Override
    public StockEntity save(StockEntity entity) {
        entity.setProduct(productQueryService.findById(entity.getProduct().getId()));
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
        log.info(String.valueOf(id));
        log.info(String.valueOf(status));
        var entity = repository.findById(id).orElseThrow(() ->
                new StockNotFoundException(id)
        );
        entity.setStatus(status);
        log.info(String.valueOf(entity.getStatus()));
        repository.save(entity);
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(), status));
    }
}
