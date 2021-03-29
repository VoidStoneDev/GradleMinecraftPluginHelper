package nl.voidgroup.gradle.plugin.opengmph.data.spigot;

import nl.voidgroup.gradle.plugin.opengmph.annotation.Repository;
import nl.voidgroup.gradle.plugin.opengmph.annotation.ServerAPI;
import nl.voidgroup.gradle.plugin.opengmph.annotation.YMLGen;
import nl.voidgroup.gradle.plugin.opengmph.data.bukkit.BukkitYMLGenHandler;
import nl.voidgroup.gradle.plugin.opengmph.data.minecraft.MinecraftServer;
import nl.voidgroup.gradle.plugin.opengmph.data.minecraft.MinecraftVersion;

import java.util.HashMap;
import java.util.Map;
@ServerAPI(group = "org.spigotmc", id = "spigot-api")
@Repository(name = "spigot-repo", url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
@YMLGen(handler = BukkitYMLGenHandler.class)
public class SpigotServer extends MinecraftServer {

    @Override
    public Map<MinecraftVersion, String> getAPIVersions() {
        return new HashMap<MinecraftVersion, String>() {{
            put(MinecraftVersion.V1_16, "1.16.5-R0.1-SNAPSHOT");
        }};
    }
}
