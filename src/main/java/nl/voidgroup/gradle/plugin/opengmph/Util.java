package nl.voidgroup.gradle.plugin.opengmph;

public class Util {

    public static <T> T requireNonNull(T obj, RuntimeException ex) {
        if(obj == null) throw ex;
        return obj;
    }
    public static <T> T requireNonNull(T obj) {
        return requireNonNull(obj, "Object cannot be null");
    }
    public static <T> T requireNonNull(T obj, String msg) {
        return requireNonNull(obj, new NullPointerException(msg));
    }
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
