/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.exception;

public class ExceptionWithSendMessage extends Exception {

    private final String messageForSend;

    public ExceptionWithSendMessage(String messageForSend, String message) {
        super(message);
        this.messageForSend = messageForSend;
    }

    public ExceptionWithSendMessage(String message, Throwable cause) {
        super(message, cause);
        this.messageForSend = message;
    }

    public String getMessageForSend() {
        return messageForSend;
    }
}
