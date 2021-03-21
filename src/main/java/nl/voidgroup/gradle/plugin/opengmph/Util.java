package nl.voidgroup.gradle.plugin.opengmph;

public class Util {
    public static <T> T requireNonNull(T obj, String msg) {
        if(obj == null) throw new NullPointerException(msg);
        return obj;
    }
    public static <T> T requireNonNull(T obj) {
        return requireNonNull(obj, "Object cannot be null");
    }
}
