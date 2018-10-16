package ro.ubb.downWork.profilemicro.dto;

/**
 * Created by langchristian96 on 1/11/2018.
 */
public class NotificationDto {
    private String message;
    private boolean read;

    public NotificationDto() {
    }

    public NotificationDto(String message, boolean read) {

        this.message = message;
        this.read = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationDto)) return false;

        NotificationDto that = (NotificationDto) o;

        if (getRead() != that.getRead()) return false;
        return getMessage() != null ? getMessage().equals(that.getMessage()) : that.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getMessage() != null ? getMessage().hashCode() : 0;
        result = 31 * result + (getRead() ? 1 : 0);
        return result;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "message='" + message + '\'' +
                ", read=" + read +
                '}';
    }
}
