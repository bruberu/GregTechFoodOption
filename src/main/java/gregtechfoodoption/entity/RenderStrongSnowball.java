package gregtechfoodoption.entity;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;

public class RenderStrongSnowball extends RenderSnowball<EntityStrongSnowball> {
    public RenderStrongSnowball(RenderManager renderManagerIn, RenderItem itemRendererIn) {
        super(renderManagerIn, Items.SNOWBALL, itemRendererIn);
    }
}
