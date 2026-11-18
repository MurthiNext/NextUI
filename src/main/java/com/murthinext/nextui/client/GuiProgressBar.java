package com.murthinext.nextui.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public record GuiProgressBar(
        ResourceLocation texture, int textureWidth, int textureHeight, Region track, Region fill, Direction direction) {
    public void draw(GuiGraphics graphics, int x, int y, float progress) {
        graphics.blit(texture, x, y, track.u(), track.v(), track.width(), track.height(), textureWidth, textureHeight);

        int fillX = x + (track.width() - fill.width()) / 2;
        int fillY = y + (track.height() - fill.height()) / 2;
        float ratio = Mth.clamp(progress, 0.0F, 1.0F);
        boolean vertical = direction == Direction.UP;
        int filled = Math.round(ratio * (vertical ? fill.height() : fill.width()));
        if (filled <= 0) {
            return;
        }
        int offset = vertical ? fill.height() - filled : 0;
        graphics.blit(
                texture,
                fillX,
                fillY + offset,
                fill.u(),
                fill.v() + offset,
                vertical ? fill.width() : filled,
                vertical ? filled : fill.height(),
                textureWidth,
                textureHeight);
    }

    public record Region(int u, int v, int width, int height) {}

    public enum Direction {
        UP,
        RIGHT
    }
}
