package me.hsgamer.essentialsbuy;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.StringConfigPath;
import org.bukkit.plugin.Plugin;

public class MessageConfig extends PathableConfig {
    public static final StringConfigPath PREFIX = new StringConfigPath("prefix", "&f[&cBuy&f] &r");
    public static final StringConfigPath PLAYER_ONLY = new StringConfigPath("player-only", "&cYou need to be a player to do that");
    public static final StringConfigPath NO_PERMISSION = new StringConfigPath("no-permission", "&cYou don't have the permission to do that");
    public static final StringConfigPath ITEM_NOT_FOUND = new StringConfigPath("item-not-found", "&cThe item is not found");
    public static final StringConfigPath BUY_WORTH_MESSAGE = new StringConfigPath("buy-worth-message", "&eThe price of &f{item} x{amount} &eis {money}");
    public static final StringConfigPath BUY_MESSAGE = new StringConfigPath("buy-message", "&eBought &f{item} x{amount} &ewith {money}");
    public static final StringConfigPath BUY_CONSOLE_MESSAGE = new StringConfigPath("buy-sonsole-message", "&f{name} &ebought &f{item} x{amount} &ewith {money}");
    public static final StringConfigPath CAN_NOT_BUY = new StringConfigPath("can-not-buy", "&cCan't buy &f{item} x{amount}. &eThe price is {money}");
    public static final StringConfigPath WORTH_NOT_FOUND = new StringConfigPath("buy-worth-not-found", "&cThe item can't be bought");
    public static final StringConfigPath POSITIVE_AMOUNT = new StringConfigPath("positive-amount", "&cThe amount should be positive");

    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "message.yml"));
    }
}
