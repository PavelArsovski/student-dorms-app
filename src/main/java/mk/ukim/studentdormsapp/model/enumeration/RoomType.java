package mk.ukim.studentdormsapp.model.enumeration;

public enum RoomType {
    SINGLE("Single"),
    SINGLE_WITH_KITCHEN("Single with Kitchen"),
    SINGLE_WITH_BATHROOM("Single with Bathroom"),

    DOUBLE("Double"),
    DOUBLE_WITH_KITCHEN("Double with Kitchen"),
    DOUBLE_WITH_BATHROOM("Double with Bathroom");

    public final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}