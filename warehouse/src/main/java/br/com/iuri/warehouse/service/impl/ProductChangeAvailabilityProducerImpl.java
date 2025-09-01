package br.com.iuri.warehouse.service.impl;

import br.com.iuri.warehouse.dto.StockStatusMessage;
import br.com.iuri.warehouse.service.IProductChangeAvailabilityProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductChangeAvailabilityProducerImpl implements IProductChangeAvailabilityProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKeyName;

    public ProductChangeAvailabilityProducerImpl(RabbitTemplate rabbitTemplate,
                                                 @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                 String exchangeName,
                                                 @Value("${spring.rabbitmq.routing-key.product-change-availability}")
                                                 String routingKeyName) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyName = routingKeyName;
    }

    @Override
    public void notifyStatusChange(StockStatusMessage message) {
        log.info("Enviou a mensagem: {}", message.toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }
}
