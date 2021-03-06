package com.tenjava.entries.instipod.t3;

import com.tenjava.entries.instipod.t3.api.Utils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WeatherRunnable extends BukkitRunnable {
    private World world;
    
    public WeatherRunnable(World world) {
        this.world = world;
    }
    
    @Override
    public void run() {
        if (world.hasStorm()) {
            if (world.getPlayers().size() > 0) {
                int luckyplayer = Utils.getRandom(world.getPlayers().size());
                Player tostrike = world.getPlayers().get(luckyplayer);
                EventsCore.getInstance().debug("World " + world.getName() + ": Selected " + tostrike.getName() + " for random weather events.");
                EventRegistrar.getInstance().doStormEvents(tostrike);
            } else {
                EventsCore.getInstance().debug("There are no players in world " + world.getName() + ", so skipping task.");
            }
        } else {
            EventsCore.getInstance().debug("World " + world.getName() + " has stopped storming, so canceling task.");
            this.cancel();
        }
    }
}