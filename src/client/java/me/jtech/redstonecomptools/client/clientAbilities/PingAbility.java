package me.jtech.redstonecomptools.client.clientAbilities;

import me.jtech.redstonecomptools.client.rendering.BlockOverlayRenderer;
import me.jtech.redstonecomptools.client.utility.RaycastingHelper;
import me.jtech.redstonecomptools.client.utility.Toaster;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

//TODO comment this
//TODO make this send a server packet, which sends a packet to all clients to render on all clients screens
public class PingAbility extends BaseAbility{
    private boolean processedShift;
    public PingAbility(String identifier) {
        super("Ping", false, GLFW.GLFW_KEY_K, false, true, Identifier.of("redstonecomptools", identifier));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void toggled(boolean state) { //TODO comment this
        // Adding an overlay
        MinecraftClient client = MinecraftClient.getInstance();

        if (InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.GLFW_KEY_LEFT_SHIFT) && !processedShift) {
            processedShift = true;
            BlockOverlayRenderer.clearOverlays();
            Toaster.sendToast(client, Text.literal("Ping"), Text.literal("Removed all pings"));
            return;
        } else {
            processedShift = false;
        }

        BlockOverlayRenderer.addOverlay(RaycastingHelper.performRaycast(client), Color.RED);

        // Clearing all overlays
        //BlockOverlay.clearOverlays();
    }

    @Override
    public void used() {

    }
}
