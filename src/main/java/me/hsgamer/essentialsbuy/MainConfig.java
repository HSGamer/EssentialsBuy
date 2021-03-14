package me.hsgamer.essentialsbuy;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.DoubleConfigPath;
import org.bukkit.plugin.Plugin;

public class MainConfig extends PathableConfig {
    public static final DoubleConfigPath WORTH_MULTIPLIER = new DoubleConfigPath("worth-multiplier", 1d);

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
