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
		if (!(sender instanceof Player)) {sender.sendMessage("Only players may use this commadn"); return true;}
		if (args.length == 0) return false;
		
		Player p = (Player) sender;
		String color = plugin.getConfig().getString("chat-color");
		
		if (args.length == 2) {
			if (p.hasPermission("homes.admin")) {
				if (Utils.homeExists(args[0], args[1])) {
					Utils.delHome(args[0], args[1]);
					p.sendMessage(Utils.chat(color + "Deleted " + args[0] + "'s home: " + args[1]));
					return true;
				}
				p.sendMessage(Utils.chat(color + "Home does not exist"));
				return true;
			}
			p.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
			return true;
		}
		if (Utils.homeExists(p.getName(), args[0])) {
			Utils.delHome(p.getName(), args[0]);
			p.sendMessage(Utils.chat(color + "Home removed: " + args[0]));
			return true;
		}
		p.sendMessage(Utils.chat(color + "Home does not exist"));
		return true;	
	}
}
