package com.osp.codechallenge.configuration.exceptions;

public class RuntimeMongoException extends RuntimeException {


    public RuntimeMongoException(String message, Throwable e) {
        super(message, e);
    }
}