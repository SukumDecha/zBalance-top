package me.louderdev.economy;

import lombok.Getter;
import me.louderdev.economy.command.BalanceTopCommand;
import me.louderdev.economy.command.zBalanceReloadCommand;
import me.louderdev.economy.config.zConfig;
import me.louderdev.economy.data.BalanceTopHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class zEconomy extends JavaPlugin {

    @Getter private static zEconomy instance;
    @Getter private BalanceTopHandler balanceTopHandler;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        registerTopHandler();
        registerCommand();
        zConfig.setup();;

    }



    public void registerTopHandler() {
        balanceTopHandler = new BalanceTopHandler(this);
    }
    public void registerCommand() {
        getCommand("balancetop").setExecutor(new BalanceTopCommand());
        getCommand("zbalreload").setExecutor(new zBalanceReloadCommand());
    }
    /*
     - '&5%top_1_name% has &6%top_1_bal%'
  - '&d%top_2_name% has &6%top_2_bal%'
  - '&e%top_3_name% has &6%top_3_bal%'
  - '&7%top_4_name% has &f%top_4_bal%'
  - '&7%top_5_name% has &f%top_5_bal%'
  - '&7%top_6_name% has &f%top_6_bal%'
  - '&7%top_7_name% has &f%top_7_bal%'
  - '&7%top_8_name% has &f%top_8_bal%'
  - '&7%top_9_name% has &f%top_9_bal%'
  - '&7%top_10_name% has &f%top_10_bal%'

     */
}
