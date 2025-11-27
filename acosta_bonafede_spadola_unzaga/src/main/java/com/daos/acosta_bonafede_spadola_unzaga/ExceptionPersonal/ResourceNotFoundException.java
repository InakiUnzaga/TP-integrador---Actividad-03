package com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
