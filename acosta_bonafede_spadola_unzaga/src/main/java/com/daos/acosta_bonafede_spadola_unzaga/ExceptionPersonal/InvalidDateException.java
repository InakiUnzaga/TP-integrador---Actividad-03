package com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }
}
