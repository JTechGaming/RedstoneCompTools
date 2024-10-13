package me.jtech.redstonecomptools.client.clientAbilities;

import me.jtech.redstonecomptools.client.utility.Toaster;
import me.jtech.redstonecomptools.networking.SetItemPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

//TODO make this send a server packet, which sends a packet to all clients to render on all clients screens
public class SwapBlockTypeAbility extends BaseAbility{ //TODO comment this
    private boolean concrete = true;

    public SwapBlockTypeAbility(String identifier) {
        super("Swap Block Type", false, GLFW.GLFW_KEY_J, false, false, Identifier.of(identifier));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void toggled(boolean state) {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        PlayerInventory inventory = client.player.getInventory();
        ItemStack item = inventory.getMainHandStack();
        String type = item.getItem().toString();

        if (InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.GLFW_KEY_LEFT_SHIFT)) {
            concrete = !concrete;
            Toaster.sendToast(client, Text.literal("Swap Block"), Text.literal("Swapped to " + (concrete?"concrete":"wool") + " mode"));
            return;
        }

        if (type.contains("stained_glass")) {
            type = type.replace("stained_glass", concrete?"concrete":"wool");
        }

        if (type.contains("wool")) {
            type = type.replace("wool", "stained_glass");
        }

        if (type.contains("concrete")) {
            type = type.replace("concrete", "stained_glass");
        }

        ItemStack returnItem = Registries.ITEM.get(Identifier.ofVanilla(type)).getDefaultStack();
        returnItem.set(DataComponentTypes.CUSTOM_NAME, item.get(DataComponentTypes.CUSTOM_NAME));
        returnItem.set(DataComponentTypes.ITEM_NAME, item.get(DataComponentTypes.ITEM_NAME));

        ClientPlayNetworking.send(new SetItemPayload(returnItem, client.player.getInventory().selectedSlot));
    }

    @Override
    public void used() {

    }

    public void toGlass(String name, String remove, String add) {

    }
}
