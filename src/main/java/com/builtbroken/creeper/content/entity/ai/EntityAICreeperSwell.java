package com.builtbroken.creeper.content.entity.ai;

import com.builtbroken.creeper.content.entity.EntityWNCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAICreeperSwell extends EntityAIBase
{
    /** The creeper that is swelling. */
    protected final EntityWNCreeper host;
    /** The creeper's attack target. This is used for the changing of the creeper's state. */
    EntityLivingBase creeperAttackTarget;

    public EntityAICreeperSwell(EntityWNCreeper entitycreeperIn)
    {
        this.host = entitycreeperIn;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.host.getAttackTarget();
        return this.host.getCreeperState() > 0 || entitylivingbase != null && this.host.getDistanceSq(entitylivingbase) < 9.0D;
    }

    @Override
    public void startExecuting()
    {
        this.host.getNavigator().clearPath();
        this.creeperAttackTarget = this.host.getAttackTarget();
    }

    @Override
    public void resetTask()
    {
        this.creeperAttackTarget = null;
    }

    @Override
    public void updateTask()
    {
        if (this.creeperAttackTarget == null)
        {
            this.host.setCreeperState(-1);
        }
        else if (this.host.getDistanceSq(this.creeperAttackTarget) > 49.0D)
        {
            this.host.setCreeperState(-1);
        }
        else if (!this.host.getEntitySenses().canSee(this.creeperAttackTarget))
        {
            this.host.setCreeperState(-1);
        }
        else
        {
            this.host.setCreeperState(1);
        }
    }
}