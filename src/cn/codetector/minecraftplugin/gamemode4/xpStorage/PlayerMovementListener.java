package cn.codetector.minecraftplugin.gamemode4.xpStorage;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerMovementListener implements Listener {
    xpStorageMain plugin;
    public PlayerMovementListener(xpStorageMain p){
        this.plugin = p;
    }
    @EventHandler
    public void PlayerMoveHandler(PlayerMoveEvent event){
        if(event.getPlayer().getLocation().add(0,0,0).getBlock().getType() == Material.ENDER_CHEST) {
            plugin.cycle.minusPlayer(event.getPlayer());
        }else if(event.getPlayer().getLocation().add(0, 2, 0).getBlock().getType() == Material.ENDER_CHEST){
            plugin.cycle.addPlayer(event.getPlayer());
        }
    }

}
