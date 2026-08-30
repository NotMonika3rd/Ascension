package rip.sayori.ascension;

import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import rip.sayori.ascension.looting.LootTableAccessor;
import rip.sayori.ascension.world.WorldUtils;

@Mod(modid = "ascension", useMetadata = true)
public class ModMain {
    static String[] classesEarly = {
            "rip.sayori.ascension.items.ModItems",
            "rip.sayori.ascension.looting.LootTables"
    };

    static {
        for (var i : classesEarly) {
            try {
                Class.forName(i);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        WorldUtils.preInit();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        for (var i : LootTableAccessor.accessors) LootTableList.register(i.getLocation());
    }
}
