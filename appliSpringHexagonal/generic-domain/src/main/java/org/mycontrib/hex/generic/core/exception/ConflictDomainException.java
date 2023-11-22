package org.mycontrib.hex.generic.core.exception;

public class ConflictDomainException extends RuntimeException {

	public ConflictDomainException() {
		this("conflict");
	}

	public ConflictDomainException(String message) {
		super(message);
	}

	public ConflictDomainException(Throwable cause) {
		super(cause);
	}

	public ConflictDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
