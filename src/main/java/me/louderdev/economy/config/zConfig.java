package me.louderdev.economy.config;

import lombok.Getter;
import me.louderdev.economy.zEconomy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.MessageFormat;

public class zConfig {

    private static File file = new File(zEconomy.getInstance().getDataFolder(), "config.yml");
    @Getter private static FileConfiguration config =  YamlConfiguration.loadConfiguration(file);

    public static void setup() {
        try {
            config.save(file);
        } catch (Exception e) {
            logs(e.getMessage());
        }
    }

    public static String PLAYER_NOT_FOUND() {
        return config.getString("BALANCE_FORMAT.PLAYER_NOT_FOUND");
    }

    public static String BALANCE_NOT_FOUND() {
        return config.getString("BALANCE_FORMAT.BALANCE_NOT_FOUND");
    }

    public static String NO_DATA_TO_DISPLAY() {
        return config.getString("NO_DATA_TO_DISPLAY");
    }
    public static void reload() {
        file = new File(zEconomy.getInstance().getDataFolder(), "config.yml");;
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static void sendMessage(CommandSender sender, String messages, String placeholder_1, String placeholder_2) {
        sender.sendMessage(translate(MessageFormat.format(messages, new Object[] { placeholder_1, placeholder_2 })));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(translate(message));
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void logs(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage("[zEconomy] " + message);
    }
}
