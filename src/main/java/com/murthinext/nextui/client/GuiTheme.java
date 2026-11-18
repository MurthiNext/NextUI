package com.murthinext.nextui.client;

public record GuiTheme(int titleColor, int accent, int accentShade) {
    public static final GuiTheme DEFAULT =
            new GuiTheme(0xFF55FFFF, GuiPanels.COLOR_HIGHLIGHT, GuiPanels.COLOR_HIGHLIGHT_SHADE);

    public GuiTheme withTitleColor(int color) {
        return new GuiTheme(color, accent, accentShade);
    }
}
