package br.com.iuri.warehouse.exceptions;

import java.util.UUID;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(UUID id) {
        super(
                "Stock with ID" + id + " not found."
        );
    }
}
