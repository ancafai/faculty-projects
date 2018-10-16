package ro.ubb.downWork.profilemicro.dto;

/**
 * Created by langchristian96 on 1/19/2018.
 */

public class ReviewDto {
    private String message;
    private boolean positive;
    private String reviewer;

    public ReviewDto() {
    }

    public ReviewDto(String message, boolean positive, String reviewer) {

        this.message = message;
        this.positive = positive;
        this.reviewer = reviewer;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "message='" + message + '\'' +
                ", positive=" + positive +
                ", reviewer='" + reviewer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDto)) return false;

        ReviewDto reviewDto = (ReviewDto) o;

        if (isPositive() != reviewDto.isPositive()) return false;
        if (getMessage() != null ? !getMessage().equals(reviewDto.getMessage()) : reviewDto.getMessage() != null)
            return false;
        return getReviewer() != null ? getReviewer().equals(reviewDto.getReviewer()) : reviewDto.getReviewer() == null;
    }

    @Override
    public int hashCode() {
        int result = getMessage() != null ? getMessage().hashCode() : 0;
        result = 31 * result + (isPositive() ? 1 : 0);
        result = 31 * result + (getReviewer() != null ? getReviewer().hashCode() : 0);
        return result;
    }

    public boolean isPositive() {

        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
