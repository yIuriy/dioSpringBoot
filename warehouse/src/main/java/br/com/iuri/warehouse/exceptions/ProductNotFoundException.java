package br.com.iuri.warehouse.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super(
                "Product with ID" + id + " not found."
        );
    }
}
