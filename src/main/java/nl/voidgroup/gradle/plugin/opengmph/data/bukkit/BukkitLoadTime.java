package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

public enum BukkitLoadTime {
    STARTUP("STARTUP"),
    POSTWORLD("POSTWORLD");

    private String string;

    BukkitLoadTime(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static BukkitLoadTime getByName(String name) {
        switch (name.toLowerCase()) {
            case "startup":
                return STARTUP;
            case "postworld":
                return POSTWORLD;
            default:
                return null;
        }
    }

}
