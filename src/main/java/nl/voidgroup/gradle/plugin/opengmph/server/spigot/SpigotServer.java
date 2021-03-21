package nl.voidgroup.gradle.plugin.opengmph.server.spigot;

import nl.voidgroup.gradle.plugin.opengmph.annotation.Repository;
import nl.voidgroup.gradle.plugin.opengmph.data.minecrarft.MinecraftServer;
@Repository(name = "spigot-repo", url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
public class SpigotServer implements MinecraftServer {

}
