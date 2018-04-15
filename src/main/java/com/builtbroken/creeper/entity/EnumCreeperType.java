package com.builtbroken.creeper.entity;

import com.builtbroken.creeper.CreeperMod;
import com.builtbroken.creeper.entity.type.EntityColorizedCreeper;
import com.builtbroken.creeper.entity.type.EntityNormalCreeper;
import com.builtbroken.creeper.entity.type.EntityPinkCreeper;
import net.minecraft.util.ResourceLocation;

/**
 * Enum of types, with data for each type
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/15/2018.
 */
public enum EnumCreeperType
{
    /** Normal creeper, used as an unknown, or error state for null */
    NORMAL("normal", EntityNormalCreeper.class),
    /** Special creeper type that is pink and has a bow-tie */
    PINK("pink", EntityPinkCreeper.class),
    /** Creepers with randomized colors */
    COLORIZED("colorized", EntityColorizedCreeper.class);

    public final ResourceLocation TEXTURE;
    public final Class<? extends EntityWNCreeper> clazz;
    public final String name;
    public final String id;

    EnumCreeperType(String name, Class<? extends EntityWNCreeper> clazz)
    {
        this.name = name;
        this.id = "creeper." + name;
        this.clazz = clazz;
        TEXTURE = new ResourceLocation(CreeperMod.DOMAIN, "textures/entity/creeper/creeper." + name + ".png");
    }
}
