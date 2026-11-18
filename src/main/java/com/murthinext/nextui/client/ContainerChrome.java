package com.murthinext.nextui.client;

import com.murthinext.nextui.layout.PlayerInventoryLayout;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public final class ContainerChrome {
    public static final int DEFAULT_WIDTH = 176;
    public static final int DEFAULT_HEIGHT = 200;
    private static final int TITLE_X = 7;
    private static final int TITLE_Y = 9;
    private static final int DIVIDER_GAP = 3;

    private ContainerChrome() {}

    public static void drawBackground(
            GuiGraphics graphics,
            int x,
            int y,
            int width,
            int height,
            PlayerInventoryLayout inventory,
            GuiTheme theme) {
        GuiPanels.panel(graphics, x, y, width, height, inventory.y() - DIVIDER_GAP);
        GuiPanels.headerSeparator(graphics, x, y, width, theme.accent(), theme.accentShade());
        inventory.forEachSlot((index, slotX, slotY) -> GuiSlots.draw(graphics, x + slotX, y + slotY));
    }

    public static void drawTitle(GuiGraphics graphics, Font font, Component title, GuiTheme theme) {
        graphics.drawString(font, title, TITLE_X, TITLE_Y, theme.titleColor(), false);
    }
}
