package com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
