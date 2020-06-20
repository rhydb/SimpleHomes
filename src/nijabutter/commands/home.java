package nijabutter.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import nijabutter.SimpleHomes;
import nijabutter.Utilities.*;


public class home implements CommandExecutor{
	private SimpleHomes plugin;
	public home(SimpleHomes plugin) {
		this.plugin = plugin;
		plugin.getCommand("home").setExecutor(this);
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
					if (!(plugin.getConfig().getBoolean("do-teleport-wait"))) {
						World world = Bukkit.getWorld(SimpleHomes.Homes().getString(args[0] + "." + args[1] + ".world"));
						double x = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".X");
						double y = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Y");
						double z = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Z");
						p.teleport(new Location(world, x, y, z));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
						return true;
					}
					if (p.hasPermission("homes.skipwait")) {
						World world = Bukkit.getWorld(SimpleHomes.Homes().getString(args[0] + "." + args[1] + ".world"));
						double x = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".X");
						double y = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Y");
						double z = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Z");
						p.teleport(new Location(world, x, y, z));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
						return true;
					}
					if (plugin.getConfig().getBoolean("admin-bypass-wait")) {
						World world = Bukkit.getWorld(SimpleHomes.Homes().getString(args[0] + "." + args[1] + ".world"));
						double x = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".X");
						double y = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Y");
						double z = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Z");
						p.teleport(new Location(world, x, y, z));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
						return true;
					}
					final int startX = p.getLocation().getBlockX();
					final int startY = p.getLocation().getBlockY();
					final int startZ = p.getLocation().getBlockZ();
					
					BukkitRunnable x = new BukkitRunnable() {
						int time = 5;
						@Override
						public void run() {
							if (time == 0) {
								World world = Bukkit.getWorld(SimpleHomes.Homes().getString(args[0] + "." + args[1] + ".world"));
								double x = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".X");
								double y = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Y");
								double z = SimpleHomes.Homes().getDouble(args[0] + "." + args[1] + ".Z");
								p.teleport(new Location(world, x, y, z));
								p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
								cancel();
							}
							
							int newX = p.getLocation().getBlockX();
							int newY = p.getLocation().getBlockY();
							int newZ = p.getLocation().getBlockZ();
							if (newX != startX) {p.sendMessage(Utils.chat(color + "Teleport cancelled")); cancel();}
							else if (newY != startY) {p.sendMessage(Utils.chat(color + "Teleport cancelled")); cancel();}
							else if (newZ != startZ) {p.sendMessage(Utils.chat(color + "Teleport cancelled")); cancel();}
							else if (time != 0) {p.sendMessage(Utils.chat(color + "Teleporting in: " + time + "...")); time--;}
							
						}
					};
					x.runTaskTimer(plugin, 0L, 20L);
					return true;
				}
				p.sendMessage(Utils.chat(color + "Home does not exist"));
				return true;
			}
			p.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
			return true;
		}
		
		
		if (Utils.homeExists(p.getName(), args[0])) {
			if (!(plugin.getConfig().getBoolean("do-teleport-wait"))) {
				World world = Bukkit.getWorld(SimpleHomes.Homes().getString(p.getName() + "." + args[0] + ".world"));
				double x = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".X");
				double y = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Y");
				double z = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Z");
				p.teleport(new Location(world, x, y, z));
				p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
				return true;
			}
			if (p.hasPermission("homes.skipwait")) {
				World world = Bukkit.getWorld(SimpleHomes.Homes().getString(p.getName() + "." + args[0] + ".world"));
				double x = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".X");
				double y = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Y");
				double z = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Z");
				p.teleport(new Location(world, x, y, z));
				p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
				return true;
			}
			if (p.hasPermission("homes.admin")) {
				if (plugin.getConfig().getBoolean("admin-bypass-wait")) {
					World world = Bukkit.getWorld(SimpleHomes.Homes().getString(p.getName() + "." + args[0] + ".world"));
					double x = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".X");
					double y = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Y");
					double z = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Z");
					p.teleport(new Location(world, x, y, z));
					p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
					return true;
				}
			}
			final int startX = p.getLocation().getBlockX();
			final int startY = p.getLocation().getBlockY();
			final int startZ = p.getLocation().getBlockZ();
			
			BukkitRunnable x = new BukkitRunnable() {
				int time = plugin.getConfig().getInt("teleport-wait");
				@Override
				public void run() {
					if (time == 0) {
						if (!(SimpleHomes.Homes().isSet(p.getName() + "." + args[0]))) {
							p.sendMessage("There was an error teleporting you.");
							cancel();
							return;
						}
						World world = Bukkit.getWorld(SimpleHomes.Homes().getString(p.getName() + "." + args[0] + ".world"));
						double x = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".X");
						double y = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Y");
						double z = SimpleHomes.Homes().getDouble(p.getName() + "." + args[0] + ".Z");
						p.teleport(new Location(world, x, y, z));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
						cancel();
						return;
					}
					
					int newX = p.getLocation().getBlockX();
					int newY = p.getLocation().getBlockY();
					int newZ = p.getLocation().getBlockZ();
					if (newX != startX) {p.sendMessage(Utils.chat(color + "Teleport cancelled")); cancel();}
					else if (newY != startY) {p.sendMessage(Utils.chat(color + "Teleport cancelled")); cancel();}
					else if (newZ != startZ) {p.sendMessage(Utils.chat(color + "Teleport cancelled")); cancel();}
					else if (time != 0) {p.sendMessage(Utils.chat(color + "Teleporting in: " + time + "...")); time--;}
					
				}
			};
			x.runTaskTimer(plugin, 0L, 20L);
			return true;
		}
		p.sendMessage(Utils.chat(color + "Home does not exist"));
		return true;
	}
}
