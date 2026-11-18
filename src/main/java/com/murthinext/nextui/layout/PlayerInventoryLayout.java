package com.murthinext.nextui.layout;

public record PlayerInventoryLayout(int x, int y, int hotbarY) {
    private static final int COLUMNS = 9;
    private static final int ROWS = 3;
    public static final int SLOT_SIZE = 18;
    public static final int MAIN_SLOTS = COLUMNS * ROWS;
    public static final int SLOT_COUNT = MAIN_SLOTS + COLUMNS;
    public static final PlayerInventoryLayout STANDARD = new PlayerInventoryLayout(8, 122, 180);

    public void forEachSlot(SlotPosition consumer) {
        for (int i = 0; i < SLOT_COUNT; i++) {
            boolean hotbar = i >= MAIN_SLOTS;
            int index = hotbar ? i - MAIN_SLOTS : i + COLUMNS;
            int slotX = x + i % COLUMNS * SLOT_SIZE;
            int slotY = hotbar ? hotbarY : y + i / COLUMNS * SLOT_SIZE;
            consumer.accept(index, slotX, slotY);
        }
    }

    @FunctionalInterface
    public interface SlotPosition {
        void accept(int inventoryIndex, int x, int y);
    }
}
