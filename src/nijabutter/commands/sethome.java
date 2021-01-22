package nijabutter.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nijabutter.SimpleHomes;
import nijabutter.Utilities.*;


public class sethome implements CommandExecutor{
    private SimpleHomes plugin;

    public sethome(SimpleHomes plugin) {
        this.plugin = plugin;
        plugin.getCommand("sethome").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage("Only players may use this command"); return true; }

        if (args.length == 0) { return false; }

        Player p = (Player) sender;
        String color = plugin.getConfig().getString("chat-color");
        String homeOwner;
        String homeName;
        // set other player home
        if (args.length == 2)
        {
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
            homeOwner = sender.getName();
            homeName = args[0];
        }
        if (Utils.getHomeCount(homeOwner) >= plugin.getConfig().getInt("max-home-count")) {
            if (!sender.hasPermission("homes.bypasscap")) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("max-homecount-message")));
                return true;
            }
        }

        if (Utils.homeExists(homeOwner, homeName) && !plugin.getConfig().getBoolean("overwrite-homes")) {
                // the home exists but overwriting is disabled
                sender.sendMessage(Utils.chat("&cThat home already exists"));
        }
        else {
            Utils.setHome(((Player) sender).getLocation(), homeOwner, homeName);
            p.sendMessage(Utils.chat(color + "Home set: " + homeName));
        }
        return true;
    }
}
