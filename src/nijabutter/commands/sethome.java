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

		// set other player home
		if (args.length == 2)
		{
		
			if (p.hasPermission("homes.admin")) {
				if (Utils.homeExists(args[0], args[1])) {
					if (plugin.getConfig().getBoolean("overwrite-homes")) {
						Utils.setHome(sender, args[0], args[1]);
						p.sendMessage(Utils.chat(color + "Home set for " + args[0] + ": " + args[1]));
						return true;
					}
					p.sendMessage(Utils.chat("&cThat home already exists"));
					return true;
				}
				Utils.setHome(sender, args[0], args[1]);
				p.sendMessage(Utils.chat(color + "Home set for " + args[0] + ": " + args[1]));
				return true;
			}
			p.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
			return true;
		}
		
		if (Utils.getHomeCount(p.getName()) >= plugin.getConfig().getInt("max-home-count")) {
			if (!(p.hasPermission("homes.bypasscap"))) {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("max-homecount-message")));
				return true;
			}
		}
		
		if (Utils.homeExists(p.getName(), args[0])) {
			if (plugin.getConfig().getBoolean("overwrite-homes")) {
				Utils.setHome(sender, p.getName(), args[0]);
				p.sendMessage(Utils.chat(color + "Home set: " + args[0]));
				return true;
			}
			p.sendMessage(Utils.chat("&cThat home already exists"));
			return true;
		}
		Utils.setHome(sender, p.getName(), args[0]);
		p.sendMessage(Utils.chat(color + "Home set: " + args[0]));
		return true;
		
	}
}
