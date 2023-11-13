package me.louderdev.economy.command;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.louderdev.economy.config.zConfig;
import me.louderdev.economy.data.BalanceTopHandler;
import me.louderdev.economy.data.zPlayerData;
import me.louderdev.economy.zEconomy;
import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.files.Message;
import net.achymake.economy.files.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class BalanceTopCommand implements CommandExecutor, TabCompleter {

    public static FileConfiguration config = zConfig.getConfig();
    public final BalanceTopHandler balTopHandler = zEconomy.getInstance().getBalanceTopHandler();


    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) return false;
        // Prevent data is empty
        if(balTopHandler.getAllPlayersData().size() <= 0) {
            zConfig.sendMessage(sender, zConfig.NO_DATA_TO_DISPLAY());
            return true;
        }

        int x = 0;

        for (String line : config.getStringList("BALANCE_TOP_MESSAGE")) {
            try {
                zPlayerData data = balTopHandler.getByIndex(x++);
                zConfig.sendMessage(sender, line, data.getName(), decimalFormat.format(data.getBalance()));
            } catch (Exception e) {
                zConfig.sendMessage(sender, line, zConfig.PLAYER_NOT_FOUND(), zConfig.BALANCE_NOT_FOUND());
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }


}
