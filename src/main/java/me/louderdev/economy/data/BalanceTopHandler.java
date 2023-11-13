package me.louderdev.economy.data;

import lombok.Getter;
import me.louderdev.economy.config.zConfig;
import me.louderdev.economy.zEconomy;
import net.achymake.economy.Economy;
import net.achymake.economy.files.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class BalanceTopHandler {

    @Getter private List<zPlayerData> allPlayersData = new ArrayList<>();

    // Create a NumberFormat instance with a specific locale that uses a comma


    public BalanceTopHandler(zEconomy plugin) {

        // Run this in async task to prevent server lag
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            updateTopBalace();
        }, 20L, 20 * 10 * 60L);
    }

    public void updateTopBalace() {
        long startAt = System.currentTimeMillis();

        this.allPlayersData = new ArrayList<>();

        for(OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()) {
            File file = new File(Economy.getInstance().getDataFolder(), "userdata/" + offlinePlayer.getUniqueId() + ".yml");
            zPlayerData playerData = new zPlayerData();

            if (file.exists()) {
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
                try {
                    yamlConfiguration.load(file);

                    double balace = yamlConfiguration.getDouble("account");
                    if(balace <= 0) continue;

                    playerData.setName(offlinePlayer.getName());
                    playerData.setBalance(yamlConfiguration.getDouble("account"));
                    allPlayersData.add(playerData);
                } catch (InvalidConfigurationException | IOException e) {
                    Message.sendLog(e.getMessage());
                }
            }
        }

        //sort the playerdata by their balance
        allPlayersData.sort(new Comparator<zPlayerData>() {
            @Override
            public int compare(zPlayerData o1, zPlayerData o2) {
                return (int) (o2.getBalance() - o1.getBalance());
            }
        });

        zConfig.sendMessage(Bukkit.getConsoleSender(), "&5[zEconomy] &aSuccessfully cached user balance in &2" + (System.currentTimeMillis() - startAt)
                + "&ams.");

    }
    public zPlayerData getByIndex(int index) {
        return allPlayersData.get(index);
    }
}
