package nl.voidgroup.gradle.plugin.opengmph;

import org.slf4j.LoggerFactory;

public abstract class Logger {

    private org.gradle.api.logging.Logger gradleLogger;
    private String name;

    public Logger(Class<?> clazz, String name) {
        gradleLogger = (org.gradle.api.logging.Logger) LoggerFactory.getLogger(clazz);
        this.name = name;
    }

    public abstract void debug(String msg);
    public abstract void debug(String msg, Throwable cause);

    public abstract void warn(String msg);
    public abstract void warn(String msg, Throwable cause);

    public abstract void error(String msg);
    public abstract void error(String msg, Throwable cause);

    public abstract void info(String msg);
    public abstract void info(String msg, Throwable cause);

    public abstract void quiet(String msg);
    public abstract void quiet(String msg, Throwable cause);

    public abstract void lifecycle(String msg);
    public abstract void lifecycle(String msg, Throwable cause);

    public org.gradle.api.logging.Logger getGradleLogger() {
        return gradleLogger;
    }
    public String getName() {
        return name;
    }

    public static Logger getLogger(Class<?> clazz, String name) {
        return new Logger(clazz, name) {
            @Override
            public void debug(String msg) {
                getGradleLogger().debug("<" + getName() + "> " + msg);
            }

            @Override
            public void debug(String msg, Throwable cause) {
                getGradleLogger().debug("<" + getName() + "> " + msg, cause);
            }

            @Override
            public void warn(String msg) {
                getGradleLogger().warn("<" + getName() + "> " + msg);
            }

            @Override
            public void warn(String msg, Throwable cause) {
                getGradleLogger().warn("<" + getName() + "> " + msg, cause);
            }

            @Override
            public void error(String msg) {
                getGradleLogger().error("<" + getName() + "> " + msg);
            }

            @Override
            public void error(String msg, Throwable cause) {
                getGradleLogger().error("<" + getName() + "> " + msg, cause);
            }

            @Override
            public void info(String msg) {
                getGradleLogger().info("<" + getName() + "> " + msg);
            }

            @Override
            public void info(String msg, Throwable cause) {
                getGradleLogger().info("<" + getName() + "> " + msg, cause);
            }

            @Override
            public void quiet(String msg) {
                getGradleLogger().quiet("<" + getName() + "> " + msg);
            }

            @Override
            public void quiet(String msg, Throwable cause) {
                getGradleLogger().quiet("<" + getName() + "> " + msg, cause);
            }

            @Override
            public void lifecycle(String msg) {
                getGradleLogger().lifecycle(msg);
            }

            @Override
            public void lifecycle(String msg, Throwable cause) {
                getGradleLogger().lifecycle(msg, cause);
            }
        };
    }

}
