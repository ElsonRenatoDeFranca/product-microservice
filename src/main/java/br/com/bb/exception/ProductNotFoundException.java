package br.com.bb.exception;

/**
 * Created by e068635 on 1/15/2019.
 */
public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    public ProductNotFoundException(String message) {
        super(message);
    }

}
