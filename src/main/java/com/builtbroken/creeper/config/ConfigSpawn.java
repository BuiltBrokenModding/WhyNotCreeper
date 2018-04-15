package com.builtbroken.creeper.config;

import com.builtbroken.creeper.CreeperMod;
import net.minecraftforge.common.config.Config;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/14/2018.
 */
@Config(modid = CreeperMod.DOMAIN, name = "whynotcreeper/spawning")
@Config.LangKey("config.merpig:spawning.title")
public class ConfigSpawn
{
    @Config.Name("should_spawn")
    @Config.Comment("Should the creeper spawn in the world")
    @Config.RequiresMcRestart
    public static boolean SHOULD_SPAWN = true;

    @Config.Name("spawn_weight")
    @Config.Comment("How likely the entity is to spawn. Higher the value the higher the spawn chance")
    @Config.RequiresMcRestart
    public static int SPAWN_WEIGHT = 100;

    @Config.Name("spawn_group_min")
    @Config.Comment("Smallest number to spawn in a group.")
    @Config.RequiresMcRestart
    public static int SPAWN_MIN = 4;

    @Config.Name("spawn_group_max")
    @Config.Comment("Largest number to spawn in a group.")
    @Config.RequiresMcRestart
    public static int SPAWN_MAX = 5;

    @Config.Name("additional_spawn_biomes")
    @Config.Comment("Additional biomes to spawn entities inside outside normal")
    @Config.RequiresMcRestart
    public static String[] ADDITIONAL_BIOMES = new String[0];

    @Config.Name("remove_spawn_biomes")
    @Config.Comment("Biomes to not spawn entities inside outside normal")
    @Config.RequiresMcRestart
    public static String[] REMOVE_BIOMES = new String[0];
}
