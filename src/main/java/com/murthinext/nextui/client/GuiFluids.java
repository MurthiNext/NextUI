package com.murthinext.nextui.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

public final class GuiFluids {
    private static final int TILE = 16;

    private GuiFluids() {}

    public static void drawIcon(GuiGraphics graphics, FluidStack fluid, int x, int y) {
        if (fluid.isEmpty()) {
            return;
        }
        var type = IClientFluidTypeExtensions.of(fluid.getFluid());
        var sprite = sprite(fluid, type);
        tint(graphics, type.getTintColor(fluid));
        try {
            graphics.blit(x, y, 0, TILE, TILE, sprite);
        } finally {
            graphics.setColor(1, 1, 1, 1);
        }
    }

    public static void drawTankFill(
            GuiGraphics graphics, FluidStack fluid, int x, int y, int width, int height, int capacity) {
        if (fluid.isEmpty() || capacity <= 0 || width <= 0 || height <= 0) {
            return;
        }
        int filled = (int) Math.min(height, Math.max(1L, (long) fluid.getAmount() * height / capacity));
        int bottom = y + height;
        int top = bottom - filled;
        var type = IClientFluidTypeExtensions.of(fluid.getFluid());
        var sprite = sprite(fluid, type);
        graphics.enableScissor(x, top, x + width, bottom);
        try {
            tint(graphics, type.getTintColor(fluid));
            for (int tileY = bottom - TILE; tileY + TILE > top; tileY -= TILE) {
                for (int tileX = x; tileX < x + width; tileX += TILE) {
                    graphics.blit(tileX, tileY, 0, TILE, TILE, sprite);
                }
            }
        } finally {
            graphics.disableScissor();
            graphics.setColor(1, 1, 1, 1);
        }
    }

    private static TextureAtlasSprite sprite(FluidStack fluid, IClientFluidTypeExtensions type) {
        return Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(type.getStillTexture(fluid));
    }

    private static void tint(GuiGraphics graphics, int color) {
        graphics.setColor(
                ((color >> 16) & 0xFF) / 255.0F,
                ((color >> 8) & 0xFF) / 255.0F,
                (color & 0xFF) / 255.0F,
                (color >>> 24) / 255.0F);
    }
}
