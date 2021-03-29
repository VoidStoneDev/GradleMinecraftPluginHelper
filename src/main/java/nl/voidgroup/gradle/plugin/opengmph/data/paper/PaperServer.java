package nl.voidgroup.gradle.plugin.opengmph.data.paper;

import nl.voidgroup.gradle.plugin.opengmph.annotation.Repository;
import nl.voidgroup.gradle.plugin.opengmph.annotation.ServerAPI;
import nl.voidgroup.gradle.plugin.opengmph.annotation.YMLGen;
import nl.voidgroup.gradle.plugin.opengmph.data.bukkit.BukkitYMLGenHandler;
import nl.voidgroup.gradle.plugin.opengmph.data.minecraft.MinecraftServer;
import nl.voidgroup.gradle.plugin.opengmph.data.minecraft.MinecraftVersion;

import java.util.HashMap;
import java.util.Map;
@Repository(name = "paper-repo", url = "https://papermc.io/repo/repository/maven-public/")
@ServerAPI(group = "com.destroystokyo.paper", id = "paper-api")
@YMLGen(handler = BukkitYMLGenHandler.class)
public class PaperServer extends MinecraftServer {

    @Override
    public Map<MinecraftVersion, String> getAPIVersions() {
        return new HashMap<MinecraftVersion, String>() {{
            put(MinecraftVersion.V1_14, "1.14.4-R0.1-SNAPSHOT");
            put(MinecraftVersion.V1_15, "1.15.2-R0.1-SNAPSHOT");
            put(MinecraftVersion.V1_16, "1.16.5-R0.1-SNAPSHOT");
        }};
    }
}
