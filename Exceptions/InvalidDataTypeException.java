package Exceptions;

import Infomation.Data;
import Infomation.DataType;

/**
 * Created by mark on 15/05/15.
 */
public class InvalidDataTypeException extends RuntimeException {
    private Data data;
    private DataType dataType;

    public InvalidDataTypeException(String message, Data data, DataType dataType) {
        super(message);
        this.data = data;
        this.dataType = dataType;
    }

    public Data getData() {
        return data;
    }

    public DataType getDataType() {
        return dataType;
    }
}
