package com.shearf.cloud.apps.user.center.common.exception;

import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.error.Error;

/**
 * @author xiahaihu2009@gmai.com
 */
public class ServiceException extends RuntimeException {

    private int code;

    private String messages;


    private SerializableObject object;

    public ServiceException() {
        this(Response.Status.FAILURE.getCode(), Response.Status.FAILURE.getMessage(), (SerializableObject) null);
    }

    public ServiceException(int code, String messages, SerializableObject object) {
        super(messages);
        this.code = code;
        this.messages = messages;
        this.object = object;
    }

    public ServiceException(Error error) {
        this(error.getCode(), error.getMessage(), (SerializableObject) null);
    }

    public ServiceException(String message) {
        this(Response.Status.FAILURE.getCode(), message, (SerializableObject) null);
    }

    public ServiceException(Error error, SerializableObject object) {
        this(error.getCode(), error.getMessage(), object);
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.messages = message;
    }

    public ServiceException(String messages, Throwable cause) {
        super(messages, cause);
    }

    public int getCode() {
        return code;
    }

    public String getMessages() {
        return messages;
    }

    public SerializableObject getObject() {
        return object;
    }
}
