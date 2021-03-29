package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

import nl.voidgroup.gradle.plugin.opengmph.Logger;
import nl.voidgroup.gradle.plugin.opengmph.Util;
import nl.voidgroup.gradle.plugin.opengmph.exception.InvalidArgumentException;

import java.util.regex.Pattern;

public class BukkitPluginName {
    public static final Pattern PATTERN = Pattern.compile("^[A-Za-z0-9_.-]+$");

    private final String string;

    public BukkitPluginName(String string) {
        this.string = Util.requireNonNull(string, "string cannot be null");
        if(!PATTERN.matcher(string).matches()) {
            throw new InvalidArgumentException("string is invalid, '" + string + "' must match '" + PATTERN.pattern() + "'");
        }
    }

    public String getString() {
        return string;
    }

}
