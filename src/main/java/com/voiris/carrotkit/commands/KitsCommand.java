package com.voiris.carrotkit.commands;

import com.voiris.carrotkit.CarrotKit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class KitsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = CarrotKit.getInstance().getServer().getPlayer(sender.getName());
        if (player != null) {
            String uuid = player.getUniqueId().toString();
            if (args.length > 0) {
                String kit = args[0];
                if (CarrotKit.getInstance().getConfig().get("kits." + kit) != null) {
                    File useFile = new File("world/CarrotKit/", kit + "_" + uuid + ".used");
                    if (!useFile.exists()) {
                        if (sender.hasPermission("carrotkit.kit." + kit)) {
                            List<String> items = CarrotKit.getInstance().getConfig().getStringList("kits." +
                                    kit);
                            for (int i = 0; i < items.size(); i++) {
                                CarrotKit.getInstance().getServer().dispatchCommand(
                                        CarrotKit.getInstance().getServer().getConsoleSender(),
                                        "minecraft:give " + sender.getName() + " " + items.get(i));
                            }
                            try {
                                useFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage("§7[§aCarrotKit§7] §aНабор " + kit + " выдан");
                        } else {
                            sender.sendMessage("§7[&§aCarrotKit§7] §cУ вас нет прав на получение этого набора");
                        }
                    } else {
                        sender.sendMessage("§7[§aCarrotKit§7] §cВы уже получали этот набор");
                    }
                } else {
                    sender.sendMessage("§7[§aCarrotKit§7] §cТакого набора не существует");
                }
            } else {
                sender.sendMessage("§7[§aCarrotKit§7] §rВведите название набора");
            }
        } else {
            sender.sendMessage("[CarrotKit] This command only for player, not console!");
        }
        return true;
    }
}