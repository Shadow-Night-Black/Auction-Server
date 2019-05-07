package Exceptions;

/**
 * Created by mark on 12/05/15.
 */
public class InvalidIDException extends Exception{
    private int id;
    public InvalidIDException(String s, int id) {
        super(s);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
