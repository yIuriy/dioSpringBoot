package br.com.iuri.warehouse.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.util.UUID;

public record StockSaveRequest(
        @JsonProperty("amount")
        Long amount,
        @JsonProperty("boughtPrice")
        BigDecimal boughtPrice,
        @JsonProperty("soldPrice")
        BigDecimal soldPrice,
        @JsonProperty("productId")
        UUID productId) {

}
