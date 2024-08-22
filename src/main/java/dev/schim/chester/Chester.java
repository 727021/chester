package dev.schim.chester;

import org.bukkit.plugin.java.JavaPlugin;

public class Chester extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SignListener(), this);
    }
}
