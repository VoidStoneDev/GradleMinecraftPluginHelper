package nl.voidgroup.gradle.plugin.opengmph;



import javax.annotation.Nullable;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * A handler for getting custom values in the <a href="https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html">MANIFEST.MF</a>
 */
public class ManifestHandler {
    private final Attributes jarAttributes;

    /**
     * Instantiate a new {@link ManifestHandler}
     * @throws IOException when a connection cannot be opened tho the jar
     */
    public ManifestHandler() throws IOException {
        URL jarURL = getClass().getResource("/" + getClass().getCanonicalName().replace('.', '/') + ".class");
        JarURLConnection jarConnection = (JarURLConnection) jarURL.openConnection();
        Manifest jarManifest = jarConnection.getManifest();
        jarAttributes = jarManifest.getMainAttributes();
    }

    /**
     * Get a string by a key
     * @param key The key that is used in the MANIFEST.MF
     * @return The value that is present in the MANIFEST.MF, or <strong>null</strong> if the
     * value was not found
     */
    @Nullable
    public String get(String key) {
        return jarAttributes.getValue(key);
    }
}
