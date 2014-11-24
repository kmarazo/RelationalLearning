package exception;

/**
 * An exception type which is used in cases when some operation is
 * unsupported for a particular class instance.
 */
public class UnsupportedException extends RuntimeException {

    /**
     * Creates an exception for problems related to unsupported operations.
     * @param message A description of the action that is unsupported.
     * @param innerException An internal exception wrapped by this.
     */
    public UnsupportedException(String message, Exception innerException) {
        super(message, innerException);
    }

    /**
     * Creates an exception for problems related to unsupported operations.
     * @param message A description of the action that is unsupported.
     */
    public UnsupportedException(String message) {
        super(message);
    }
}
