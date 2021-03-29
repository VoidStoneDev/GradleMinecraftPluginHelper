package nl.voidgroup.gradle.plugin.opengmph.exception;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String msg) {
        super(msg);
    }
    public InvalidArgumentException(String msg, Throwable parent) {
        super(msg, parent);
    }
}
