package br.com.iuri.storefront.service.impl;

import br.com.iuri.storefront.dto.StockStatusMessage;
import br.com.iuri.storefront.service.IProductChangeAvailabilityConsumer;
import br.com.iuri.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Log4j2
public class ProductChangeAvailabilityConsumerImpl implements IProductChangeAvailabilityConsumer {

    private final IProductService service;

    @RabbitListener(queues = "${spring.rabbitmq.queue.product-change-availability}")
    @Override
    public void receive(final StockStatusMessage message) {
        service.changeActivated(message.id(), message.active());
    }
}
