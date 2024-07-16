package hotel_management.shared;

public class ServiceExceptionGenerator {

    private String type;
    private String message;

    private ServiceExceptionGenerator() {
    }

    public static ServiceExceptionGenerator serviceException() {
        return new ServiceExceptionGenerator();
    }

    public ServiceExceptionGenerator type(String type) {
        this.type = type;
        return this;
    }

    public ServiceExceptionGenerator message(String message) {
        this.message = message;
        return this;
    }

    public ServiceException build() {
        return new ServiceException(type, message);
    }

}
