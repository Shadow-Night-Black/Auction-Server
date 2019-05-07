package Exceptions;

import Infomation.Data;

/**
 * Created by mark on 15/05/15.
 */
public class DuplicateDataException extends Exception {
    private Data data;

    public DuplicateDataException(String message, Data data) {
        super(message);
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
