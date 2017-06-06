package org.constantgatherer.error;

import java.util.List;

/**
 * Demo Project documentation not available
 * User: ggomes
 */
public class GathererException extends Exception {

    List<Error> errors;

    public GathererException(Exception exception){
        super(exception);
    }

    public GathererException(String message){
        super(message);
    }

    public GathererException(List<Error> errors, String errorsJSON){
        super(errorsJSON);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
