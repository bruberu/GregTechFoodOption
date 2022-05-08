package gregtechfoodoption.client;

import gregtech.api.GTValues;
import gregtech.api.gui.resources.TextureArea;

public class GTFOGuiTextures {
    public static final TextureArea PROGRESS_BAR_SLICER = TextureArea.fullImage("textures/gui/progress_bar/progress_bar_slicer.png");
    public static final TextureArea SLICER_INPUT_OVERLAY = TextureArea.fullImage("textures/gui/overlay/chopping_block_overlay.png");
    public static final TextureArea SLICER_CUTTER_OVERLAY = TextureArea.fullImage("textures/gui/overlay/slicer_container_overlay.png");
    public static final TextureArea SLICER_OUTPUT_OVERLAY = TextureArea.fullImage("textures/gui/overlay/sliced_matter_overlay.png");
    public static final TextureArea PRIMITIVE_FOOD_OVERLAY = TextureArea.fullImage("textures/gui/overlay/food_overlay.png");
    public static final TextureArea BUTTON_MOB_SORTER_MODE = GTValues.FOOLS.get() ? TextureArea.fullImage("textures/gui/widget/button_mob_sorter_mode_special.png") : TextureArea.fullImage("textures/gui/widget/button_mob_sorter_mode.png");

}
