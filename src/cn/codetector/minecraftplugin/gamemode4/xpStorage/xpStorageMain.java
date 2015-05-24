package cn.codetector.minecraftplugin.gamemode4.xpStorage;

import org.apache.logging.log4j.core.config.plugins.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class xpStorageMain extends JavaPlugin {
    public MainCycle cycle = new MainCycle(this);
    @Override
    public void onEnable() {
        getLogger().info("Started");
        getServer().getPluginManager().registerEvents(new PlayerMovementListener(this), this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, cycle, 0L, 10L);
    }

    @Override
    public void onDisable(){

    }
}
