package com.murthinext.nextui.client;

import net.minecraft.client.gui.GuiGraphics;

public final class GuiPanels {
    public static final int COLOR_BACKGROUND = 0xFF101418;
    public static final int COLOR_BORDER = 0xFF5A7A9A;
    public static final int COLOR_HIGHLIGHT = 0xFF8ABBEF;
    public static final int COLOR_HIGHLIGHT_SHADE = 0x80294057;
    private static final int COLOR_POPUP = 0xFF171D24;
    private static final int COLOR_SEPARATOR = 0xFF2C3A46;

    private GuiPanels() {}

    public static void panel(GuiGraphics graphics, int x, int y, int width, int height, int dividerY) {
        frame(graphics, x, y, width, height, COLOR_BACKGROUND);
        if (dividerY > 0 && dividerY < height - 1) {
            graphics.fill(x + 1, y + dividerY, x + width - 1, y + dividerY + 1, COLOR_BORDER);
        }
    }

    public static void popup(GuiGraphics graphics, int x, int y, int width, int height) {
        frame(graphics, x, y, width, height, COLOR_POPUP);
        graphics.fill(x + 1, y + 1, x + width - 1, y + 2, COLOR_HIGHLIGHT);
    }

    public static void headerSeparator(GuiGraphics graphics, int x, int y, int width, int accent, int accentShade) {
        int left = x + 7;
        int top = y + 21;
        int accentEnd = left + 12;
        graphics.fill(left, top, x + width - 8, top + 1, COLOR_SEPARATOR);
        graphics.fill(left, top, accentEnd, top + 1, accent);
        graphics.fill(left, top + 1, accentEnd, top + 2, accentShade);
    }

    private static void frame(GuiGraphics graphics, int x, int y, int width, int height, int background) {
        graphics.fill(x, y, x + width, y + height, COLOR_BORDER);
        graphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, background);
    }
}
