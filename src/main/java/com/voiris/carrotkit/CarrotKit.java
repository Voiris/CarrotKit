package com.voiris.carrotkit;

import com.voiris.carrotkit.commands.CarrotKitCommand;
import com.voiris.carrotkit.commands.KitsCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class CarrotKit extends JavaPlugin {
    private static CarrotKit instance;
    Logger log = Bukkit.getLogger();
    File configFile = new File(getDataFolder(), "config.yml");
    FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        if (!configFile.exists()) {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                config.options().copyDefaults(true);
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File usesDir = new File("world", "CarrotKit");
        if (!usesDir.exists()) {
            usesDir.mkdirs();
        }
        PluginCommand carrotKitCommand = getCommand("carrotkit");
        if (carrotKitCommand != null) {
            carrotKitCommand.setExecutor(new CarrotKitCommand());
        }
        PluginCommand kitCommand = getCommand("kits");
        if (kitCommand != null) {
            kitCommand.setExecutor(new KitsCommand());
        }
        log.info("[CarrotKit] Plugin enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CarrotKit getInstance() {
        return instance;
    }
}
