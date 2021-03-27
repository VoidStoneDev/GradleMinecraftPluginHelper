package nl.voidgroup.gradle.plugin.opengmph.extension;

public class PluginData {
    public String targetServer;
    public String targetVersion;
    public boolean importRepository = true;
    public boolean importAPI = true;
    public void targetServer(String minecraftServer) {
        targetServer = minecraftServer;
    }
}
