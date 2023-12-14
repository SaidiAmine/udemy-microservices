package com.eazybytes.accounts.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
