package gregtechfoodoption.entity;

import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderItalianBuffalo extends RenderLiving<EntityItalianBuffalo> {
    private static final ResourceLocation ITALIAN_BUFFALO_TEXTURES = new ResourceLocation(GregTechFoodOption.MODID, "textures/entity/italian_buffalo/italian_buffalo.png");

    public RenderItalianBuffalo(RenderManager manager) {
        super(manager, new ModelCow(), 0.7F);
    }

    protected ResourceLocation getEntityTexture(EntityItalianBuffalo entity) {
        return ITALIAN_BUFFALO_TEXTURES;
    }

}
