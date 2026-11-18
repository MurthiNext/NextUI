package com.murthinext.nextui.client;

import java.util.List;
import net.minecraft.client.renderer.Rect2i;

public interface GuiExtraAreas {
    List<Rect2i> getGuiExtraAreas();

    default boolean isInExtraArea(double mouseX, double mouseY) {
        return getGuiExtraAreas().stream().anyMatch(area -> contains(area, mouseX, mouseY));
    }

    static boolean contains(Rect2i area, double mouseX, double mouseY) {
        return contains(mouseX, mouseY, area.getX(), area.getY(), area.getWidth(), area.getHeight());
    }

    static boolean contains(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
