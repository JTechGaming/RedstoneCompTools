package me.jtech.redstonecomptools.client.clientAbilities;

import me.jtech.redstonecomptools.client.rendering.BlockOverlay;
import me.jtech.redstonecomptools.client.utility.RaycastingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class PingAbility extends BaseAbility{
    public PingAbility(String identifier) {
        super("Ping", false, GLFW.GLFW_KEY_K, false, true, Identifier.of(identifier));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void toggled(boolean state) { //TODO comment this
        // Adding an overlay
        MinecraftClient client = MinecraftClient.getInstance();

        BlockOverlay.addOverlay(RaycastingHelper.performRaycast(client), Color.RED);

        // Clearing all overlays
        //BlockOverlay.clearOverlays();
    }

    @Override
    public void used() {

    }
}