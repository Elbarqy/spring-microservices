package com.elbarqy.appstore.product.service.core.errorHandling.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    public final Date timestamp;
    private final String message;
}
