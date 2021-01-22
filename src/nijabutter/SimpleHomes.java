package nijabutter;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nijabutter.commands.*;
/*
SimpleHomes v1.0
Author: nijabutter
*/

public class SimpleHomes extends JavaPlugin implements Listener{

    public static FileConfiguration homes;
    public static FileConfiguration config;
    public static File file;
    @Override
    public void onEnable()
    {
        if (!(new File(this.getDataFolder() + File.separator + "config.yml").exists())) {
            this.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder() + File.separator + "config.yml"));
        if (!(new File(this.getDataFolder() + File.separator + "Homes.yml").exists())) {
            this.saveResource("Homes.yml", false);
        }
        file = new File(this.getDataFolder(), "Homes.yml");
        homes = YamlConfiguration.loadConfiguration(new File(this.getDataFolder() + File.separator + "Homes.yml"));
        getServer().getPluginManager().registerEvents(this, this);
        new homes(this);
        new sethome(this);
        new delhome(this);
        new home(this);
    }

    public static void saveHomes() {
        try {
            Homes().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable()
    {
        HandlerList.unregisterAll();
    }

    public static FileConfiguration Homes() {
        return homes;
    }

}
