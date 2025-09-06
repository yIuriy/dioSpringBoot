package br.com.iuri.storefront.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super(
                "Product with id " + id + " not found."
        );
    }
}
