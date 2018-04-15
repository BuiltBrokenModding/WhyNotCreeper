package com.builtbroken.creeper.entity.render;

import com.builtbroken.creeper.entity.EntityWNCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Copy of {@link net.minecraft.client.renderer.entity.RenderCreeper} to change entity and allow customization
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/14/2018.
 */
@Mod.EventBusSubscriber
public class RenderWNCreeper extends RenderLiving<EntityWNCreeper>
{
    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");

    public RenderWNCreeper(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCreeper(), 0.5F);
        this.addLayer(new LayerWNCreeperCharge(this));
    }

    @Override
    protected void preRenderCallback(EntityWNCreeper entity, float partialTickTime)
    {
        float f = entity.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }

    @Override
    protected int getColorMultiplier(EntityWNCreeper entity, float lightBrightness, float partialTickTime)
    {
        float f = entity.getCreeperFlashIntensity(partialTickTime);

        if ((int) (f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int) (f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWNCreeper entity)
    {
        return CREEPER_TEXTURES;
    }

    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityWNCreeper.class, manager -> new RenderWNCreeper(manager));
    }
}