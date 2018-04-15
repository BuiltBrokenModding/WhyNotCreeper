package com.builtbroken.creeper.entity.type;

import com.builtbroken.creeper.entity.EntityWNCreeper;
import com.builtbroken.creeper.entity.EnumCreeperType;
import net.minecraft.world.World;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/15/2018.
 */
public class EntityPinkCreeper extends EntityWNCreeper
{
    public EntityPinkCreeper(World worldIn)
    {
        super(worldIn, EnumCreeperType.PINK);
    }
}
