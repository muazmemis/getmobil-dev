package dev.muazmemis.getmobil_dev.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super("Insufficient stock: " + message);
    }
}
