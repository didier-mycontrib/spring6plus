package org.mycontrib.hex.generic.domain.exception;

public class NotFoundDomainException extends RuntimeException {

	public NotFoundDomainException() {
		this("not found");
	}

	public NotFoundDomainException(String message) {
		super(message);
	}

	public NotFoundDomainException(Throwable cause) {
		super(cause);
	}

	public NotFoundDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
