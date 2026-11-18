package com.murthinext.nextui.client;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class IconButton extends AbstractButton {
    public static final int SIZE = 18;

    private final Skin skin;
    private final Supplier<ResourceLocation> icon;
    private final BooleanSupplier selected;
    private final Runnable action;

    public IconButton(
            int x,
            int y,
            Component label,
            Skin skin,
            Supplier<ResourceLocation> icon,
            BooleanSupplier selected,
            Runnable action) {
        super(x, y, SIZE, SIZE, label);
        this.skin = skin;
        this.icon = icon;
        this.selected = selected;
        this.action = action;
    }

    @Override
    public void onPress() {
        action.run();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int x = getX();
        int y = getY();
        skin.drawFrame(graphics, x, y, active && GuiTooltips.isTooltipTarget(this), selected.getAsBoolean());
        drawTexture(graphics, icon.get(), x, y);
        if (!active) {
            graphics.fill(x, y, x + SIZE, y + SIZE, 0x80101418);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }

    public static void drawTexture(GuiGraphics graphics, ResourceLocation texture, int x, int y) {
        graphics.blit(texture, x, y, 0, 0.0F, 0.0F, SIZE, SIZE, SIZE, SIZE);
    }

    public record Skin(ResourceLocation normal, ResourceLocation hover, ResourceLocation selected) {
        public void drawFrame(GuiGraphics graphics, int x, int y, boolean highlighted, boolean current) {
            drawTexture(graphics, current ? selected : highlighted ? hover : normal, x, y);
        }
    }
}
