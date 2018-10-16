package ro.ubb.downWork.profilemicro.model;

public enum JobStatus {
    OPEN("OPEN"),
    CLOSED("CLOSED");

    private final String name;

    JobStatus(String s) {
        name = s;
    }
}
