package nl.voidgroup.gradle.plugin.opengmph.data.bukkit;

import nl.voidgroup.gradle.plugin.opengmph.Util;
import nl.voidgroup.gradle.plugin.opengmph.exception.InvalidArgumentException;

import java.util.regex.Pattern;

public class BukkitPluginMain {
    public static final Pattern PATTERN = Pattern.compile("^(?!org\\.bukkit\\.)([a-zA-Z_$][a-zA-Z\\d_$]*\\.)*[a-zA-Z_$][a-zA-Z\\d_$]*$");

    private final String string;

    public BukkitPluginMain(String string) {
        this.string = Util.requireNonNull(string, "string cannot be null");
        if(!PATTERN.matcher(string).matches()) throw new InvalidArgumentException("string is invalid, '" + string + "' must match '" + PATTERN.pattern() + "'");
    }

    public String getString() {
        return string;
    }

}
