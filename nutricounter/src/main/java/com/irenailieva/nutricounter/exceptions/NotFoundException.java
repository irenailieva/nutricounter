package com.irenailieva.nutricounter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Oops... Seems like we couldn't find what you were looking for.")
public class NotFoundException extends RuntimeException {
}
