package Exceptions;

import Infomation.Data;

/**
 * Created by mark on 22/03/15.
 */
public class DuplicateIDException extends Exception {
    private Data identifiable;

    public DuplicateIDException(String s, Data identifiable) {
        super(s);
        this.identifiable = identifiable;
    }


    public Data getIdentifiable() {
        return identifiable;
    }
}
