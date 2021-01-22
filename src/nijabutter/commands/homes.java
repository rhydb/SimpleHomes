package nijabutter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nijabutter.SimpleHomes;
import nijabutter.Utilities.*;


public class homes implements CommandExecutor{
    private SimpleHomes plugin;
    public homes(SimpleHomes plugin) {
        this.plugin = plugin;
        plugin.getCommand("homes").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command!");
            return true;
        }
        String color = plugin.getConfig().getString("chat-color");
        String homeOwner;
        if (args.length == 1) {
            if (sender.hasPermission("homes.admin")) {
                homeOwner  = args[0];
            }
            else {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
                return true;
            }
        }
        else {
            homeOwner = sender.getName();
        }
        if (SimpleHomes.Homes().isSet(homeOwner)) {
            int homeCount = 0;
            for (String key : SimpleHomes.Homes().getConfigurationSection(homeOwner).getKeys(false)) {
                sender.sendMessage(Utils.chat(color + key));
                homeCount++;
            }
            int maxHomes = plugin.getConfig().getInt("max-home-count");
            sender.sendMessage(Utils.chat(color + homeOwner + " has " + Integer.toString(homeCount) + "/" + Integer.toString(maxHomes) + " homes"));
        }
        else {
            sender.sendMessage(Utils.chat(color + "No homes found"));
        }
        return true;
    }
}