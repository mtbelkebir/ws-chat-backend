package dz.mtbelkebir.wschat.api.exception;

public class ChannelNotFoundException extends Exception{
    public ChannelNotFoundException() {
        super("The requested channel has not been found");
    }

    public ChannelNotFoundException(String message) {
        super(message);
    }

    public ChannelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
