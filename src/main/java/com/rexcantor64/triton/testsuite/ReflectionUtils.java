package com.rexcantor64.triton.testsuite;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {


    public static void sendPacket(Player p, Object packet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
        Object plrConnection = nmsPlayer.getClass().getField(getMajorMcVersion() >= 17 ? "b" : "playerConnection").get(nmsPlayer);
        plrConnection.getClass().getMethod("sendPacket", getMajorMcVersion() >= 17 ?
                getClass("net.minecraft.network.protocol.Packet") : getNmsClass("Packet")
        ).invoke(plrConnection, packet);
    }

    public static int getMajorMcVersion() {
        // org.bukkit.craftbukkit.v1_17_R1 for example
        return Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].split("_")[1]);
    }

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return getClass("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

}
