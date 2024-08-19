package de.moritzmcc.commands;

import de.moritzmcc.gui.ManageMenuInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp()){
            commandSender.sendMessage(ChatColor.DARK_RED + "You do not have the required permissions");
            return false;
        }
        if (! (commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.DARK_RED + "You have to be a Player to execute this command");
            return false;
        }

        Player player =(Player) commandSender;
      player.openInventory( new ManageMenuInventory().getManageInventory(player));
        return false;
    }
}
