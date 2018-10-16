package ro.ubb.downWork.profilemicro.model;

public enum JobOccurrence {
    MONTHLY("MONTHLY"),
    WEEKLY("WEEKLY"),
    ONCE("ONCE");

    private final String name;

    JobOccurrence(String s) {
        name = s;
    }
}
