package org.mycontrib.hex.generic.domain.exception;

public class GenericDomainException extends RuntimeException {

	public GenericDomainException() {
		this("error");
	}

	public GenericDomainException(String message) {
		super(message);
	}

	public GenericDomainException(Throwable cause) {
		super(cause);
	}

	public GenericDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
