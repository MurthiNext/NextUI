package com.murthinext.nextui.client;

import java.util.function.BooleanSupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public final class OptionBox extends AbstractButton {
    public static final int TEXT_COLOR = 0xFFAAB8C6;
    private static final int TEXT_DISABLED = 0xFF7A8794;

    private final BooleanSupplier selected;
    private final Runnable action;
    private final Tooltip description;

    public OptionBox(
            int x,
            int y,
            int width,
            Component label,
            Component description,
            BooleanSupplier selected,
            Runnable action) {
        super(x, y, width, 14, label);
        this.selected = selected;
        this.action = action;
        this.description = Tooltip.create(description);
    }

    @Override
    public void onPress() {
        action.run();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int color = !active
                ? TEXT_DISABLED
                : isHoveredOrFocused() ? GuiTheme.DEFAULT.titleColor() : GuiTheme.DEFAULT.accent();
        int x = getX();
        int y = getY() + 2;
        graphics.fill(x, y, x + 10, y + 10, color);
        graphics.fill(x + 1, y + 1, x + 9, y + 9, GuiPanels.COLOR_BACKGROUND);
        if (selected.getAsBoolean()) {
            graphics.fill(x + 3, y + 3, x + 7, y + 7, color);
        }
        var font = Minecraft.getInstance().font;
        int textX = x + 16;
        int textY = getY() + 3;
        graphics.drawString(font, getMessage(), textX, textY, active ? TEXT_COLOR : TEXT_DISABLED, false);
        boolean textHovered =
                GuiExtraAreas.contains(mouseX, mouseY, textX, textY, font.width(getMessage()), font.lineHeight);
        setTooltip(textHovered || GuiTooltips.isKeyboardFocused(this) ? description : null);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
        output.add(
                NarratedElementType.HINT,
                Component.translatable(
                        "gui.nextui.option_state",
                        getMessage(),
                        Component.translatable(selected.getAsBoolean() ? "options.on" : "options.off")));
    }
}
