package cn.codetector.minecraftplugin.gamemode4.xpStorage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MainCycle implements Runnable {

    private JavaPlugin plugin;
    public boolean isRunning = false;
    private List<Player> players = new ArrayList<>();
    private List<Player> players_m = new ArrayList<>();


    public MainCycle(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void addPlayer(Player player){
        if(plugin.getConfig().get("gm4.xpStorage.player."+player.getUniqueId().toString()) == null){
            plugin.getConfig().set("gm4.xpStorage.player."+player.getUniqueId().toString(),0);
        }
        if (!this.players.contains(player)){
            this.players.add(player);
        }
    }

    public void minusPlayer(Player player){
        if(plugin.getConfig().get("gm4.xpStorage.player."+player.getUniqueId().toString()) == null){
            plugin.getConfig().set("gm4.xpStorage.player."+player.getUniqueId().toString(),0);
        }
        if (!this.players_m.contains(player)){
            this.players_m.add(player);
        }
    }

    public void run(){
        plugin.reloadConfig();
        for (int i = 0; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            int strlvl = Integer.parseInt(plugin.getConfig().get("gm4.xpStorage.player."+player.getUniqueId().toString()).toString());
            if(strlvl>0) {
                int lvl = player.getLevel();
                lvl++;
                strlvl--;
                Title t = new Title("Bank Balance:"+ strlvl);
                t.setSubtitle("");
                t.setSubtitle("Powered by xpBank");
                t.send(player);
                plugin.getConfig().set("gm4.xpStorage.player." + player.getUniqueId().toString(), strlvl);
                player.setLevel(lvl);
            }else{
                Title t = new Title("Bank Balance:" + strlvl);
                t.setTitleColor(ChatColor.RED);
                t.send(player);

            }
        }
        for (int a = 0; a< players.size();a++){
            if(players.get(a).getLocation().add(0,2,0).getBlock().getType() != Material.ENDER_CHEST){
                players.remove(a);
            }
        }

        //Plus XP
        for (int i = 0; i < this.players_m.size(); i++) {
            Player player = this.players_m.get(i);
            int strlvl = Integer.parseInt(plugin.getConfig().get("gm4.xpStorage.player."+player.getUniqueId().toString()).toString());
            int lvl = player.getLevel();
            if(lvl>0) {
                lvl--;
                strlvl++;
                Title t = new Title("Bank Balance:"+ strlvl);
                t.setSubtitle("Powered by xpBank");
                t.setTitleColor(ChatColor.GREEN);
                t.send(player);
                player.setLevel(lvl);
                plugin.getConfig().set("gm4.xpStorage.player."+player.getUniqueId().toString(),strlvl);
            }
        }
        for (int a = 0; a< players_m.size();a++){
            if(players_m.get(a).getLocation().add(0,0,0).getBlock().getType() != Material.ENDER_CHEST){
                players_m.remove(a);
            }
        }
        plugin.saveConfig();
    }

}
