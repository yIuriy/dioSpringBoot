package br.com.iuri.warehouse.dto;

import br.com.iuri.warehouse.entity.StockStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record StockStatusMessage(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("status")
        StockStatus status) {
}
