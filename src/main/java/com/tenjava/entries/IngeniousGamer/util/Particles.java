package com.tenjava.entries.IngeniousGamer.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
 
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
 
public enum Particles {
    FIREWORKS_SPARK("fireworksSpark"),
    REDDUST("reddust");
    
    private String name;
 
    Particles(String name) {
        this.name = name;
    }

    public static void sendParticle(Particles effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
        Object packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player, packet);
        }
    } 
    public static Object createPacket(Particles effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
        if (count <= 0)
            count = 0;
        Object packet = getPacket();
        setValue(packet, "a", (String)effect.name);
        setValue(packet, "b", (float) location.getX());
        setValue(packet, "c", (float) location.getY());
        setValue(packet, "d", (float) location.getZ());
        setValue(packet, "e", (float)offsetX);
        setValue(packet, "f", (float)offsetY);
        setValue(packet, "g", (float)offsetZ);
        setValue(packet, "h", (float)speed);
        setValue(packet, "i", (int)count);
        return packet;
    }
    private static void setValue(Object instance, String fieldName, Object value) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
    private static String getPackageName() {
        return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
 
    private static Object getPacket() throws Exception {
        Class<?> packet = Class.forName(getPackageName() + ".PacketPlayOutWorldParticles");
        return packet.getConstructor().newInstance();
      }
 
    private static void sendPacket(Player p, Object packet) throws Exception {
        Method getHandle = p.getClass().getMethod("getHandle");
        Object nmsPlayer = getHandle.invoke(p);
        Field playerConnectionField = nmsPlayer.getClass().getField("playerConnection");
        Object playerConnection = playerConnectionField.get(nmsPlayer);
        for (Method m : playerConnection.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase("sendPacket")) {
                m.invoke(playerConnection, packet);
                return;
            }
        }
    }
}