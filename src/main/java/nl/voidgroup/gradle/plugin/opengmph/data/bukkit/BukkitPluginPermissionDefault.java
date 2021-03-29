package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

public enum BukkitPluginPermissionDefault {
    TRUE("true"),
    FALSE("false"),
    OP("op"),
    NOT_OP("not op");

    private String string;

    BukkitPluginPermissionDefault(String string) {
        this.string = string;
    }

    public static BukkitPluginPermissionDefault getByName(String name) {
        switch (name.toLowerCase()) {
            case "true":
                return TRUE;
            case "false":
                return FALSE;
            case "op":
                return OP;
            case "not op":
                return NOT_OP;
            default:
                return null;
        }
    }

}
