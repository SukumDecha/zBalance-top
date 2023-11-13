package me.louderdev.economy.command;

import me.louderdev.economy.config.zConfig;
import me.louderdev.economy.data.BalanceTopHandler;
import me.louderdev.economy.data.zPlayerData;
import me.louderdev.economy.zEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class zBalanceReloadCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        zEconomy.getInstance().getBalanceTopHandler().updateTopBalace();

        zConfig.reload();
        zConfig.sendMessage(sender, "&5[zEconomy] &dSuccesfully reloaded the plugin.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }


}
