package net.brassburg.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class ConfigManager {
    public static void register() {
        AutoConfig.register(ConfigOptions.class, GsonConfigSerializer::new);
    }

    public static ConfigOptions getConfig() {
        return AutoConfig.getConfigHolder(ConfigOptions.class).getConfig();
    }

    public static void saveConfig() {
        AutoConfig.getConfigHolder(ConfigOptions.class).save();
    }
}
