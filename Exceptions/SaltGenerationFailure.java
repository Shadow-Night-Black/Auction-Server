package Exceptions;

/**
 * Created by mark on 12/05/15.
 */
public class SaltGenerationFailure extends RuntimeException {
    public SaltGenerationFailure(String s) {
        super(s);
    }
}
