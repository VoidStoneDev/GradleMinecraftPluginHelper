package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

import java.util.Map;

public class BukkitPluginPermission {
    public String name;
    public String description;
    public String _default;
    public Map<String, Boolean> children;
    public void children(Map<String, Boolean> children) {
        this.children = children;
    }
}
