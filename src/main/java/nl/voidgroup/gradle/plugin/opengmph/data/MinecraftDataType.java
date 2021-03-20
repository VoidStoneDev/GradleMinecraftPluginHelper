package nl.voidgroup.gradle.plugin.opengmph.data;

public interface MinecraftDataType {
    boolean equals(MinecraftDataType obj);
    MinecraftDataType clone() throws CloneNotSupportedException;
    String getString();
}
