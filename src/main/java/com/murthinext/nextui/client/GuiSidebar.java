package com.murthinext.nextui.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;

public record GuiSidebar(int x, int y, int rows) {
    private static final int STEP = IconButton.SIZE + 2;
    private static final int BORDER = 0xFF39424E;

    public Rect2i bounds() {
        return new Rect2i(x, y, STEP, height());
    }

    public int cellX() {
        return x + 1;
    }

    public int cellY(int row) {
        return y + 1 + row * STEP;
    }

    public void draw(GuiGraphics graphics) {
        int right = x + STEP;
        int bottom = y + height();
        graphics.fill(x, y, right, bottom, BORDER);
        graphics.fill(x + 1, y + 1, right - 1, bottom - 1, GuiPanels.COLOR_BACKGROUND);
    }

    private int height() {
        return rows * STEP + 2;
    }
}
