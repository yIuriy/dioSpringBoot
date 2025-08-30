package br.com.iuri.storefront.dto;

import java.util.UUID;

public record StockStatusMessage(
        UUID id,
        String status
) {
    public boolean active() {
        return status.equals("AVAILABLE");
    }
}
