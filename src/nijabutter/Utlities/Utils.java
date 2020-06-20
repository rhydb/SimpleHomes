package nijabutter.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import nijabutter.*;

public class Utils {
	static SimpleHomes plugin = SimpleHomes.getPlugin(SimpleHomes.class);
	private static String color = plugin.getConfig().getString("chat-color");
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static String getHomes(CommandSender s, String p) {
		String msg = "";
		int homeCount = getHomeCount(p);
		
		if (s.getName() != p) {
			if (homeCount == 0) { return color + p + " has no homes"; }
			msg += color + "Homes of " + p + "(" + homeCount + "):\n";
			for (String key : SimpleHomes.Homes().getConfigurationSection(p).getKeys(false)) {
				msg += color + key + "\n";
	 		}
			return msg;
		}
		
		if (homeCount == 0) { return color + "You have no homes"; }
		msg += color + "Your homes (" + homeCount + "):\n";
		for (String key : SimpleHomes.Homes().getConfigurationSection(p).getKeys(false)) {
			msg += color + key + "\n";
 		}
		return msg;
	}
	
	public static int getHomeCount(String p) {
		int homecount = 0;
		if (SimpleHomes.Homes().isSet(p)) {
			for (String key : SimpleHomes.Homes().getConfigurationSection(p).getKeys(false)) { 
				homecount++;
			}
		}
		return homecount;
	}
	
	public static boolean homeExists(String p, String homeName) {
		if (SimpleHomes.Homes().isSet(p + "." + homeName)) {
			return true;
		}
		return false;
	}
	
	public static void setHome(CommandSender s, String p, String homeName) {
		Player sp = (Player) s;
		final double X = sp.getLocation().getX();
		final double Y = sp.getLocation().getY();
		final double Z = sp.getLocation().getZ();
		final String world = sp.getWorld().getName();
		final Vector dir = sp.getLocation().getDirection();
		SimpleHomes.Homes().set(p + "." + homeName + ".world", world);
		SimpleHomes.Homes().set(p + "." + homeName + ".X", X);
		SimpleHomes.Homes().set(p + "." + homeName + ".Y", Y);
		SimpleHomes.Homes().set(p + "." + homeName + ".Z", Z);
		SimpleHomes.saveHomes();
	}
	
	public static void delHome(String p, String homeName) {
		SimpleHomes.Homes().set(p + "." + homeName, null);
		SimpleHomes.saveHomes();
	}
}


