package com.builtbroken.creeper.content;

import com.builtbroken.creeper.CreeperMod;
import com.builtbroken.creeper.content.entity.EntityWNCreeper;
import com.builtbroken.creeper.content.entity.render.RenderWNCreeper;
import com.builtbroken.creeper.content.item.EnumDecoItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/17/2018.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = CreeperMod.DOMAIN)
public class ClientReg
{
    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityWNCreeper.class, manager -> new RenderWNCreeper(manager));
        for (EnumDecoItems entry : EnumDecoItems.values())
        {
            ModelLoader.setCustomModelResourceLocation(entry.item, 0, new ModelResourceLocation(entry.item.getRegistryName(), "inventory"));
        }
    }
}
