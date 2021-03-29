package nl.voidgroup.gradle.plugin.opengmph;

import groovy.lang.Closure;
import nl.voidgroup.gradle.plugin.opengmph.annotation.ServerAPI;
import nl.voidgroup.gradle.plugin.opengmph.annotation.Repository;
import nl.voidgroup.gradle.plugin.opengmph.annotation.YMLGen;
import nl.voidgroup.gradle.plugin.opengmph.data.minecraft.MinecraftServer;
import nl.voidgroup.gradle.plugin.opengmph.data.minecraft.MinecraftVersion;
import nl.voidgroup.gradle.plugin.opengmph.extension.PluginData;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.jvm.tasks.Jar;

import javax.annotation.Nonnull;
import java.util.regex.Pattern;

public class OpenGMPH implements Plugin<Project> {

    public static final String ID_ATTRIBUTE = "Plugin-Id";
    public static final String NAME_ATTRIBUTE = "Name";
    public static final String GROUP_ATTRIBUTE = "Group-Id";
    public static final String VERSION_ATTRIBUTE = "Version";

    public static final Pattern VERSION_PATTERN = Pattern.compile("^\\d.\\d.\\d-[a-z]+-\\d+$");
    public static final Pattern BUILD_VERSION_PATTERN = Pattern.compile("^unstable(@\\d+)?$");

    public static String id;
    public static String name;
    public static String group;
    public static String version;

    public static final String PLUGIN_DATA_EXTENSION = "pluginData";

    @Override
    public void apply(@Nonnull Project project) {
        Logger logger = Logger.getLogger(getClass(), "NULL");
        logger.debug("Applying plugin");
        logger.debug("Instantiating ManifestHandler");
        ManifestHandler manifestHandler;
        try {
            manifestHandler = new ManifestHandler();
        } catch (Exception ex) {
            throw new GradleException("Failed to instantiate ManifestHandler", ex);
        }
        logger.debug("Getting plugin name");
        name = Util.requireNonNull(manifestHandler.get(NAME_ATTRIBUTE), "Plugin name not found in manifest");
        logger.debug("Getting plugin group");
        group = Util.requireNonNull(manifestHandler.get(GROUP_ATTRIBUTE), "Plugin group not found in manifest");
        logger.debug("Getting plugin version");
        version = Util.requireNonNull(manifestHandler.get(VERSION_ATTRIBUTE), "Plugin version not found in manifest");
        logger.debug("Getting plugin id");
        id = Util.requireNonNull(manifestHandler.get(ID_ATTRIBUTE), "Plugin id not found in manifest");
        logger.debug("Updating logger name");
        logger = Logger.getLogger(getClass(), name);
        if(BUILD_VERSION_PATTERN.matcher(version).matches()) {
            logger.debug("Detected unstable version");
            logger.lifecycle("***THIS IS A UNSTABLE VERSION***");
            logger.lifecycle("***REPORT ANY BUGS AT GITHUB IN THE ISSUES MODULE***");
            logger.lifecycle("***ISSUES: https://github.com/VoidStoneDev/OpenGMPH/issues***");
        } else if (!VERSION_PATTERN.matcher(version).matches()) {
            logger.debug("Detected modded version");
            logger.lifecycle("***THIS VERSION IS DETECTED TO BE MODIFIED***");
            logger.lifecycle("***NO OFFICIAL SUPPORT WILL BE GIVEN FOR THIS VERSION***");
        }
        logger.lifecycle("Using version: " + name + "@" + version);
        logger.debug("Registering extension: '" + PLUGIN_DATA_EXTENSION + "'");
        PluginData pluginData = project.getExtensions().create(PLUGIN_DATA_EXTENSION, PluginData.class);
        project.getTasks().create("genYML", nl.voidgroup.gradle.plugin.opengmph.task.YMLGen.class);
        project.afterEvaluate(_project -> {
            Logger _logger = Logger.getLogger(getClass(), name);
            _logger.debug("Starting Project#afterEvaluate");
            _logger.debug("Repository importing is: " + pluginData.importRepository);
            _logger.debug("API importing is: " + pluginData.importAPI);
            if(pluginData.importRepository) {
                _logger.debug("Importing repository");
                _logger.debug("Checking for Repository annotation");
                MinecraftServer targetServer = Util.requireNonNull(MinecraftServer.getByName(Util.requireNonNull(pluginData.targetServer, "Target server not set")), "Invalid target server");
                if(!targetServer.getClass().isAnnotationPresent(Repository.class)) {
                    throw new UnsupportedOperationException("Current target server does not support repository importing");
                }
                _logger.debug("Getting Repository annotation");
                Repository repositoryData = targetServer.getClass().getAnnotation(Repository.class);
                _logger.debug("Using repository name: " + repositoryData.name());
                _logger.debug("Using repository url: " + repositoryData.url());
                _logger.debug("Adding repository");
                _project.getRepositories().maven(repository -> {
                    _logger.debug("Setting repository name");
                    repository.setName(repositoryData.name());
                    _logger.debug("Setting repository url");
                    repository.setUrl(repositoryData.url());
                });
            }
            if(pluginData.importAPI) {
                _logger.debug("Importing API");
                _logger.debug("Checking for ServerAPI annotation");
                MinecraftServer targetServer = Util.requireNonNull(MinecraftServer.getByName(Util.requireNonNull(pluginData.targetServer, "Target server not set")), "Invalid target server");
                if(!targetServer.getClass().isAnnotationPresent(ServerAPI.class)) {
                    throw new UnsupportedOperationException("Current target server does not support api importing");
                }
                _logger.debug("Getting ServerAPI annotation");
                ServerAPI serverAPIData = targetServer.getClass().getAnnotation(ServerAPI.class);
                _logger.debug("Using group: " + serverAPIData.group());
                _logger.debug("Using id: " + serverAPIData.id());
                _logger.debug("Getting target version");
                MinecraftVersion targetVersion = MinecraftVersion.getByName(Util.requireNonNull(pluginData.targetVersion, "Target version not set"));
                String apiVersion;
                if(targetVersion != null) {
                    apiVersion = targetServer.getAPIVersions().get(targetVersion);
                    Util.requireNonNull(apiVersion, new UnsupportedOperationException("Current target server does not support current target version"));
                } else {
                    _logger.lifecycle("Using custom version: " + pluginData.targetVersion + " (may cause issues)");
                    apiVersion = pluginData.targetVersion;
                }
                _logger.debug("Using api version: " + apiVersion);
                Configuration compileOnlyConfig = _project.getConfigurations().getByName("compileOnly");
                _logger.debug("Importing: " + serverAPIData.group() + ":" + serverAPIData.id() + ":" + apiVersion);
                compileOnlyConfig.getDependencies().add(_project.getDependencies().create(serverAPIData.group() + ":" + serverAPIData.id() + ":" + apiVersion));
            }
            if(pluginData.generateYML) {
                _logger.debug("Setting up generate yml auto execute");
                _logger.debug("Checking for YMLGen annotation");
                MinecraftServer targetServer = Util.requireNonNull(MinecraftServer.getByName(Util.requireNonNull(pluginData.targetServer, "Target server not set")), "Invalid target server");
                if(!targetServer.getClass().isAnnotationPresent(nl.voidgroup.gradle.plugin.opengmph.annotation.YMLGen.class)) {
                    throw new UnsupportedOperationException("Current target server does not support yml generation");
                }
                _logger.debug("Getting YMLGen annotation");
                YMLGen serverGenData = targetServer.getClass().getAnnotation(YMLGen.class);
                _logger.debug("Using file: " + serverGenData.file());
                _logger.debug("Using handler: " + serverGenData.handler().getCanonicalName());
                Jar jarTask = (Jar) _project.getTasks().getByName("jar");
                jarTask.dependsOn("genYML");
                Object pluginDataContainer = null;
                try{
                    pluginDataContainer = serverGenData.handler().getConstructor().newInstance().getPluginDataContainer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pluginData.ymlData.setDelegate(pluginDataContainer);
                pluginData.ymlData.setResolveStrategy(Closure.DELEGATE_FIRST);
            }
        });
    }

}
