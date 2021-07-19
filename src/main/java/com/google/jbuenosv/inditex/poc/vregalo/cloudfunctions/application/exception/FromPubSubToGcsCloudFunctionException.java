package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception;

/**
 * Created by jbuenosv@google.com
 */
public class FromPubSubToGcsCloudFunctionException extends RuntimeException {

    public FromPubSubToGcsCloudFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FromPubSubToGcsCloudFunctionException(String message) {
        super(message);
    }

    public FromPubSubToGcsCloudFunctionException(Throwable cause) {
        super(cause);
    }

}