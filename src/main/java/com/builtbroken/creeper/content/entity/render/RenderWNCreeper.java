package com.builtbroken.creeper.content.entity.render;

import com.builtbroken.creeper.content.entity.EntityWNCreeper;
import com.builtbroken.creeper.content.item.ItemHeadDeco;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

/**
 * Copy of {@link net.minecraft.client.renderer.entity.RenderCreeper} to change entity and allow customization
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/14/2018.
 */
@Mod.EventBusSubscriber
public class RenderWNCreeper extends RenderLiving<EntityWNCreeper>
{
    private EntityItem entityItem;
    private RenderEntityItem2 renderEntityItem;

    public RenderWNCreeper(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCreeper(), 0.5F);
        this.addLayer(new LayerWNCreeperCharge(this));
        entityItem = new EntityItem(null);
        renderEntityItem = new RenderEntityItem2(renderManager, Minecraft.getMinecraft().getRenderItem());
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
    public void doRender(EntityWNCreeper entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        //entity.rotationYaw = entity.prevRotationYaw = 0;
        //entity.rotationYawHead = entity.prevRotationYawHead = 50;
        //entity.rotationPitch = entity.prevRotationPitch = 0;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);


        ItemStack stack = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        stack = new ItemStack(Blocks.FURNACE);
        if (!stack.isEmpty())
        {
            if (stack.getItem() instanceof ItemBlock)
            {
                renderBlockOnHead(entity, stack, x, y, z, entityYaw, partialTicks);
            }
            else if (stack.getItem() instanceof ItemHeadDeco)
            {
                renderDecoItem(entity, stack, x, y, z, entityYaw, partialTicks);
            }
            else
            {
                renderDecoItem(entity, stack, x, y, z, entityYaw, partialTicks);
            }
        }
    }

    protected void renderBlockOnHead(EntityWNCreeper entity, ItemStack stack, double x, double y, double z, float entityYaw, float partialTicks)
    {
        Block block = Block.getBlockFromItem(stack.getItem());
        if (block != null)
        {
            IBlockState blockState = block.getDefaultState();

            if (block.getRenderType(blockState) == EnumBlockRenderType.MODEL)
            {
                float itemRenderScale = 0.4f;

                float yaw = 180 - this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
                float pitch = -this.interpolateRotation(entity.prevRotationPitch, entity.rotationPitch, partialTicks);

                BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                GlStateManager.enableRescaleNormal();
                GlStateManager.pushMatrix();

                //Move to rotation point
                GlStateManager.translate(x, y + 1.13, z);

                //Rotate
                GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90, 0.0F, 1.0F, 0.0F);

                //Move object to center
                GlStateManager.translate(-0.5 * itemRenderScale, 0, 0.5 * itemRenderScale);

                //Move object to render position
                GlStateManager.translate(0, 0.48, 0);


                GlStateManager.scale(itemRenderScale, itemRenderScale, itemRenderScale);
                int i = entity.getBrightnessForRender();
                int j = i % 65536;
                int k = i / 65536;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                blockrendererdispatcher.renderBlockBrightness(blockState, 1.0F);
                GlStateManager.popMatrix();
                GlStateManager.disableRescaleNormal();
            }
        }
    }

    protected void renderDecoItem(EntityWNCreeper entity, ItemStack stack, double x, double y, double z, float entityYaw, float partialTicks)
    {
        float itemRenderScale = 0.5f;

        float yaw = 180 - this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
        float pitch = -this.interpolateRotation(entity.prevRotationPitch, entity.rotationPitch, partialTicks);

        //GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();

        //Move to rotation point
        GlStateManager.translate(x, y + 1.13, z);

        //Rotate
        GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);

        //Move object to center
        GlStateManager.translate(-0.5 * itemRenderScale, 0, 0.5 * itemRenderScale - 0.5 * itemRenderScale);

        //Move object to render position
        GlStateManager.translate(-0.25, 0.4, -0.25);

        GlStateManager.scale(itemRenderScale, itemRenderScale, itemRenderScale);
        renderItem(stack, entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, entityYaw, partialTicks);

        GlStateManager.popMatrix();
        //GlStateManager.disableRescaleNormal();
    }


    public void renderItem(ItemStack stack, World world, double wx, double wy, double wz,
                           double x, double y, double z, float entityYaw, float partialTicks)
    {
        //Set data for fake entity
        entityItem.setWorld(world);
        entityItem.rotationYaw = 0;
        entityItem.setPosition(wx, wy, wz);
        entityItem.setItem(stack);

        //render entity item
        renderEntityItem.doRender(entityItem, x, y, z, entityYaw, partialTicks);
    }


    @Override
    protected void renderModel(EntityWNCreeper entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        boolean flag = this.isVisible(entitylivingbaseIn);
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1)
        {
            if (!this.bindEntityTexture(entitylivingbaseIn))
            {
                return;
            }

            if (flag1)
            {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }

            GlStateManager.color(Color.PINK.getRed() / 255f, Color.PINK.getGreen() / 255f, Color.PINK.getBlue() / 255f);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            GlStateManager.color(1, 1, 1, 1);

            if (flag1)
            {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

    @Override
    protected int getColorMultiplier(EntityWNCreeper entity, float lightBrightness, float partialTickTime)
    {
        //This code seems to only change the brightness of the texture and not color
        final int color = 822083583;
        float f = entity.getCreeperFlashIntensity(partialTickTime);

        if ((int) (f * 10.0F) % 2 == 0)
        {
            return 0; //Flash to white
        }
        else
        {
            int i = (int) (f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | color;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWNCreeper entity)
    {
        return entity.type.TEXTURE;
    }

    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityWNCreeper.class, manager -> new RenderWNCreeper(manager));
    }
}