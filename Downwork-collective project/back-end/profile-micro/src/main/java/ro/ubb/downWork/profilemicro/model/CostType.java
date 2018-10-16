package ro.ubb.downWork.profilemicro.model;

public enum CostType {
    HOURLY("HOURLY"),
    SESSION("SESSION"),
    VOLUNTEERING("VOLUNTEERING");

    private final String name;

    CostType(String s) {
        name = s;
    }
}
