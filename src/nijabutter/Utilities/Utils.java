package nijabutter.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nijabutter.*;

public class Utils {
    static SimpleHomes plugin = SimpleHomes.getPlugin(SimpleHomes.class);
    private static final String color = plugin.getConfig().getString("chat-color");

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String getHomes(CommandSender s, String p) {
        String msg = "";
        int homeCount = getHomeCount(p);

        if (s.getName().equals(p)) {
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

    public static boolean homeExists(String playerName, String homeName) {
        return (SimpleHomes.Homes().isSet(playerName + "." + homeName));
    }

    public static void teleportPlayer(Player playerToTeleport, String homeOwner, String homeName) {
        World world = Bukkit.getWorld(SimpleHomes.Homes().getString(homeOwner + "." + homeName + ".world"));
        double x = SimpleHomes.Homes().getDouble(homeOwner + "." + homeName + ".X");
        double z = SimpleHomes.Homes().getDouble(homeOwner + "." + homeName + ".Z");
        double y = SimpleHomes.Homes().getDouble(homeOwner + "." + homeName + ".Y");
        playerToTeleport.teleport(new Location(world, x, y, z));
    }

    public static void setHome(Location location, String homeOwner, String homeName) {
        final double X = location.getX();
        final double Y = location.getY();
        final double Z = location.getZ();
        final String world = location.getWorld().getName();
        SimpleHomes.Homes().set(homeOwner + "." + homeName + ".world", world);
        SimpleHomes.Homes().set(homeOwner + "." + homeName + ".X", X);
        SimpleHomes.Homes().set(homeOwner + "." + homeName + ".Y", Y);
        SimpleHomes.Homes().set(homeOwner + "." + homeName + ".Z", Z);
        SimpleHomes.saveHomes();
    }

    public static void delHome(String p, String homeName) {
        SimpleHomes.Homes().set(p + "." + homeName, null);
        SimpleHomes.saveHomes();
    }
}


