package nijabutter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nijabutter.SimpleHomes;
import nijabutter.Utilities.*;


public class delhome implements CommandExecutor{
    private SimpleHomes plugin;
    public delhome(SimpleHomes plugin) {
        this.plugin = plugin;
        plugin.getCommand("delhome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command");
            return true;
        }
        if (args.length == 0) return false; // must be either /delhome <home name> or /delhome <home owner> <home name>

        Player p = (Player) sender;
        String color = plugin.getConfig().getString("chat-color");
        String homeOwner;
        String homeName;
        if (args.length == 2) {
            if (p.hasPermission("homes.admin")) {
                homeOwner = args[0];
                homeName = args[1];
            }
            else {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
                return true;
            }
        }
        else {
            // delete their own home
            homeOwner = sender.getName();
            homeName = args[0];
        }
        if (Utils.homeExists(homeOwner, homeName)) {
            Utils.delHome(homeOwner, homeName);
            sender.sendMessage(Utils.chat(color + "Home removed: " + homeName));
            return true;
        }
        sender.sendMessage(Utils.chat(color + "Home does not exist"));
        return true;
    }
}
