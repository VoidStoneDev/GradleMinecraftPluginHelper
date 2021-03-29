package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

import groovy.lang.Closure;
import nl.voidgroup.gradle.plugin.opengmph.Util;
import nl.voidgroup.gradle.plugin.opengmph.data.YMLObject;
import nl.voidgroup.gradle.plugin.opengmph.exception.InvalidArgumentException;
import nl.voidgroup.gradle.plugin.opengmph.extension.PluginData;

import java.util.Map;

public class BukkitYMLGenHandler {

    public BukkitYMLGenHandler() {

    }

    public YMLObject generate(PluginData pluginData) {
        BukkitPluginData data = new BukkitPluginData();
        pluginData.ymlData.setDelegate(data);
        pluginData.ymlData.call();
        YMLObject output = new YMLObject();
        output.add("name", new BukkitPluginName(Util.requireNonNull(data.name, "Plugin name not set")).getString());
        output.add("version", Util.requireNonNull(data.version, "Plugin version not set"));
        output.add("main", new BukkitPluginMain(Util.requireNonNull(data.main, "Plugin main not set")).getString());
        if(!Util.isNull(data.description)) output.add("description", data.description);
        if(!Util.isNull(data.apiVersion)) output.add("api-version", Util.requireNonNull(BukkitAPIVersion.getByName(data.apiVersion), "Invalid api version").getString());
        if(!Util.isNull(data.loadTime)) output.add("load", Util.requireNonNull(BukkitLoadTime.getByName(data.loadTime), "Invalid load time").getString());
        if(!Util.isNull(data.authors)) output.add("authors", data.authors);
        if(!Util.isNull(data.website)) output.add("website", data.website);
        if(!Util.isNull(data.depend)) {
            output.add("depend", data.depend);
            for(String plugin : data.depend) {
                try {
                    new BukkitPluginName(plugin);
                } catch (InvalidArgumentException ex) {
                    throw new InvalidArgumentException("Illegal depend");
                }
            }
        }
        if(!Util.isNull(data.logPrefix)) output.add("prefix", data.logPrefix);
        if(!Util.isNull(data.softDepend)) {
            output.add("softdepend", data.softDepend);
            for(String plugin : data.softDepend) {
                try {
                    new BukkitPluginName(plugin);
                } catch (InvalidArgumentException ex) {
                    throw new InvalidArgumentException("Invalid soft depend");
                }
            }
        }
        if(!Util.isNull(data.loadBefore)) {
            output.add("loadbefore", data.loadBefore);
            for(String plugin : data.loadBefore) {
                try {
                    new BukkitPluginName(plugin);
                } catch (InvalidArgumentException ex) {
                    throw new InvalidArgumentException("Invalid load before");
                }
            }
        }
        if(!Util.isNull(data.commands)) {
            YMLObject commands = new YMLObject();
            for(Closure<?> clos : data.commands) {
                BukkitPluginCommand command = new BukkitPluginCommand();
                clos.setDelegate(command);
                clos.setResolveStrategy(Closure.DELEGATE_FIRST);
                clos.call();
                YMLObject obj = new YMLObject();
                if(!Util.isNull(command.description)) obj.add("description", command.description);
                if(!Util.isNull(command.aliases)) obj.add("aliases", command.aliases);
                if(!Util.isNull(command.permission)) obj.add("permission", command.permission);
                if(!Util.isNull(command.permissionMessage)) obj.add("permission-message", command.permissionMessage);
                if(!Util.isNull(command.usage)) obj.add("usage", command.usage);
                commands.add(Util.requireNonNull(command.name, "Command name not set"), obj);
            }
            output.add("commands", commands);
        }
        if(!Util.isNull(data.permissions)) {
            YMLObject permissions = new YMLObject();
            for(Closure<?> clos : data.permissions) {
                BukkitPluginPermission permission = new BukkitPluginPermission();
                clos.setDelegate(permission);
                clos.setResolveStrategy(Closure.DELEGATE_FIRST);
                clos.call();
                YMLObject permissionYML = new YMLObject();
                if(!Util.isNull(permission.description)) permissionYML.add("description", permission.description);
                if(!Util.isNull(permission._default)) permissionYML.add("default", Util.requireNonNull(BukkitPluginPermissionDefault.getByName(permission._default), "Invalid default permission value"));
                if(!Util.isNull(permission.children)) {
                    YMLObject children = new YMLObject();
                    for(Map.Entry<String, Boolean> entry : permission.children.entrySet()) {
                        children.add(entry.getKey(), entry.getValue());
                    }
                    permissionYML.add("children", children);
                }
                permissions.add(Util.requireNonNull(permission.name, "Permission name not set"), permissionYML);
            }
            output.add("permissions", permissions);
        }
        return output;
    }
    public Object getPluginDataContainer() {
        return new BukkitPluginData();
    }

}
