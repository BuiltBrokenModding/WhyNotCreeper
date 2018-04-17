package com.builtbroken.creeper;

import com.builtbroken.creeper.config.ConfigSpawn;
import com.builtbroken.creeper.content.entity.EnumCreeperType;
import com.builtbroken.creeper.content.item.EnumDecoItems;
import com.builtbroken.creeper.content.item.ItemHeadDeco;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple mod to improve usability of the jukebox
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by StrikerRocker on 4/7/2018.
 */
@Mod(modid = CreeperMod.DOMAIN, name = CreeperMod.NAME, version = CreeperMod.VERSION)
@Mod.EventBusSubscriber()
public class CreeperMod
{
    public static final String DOMAIN = "whynotcreeper";
    public static final String NAME = "Why Not Creeper";
    public static final String PREFIX = DOMAIN + ":";

    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    @Mod.Instance(DOMAIN)
    public static CreeperMod INSTANCE;

    protected static Logger logger = LogManager.getLogger(DOMAIN);

    private static int nextEntityNetworkID = 0;

    private static int getNextEntityNetworkID()
    {
        return nextEntityNetworkID++;
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event)
    {
        for (EnumDecoItems decoItem : EnumDecoItems.values())
        {
            event.getRegistry().register(decoItem.item = new ItemHeadDeco(decoItem));
        }
    }

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityEntry> event)
    {
        //Collect biomes to spawn entities inside
        List<Biome> biomesList = get(ConfigSpawn.ADDITIONAL_BIOMES);
        List<Biome> removeList = get(ConfigSpawn.REMOVE_BIOMES);

        //Find biomes that are already supported by the vanilla creeper
        for (Biome biome : Biome.MUTATION_TO_BASE_ID_MAP)
        {
            if (biome != null && !removeList.contains(biome) && !biomesList.contains(biome))
            {
                List<Biome.SpawnListEntry> creatures = biome.getSpawnableList(EnumCreatureType.MONSTER);
                if (creatures != null)
                {
                    for (Biome.SpawnListEntry spawn : creatures)
                    {
                        if (spawn.entityClass == EntityCreeper.class)
                        {
                            biomesList.add(biome);
                            break;
                        }
                    }
                }
            }
        }

        //Convert to array
        Biome[] biomes = new Biome[biomesList.size()];
        for (int i = 0; i < biomesList.size(); i++)
        {
            biomes[i] = biomesList.get(i);
        }

        //Build entity data
        for (EnumCreeperType type : EnumCreeperType.values())
        {
            EntityEntryBuilder builder = buildEntity(type);

            //Enable spawns if config is enabled
            if (ConfigSpawn.SHOULD_SPAWN)
            {
                builder.spawn(EnumCreatureType.MONSTER, ConfigSpawn.SPAWN_WEIGHT, ConfigSpawn.SPAWN_MIN, ConfigSpawn.SPAWN_MAX, biomes);
            }

            //Register entity
            event.getRegistry().register(builder.build());
        }
    }

    public static EntityEntryBuilder buildEntity(EnumCreeperType type)
    {
        EntityEntryBuilder builder = buildEntity(type.clazz, type.id);
        builder.egg(Color.CYAN.getRGB(), Color.PINK.getRGB()); //TODO pull from enum
        return builder;
    }

    public static EntityEntryBuilder buildEntity(Class<? extends Entity> clazz, String id)
    {
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.name(PREFIX + id);
        builder.id(new ResourceLocation(DOMAIN, id), getNextEntityNetworkID());
        builder.tracker(128, 1, true);
        builder.entity(clazz);
        return builder;
    }

    private static List<Biome> get(String[] array)
    {
        //Build supported biome list
        List<Biome> biomesList = new ArrayList();
        for (String id : array)
        {
            Biome biome = Biome.REGISTRY.getObject(new ResourceLocation(id));
            if (biome != null)
            {
                biomesList.add(biome);
            }
            else
            {
                logger.error("CreeperMod#registerEntity() -> Failed to find a biome with id [" + id + "] while loading config data for entity registry");
            }
        }
        return biomesList;
    }
}
