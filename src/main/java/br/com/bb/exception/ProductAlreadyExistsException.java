package br.com.bb.exception;

public class ProductAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 7718928512143293558L;

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

}
