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
		Player p = (Player) sender;
		if (args.length == 1) {
			if (p.hasPermission("homes.admin")) {
				if (SimpleHomes.Homes().isSet(args[0])) {
					p.sendMessage(Utils.chat(Utils.getHomes(sender, args[0])));
					return true;
				}
				p.sendMessage(Utils.chat(color + "No homes found or player doesnt exist"));
				return true;
			}
			p.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
			return true;
		}
		if (SimpleHomes.Homes().isSet(p.getName())) {
			p.sendMessage(Utils.chat(Utils.getHomes(sender, p.getName())));
			return true;
		}
		p.sendMessage(Utils.chat(color + "No homes found"));
		return true;
	}
}
