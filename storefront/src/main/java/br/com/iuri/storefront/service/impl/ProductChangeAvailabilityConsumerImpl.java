package br.com.iuri.storefront.service.impl;

import br.com.iuri.storefront.dto.StockStatusMessage;
import br.com.iuri.storefront.service.IProductChangeAvailabilityConsumer;
import br.com.iuri.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductChangeAvailabilityConsumerImpl implements IProductChangeAvailabilityConsumer {

    private final IProductService service;

    @RabbitListener(queues = "${spring.queue.product-change-availability}")
    @Override
    public void receive(StockStatusMessage message) {
        service.changeActivated(message.id(), message.active());
    }
}
