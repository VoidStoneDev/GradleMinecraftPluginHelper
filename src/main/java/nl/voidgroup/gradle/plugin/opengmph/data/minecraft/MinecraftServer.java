package nl.voidgroup.gradle.plugin.opengmph.data.minecraft;

import nl.voidgroup.gradle.plugin.opengmph.data.spigot.SpigotServer;

import java.util.Map;

public abstract class MinecraftServer {
    public static MinecraftServer getByName(String name) {
        switch (name.toLowerCase()) {
            case "spigot":
            case "spigotmc":
                return new SpigotServer();
            default:
                return null;
        }
    }

    public abstract Map<MinecraftVersion, String> getAPIVersions();

}
