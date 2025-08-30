package br.com.iuri.warehouse.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ProductSaveResponse(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name) {
}
