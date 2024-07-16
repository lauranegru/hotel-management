package hotel_management.shared;

import java.util.Objects;

public class ServiceException extends RuntimeException {

    private final String type;
    private final String message;

    public ServiceException(String type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof ServiceException that)
               && Objects.equals(type, that.type)
               && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, message);
    }

    @Override
    public String toString() {
        return new StringBuilder("ServiceException{")
            .append("type='").append(type).append('\'')
            .append(", message='").append(message).append('\'')
            .append('}')
            .toString();
    }

}
