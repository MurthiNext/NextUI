package com.murthinext.nextui.client;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

public final class GuiTooltips {

    private GuiTooltips() {}

    public static boolean isKeyboardFocused(AbstractWidget widget) {
        return widget.isFocused() && Minecraft.getInstance().getLastInputType().isKeyboard();
    }

    public static boolean isTooltipTarget(AbstractWidget widget) {
        return widget.visible && (widget.isHovered() || isKeyboardFocused(widget));
    }

    public static void render(GuiGraphics graphics, Font font, List<Component> lines, int mouseX, int mouseY) {
        graphics.renderComponentTooltip(font, withHeader(lines), mouseX, mouseY);
    }

    public static List<Component> withHeader(List<Component> lines) {
        List<Component> styled = new ArrayList<>(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            Component line = lines.get(i);
            if (i == 0) {
                styled.add(line.copy().withStyle(ChatFormatting.WHITE));
            } else if (line.getStyle().getColor() == null) {
                styled.add(line.copy().withStyle(ChatFormatting.GRAY));
            } else {
                styled.add(line);
            }
        }
        return styled;
    }
}
