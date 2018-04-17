package com.builtbroken.creeper.content.item;

import com.builtbroken.creeper.CreeperMod;
import net.minecraft.item.Item;

/**
 * Item used to represent decorations that can be placed on an entity head
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/16/2018.
 */
public class ItemHeadDeco extends Item
{
    public ItemHeadDeco(String name)
    {
        this.setRegistryName(CreeperMod.DOMAIN, "head.deco." + name);
    }

    //TODO maybe change entity type based on right clicking with item?
    //Ex: Bow tie + creeper = pink creeper or colorized creeper of color pink + bow-tie
}
