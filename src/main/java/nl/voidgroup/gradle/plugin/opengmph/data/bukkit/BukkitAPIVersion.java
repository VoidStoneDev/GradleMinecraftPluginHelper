package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

public enum BukkitAPIVersion {
    V1_13("1.13"),
    V1_14("1.14"),
    V1_15("1.15"),
    V1_16("1.16");

    private String string;

    BukkitAPIVersion(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static BukkitAPIVersion getByName(String name) {
        switch (name.toLowerCase()) {
            case "1.13":
                return V1_13;
            case "1.14":
                return V1_14;
            case "1.15":
                return V1_15;
            case "1.16":
                return V1_16;
            default:
                return null;
        }
    }

}
