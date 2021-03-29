package nl.voidgroup.gradle.plugin.opengmph.annotation;

import nl.voidgroup.gradle.plugin.opengmph.data.bukkit.BukkitYMLGenHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface YMLGen {
    String file() default "plugin.yml";
    Class<? extends BukkitYMLGenHandler> handler();
}
