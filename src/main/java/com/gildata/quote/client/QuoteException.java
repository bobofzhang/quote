package com.gildata.quote.client;

public class QuoteException extends Exception {

	private static final long serialVersionUID = -2216432100890626592L;

    /**
     * Creates a new instance.
     */
    public QuoteException() { }

    /**
     * Creates a new instance.
     */
    public QuoteException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance.
     */
    public QuoteException(String message) {
        super(message);
    }

    /**
     * Creates a new instance.
     */
    public QuoteException(Throwable cause) {
        super(cause);
    }

}
