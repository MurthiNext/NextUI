package com.murthinext.nextui.client;

import com.murthinext.nextui.NextUi;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public final class GuiSlots {
    public static final int SIZE = 18;
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NextUi.MOD_ID, "textures/gui/slot.png");

    private GuiSlots() {}

    public static void draw(GuiGraphics graphics, int itemX, int itemY) {
        graphics.blit(TEXTURE, itemX - 1, itemY - 1, 0, 0.0F, 0.0F, SIZE, SIZE, SIZE, SIZE);
    }
}
