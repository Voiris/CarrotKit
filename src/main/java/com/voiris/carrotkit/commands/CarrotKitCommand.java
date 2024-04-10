package com.voiris.carrotkit.commands;

import com.voiris.carrotkit.CarrotKit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CarrotKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(
                        "[CarrotKit] Help" +
                        "/carrotkit help - Вводит это сообщение" +
                        "/carrotkit reload - Перезагружает плагин");
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                CarrotKit.getInstance().reloadConfig();
                sender.sendMessage("[CarrotKit] Plugin was reloaded");
                return true;
            }
        }
        sender.sendMessage("[CarrotKit] Made by Voiris!");
        return true;
    }
}
