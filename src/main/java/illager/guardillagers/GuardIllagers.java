package illager.guardillagers;

import illager.guardillagers.client.IllagerEntityRender;
import illager.guardillagers.event.EntityEventHandler;
import illager.guardillagers.init.IllagerEntityRegistry;
import illager.guardillagers.init.IllagerItems;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = GuardIllagers.MODID, name = GuardIllagers.MODNAME, version = GuardIllagers.VERSION, dependencies = "required-after:forge@[14.23.5.2768,);")
public class GuardIllagers {
    public static final String MODID = "guardillagers";
    public static final String VERSION = "1.0.2";
    public static final String MODNAME = "GuardIllagers";

    public static final ResourceLocation LOOT_TABLE = LootTableList.register(new ResourceLocation(GuardIllagers.MODID, "entity/guard_illager"));

    @Mod.Instance
    public static GuardIllagers instance;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        IllagerItems.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        IllagerItems.registerModels();
    }

    @SubscribeEvent
    public void registerEntityEntries(RegistryEvent.Register<EntityEntry> event) {
        IllagerEntityRegistry.registerEntity(event);
    }


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide().isClient()) {
            IllagerEntityRender.entityRender();
        }
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
