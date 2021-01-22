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
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may use this commadn");
            return true;
        }
        if (args.length == 0) return false; // they must have at least one argument (/home <home name> or /home <player name> <home name>)

        final Player p = (Player) sender;
        final String color = plugin.getConfig().getString("chat-color"); // color to use in chat from config.yml
        boolean mustWait;
        String homeOwner;
        String homeName;

        mustWait = (
                plugin.getConfig().getBoolean("do-teleport-wait") ^
                (sender.hasPermission("homes.skipwait") ||
                (sender.hasPermission("homes.admin") & // they need to have both admin and admins be able to bypass
                plugin.getConfig().getBoolean("admin-bypass-wait")))
        );

        if (args.length == 2) { // use another player's home
            if (sender.hasPermission("homes.admin")) { // they need admin to teleport to other homes
                homeOwner = args[0];
                homeName = args[1];
            }
            else {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("invalid-perm-message")));
                return true;
            }
        }
        else {
            // trying to tp to their own home
            homeOwner = sender.getName();
            homeName = args[0];
        }

        if (Utils.homeExists(homeOwner, homeName)) {
            if (mustWait) {
                // get the player's location
                Location initialLocation = ((Player) sender).getLocation();
                // add the timer
                BukkitRunnable x = new BukkitRunnable() {
                    int time = plugin.getConfig().getInt("teleport-wait"); // the time to wait set in config
                    @Override
                    public void run() {
                        if (time == 0) {
                            // wait has ended time to teleport
                            if (!Utils.homeExists(homeOwner, homeName)) {
                                // the has since been deleted whilst waiting
                                p.sendMessage("There was an error teleporting you.");
                                cancel();
                                return;
                            }
                            Utils.teleportPlayer((Player) sender, homeOwner, homeName);
                            sender.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));
                            cancel();
                            return;
                        }
                        Location currentLocation = ((Player) sender).getLocation();
                        if (initialLocation.getX() != currentLocation.getX() ||
                            initialLocation.getY() != currentLocation.getY() ||
                            initialLocation.getZ() != currentLocation.getZ()) {
                            // they've moved and the config says to cancel on move
                            sender.sendMessage(Utils.chat(plugin.getConfig().getString("move-cancel-message")));
                            cancel();
                        }
                        else {
                            sender.sendMessage(Utils.chat(plugin.getConfig().getString("countdown-message").replace("<time>", Integer.toString(time))));
                            time--;
                        }
                    }
                };
                x.runTaskTimer(plugin, 0L, 20L);
            }
            else {
                // tp them directly
                Utils.teleportPlayer((Player) sender, homeOwner, homeName); // tp the sender to homeOwner's home called homeName
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("teleport-color") + plugin.getConfig().getString("teleport-message")));

            }
        }
        else {
            sender.sendMessage(Utils.chat(color + "Home does not exist"));
        }
        return true;
    }
}
