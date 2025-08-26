package ku.shop;

public class InsufficientException extends RuntimeException {
    public InsufficientException(String productName, int current, int requested) {
        super("Insufficient stock for " + productName + " have " + current + " but requested " + requested);
    }
}
