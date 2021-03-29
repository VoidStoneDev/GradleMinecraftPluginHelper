package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

import groovy.lang.Closure;

public class BukkitPluginData {

    public String name;
    public String version;
    public String main;
    public String description;
    public String apiVersion;
    public String loadTime;
    public String[] authors;
    public String website;
    public String[] depend;
    public String logPrefix;
    public String[] softDepend;
    public String[] loadBefore;
    public Closure<?>[] commands;
    public Closure<?>[] permissions;

    protected BukkitPluginData() {

    }
}
