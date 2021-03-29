package nl.voidgroup.gradle.plugin.opengmph.extension;

import groovy.lang.Closure;
import org.gradle.api.tasks.SourceSet;

public class PluginData {
    public String targetServer;
    public String targetVersion;
    public boolean importRepository = true;
    public boolean importAPI = true;
    public boolean generateYML = true;
    public String buildResourcesDir;
    public String sourceSet = SourceSet.MAIN_SOURCE_SET_NAME;
    public Closure<?> ymlData = new Closure<Object>(this) {
        @Override
        public Object call() {
            return null;
        }
    };
    public void ymlData(Closure<?> ymlData) {
        this.ymlData = ymlData;
    }
}
