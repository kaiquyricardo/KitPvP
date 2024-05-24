package com.github.kaiquy.kitpvp.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Setter
@Getter
@AllArgsConstructor
public class SimpleLocation {

    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    private final String world;

    public static SimpleLocation fromString(String string) {
        String[] split = string.split(";");

        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);
        double z = Double.parseDouble(split[2]);
        float yaw = Float.parseFloat(split[3]);
        float pitch = Float.parseFloat(split[4]);
        String world = split[5];

        return new SimpleLocation(x, y, z, yaw, pitch, world);
    }

    public static SimpleLocation fromLocation(Location location) {
        return new SimpleLocation(
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch(),
                location.getWorld().getName()
        );
    }

    public Location toLocation() {
        return new Location(
                Bukkit.getWorld(world),
                x, y, z, yaw, pitch
        );
    }

    public String toString() {
        return x + ";" + y + ";" + z + ";" + yaw + ";" + pitch + ";" + world;
    }
}
