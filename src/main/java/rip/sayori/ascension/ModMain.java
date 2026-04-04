package rip.sayori.ascension;

import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import rip.sayori.ascension.looting.LootTableAccessor;

@Mod(modid = "ascension", useMetadata = true)
public class ModMain {
    static String[] classesEarly = {
            "rip.sayori.ascension.items.ModItems",
            "rip.sayori.ascension.looting.LootTables"
    };

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        for(var i : LootTableAccessor.accessors) LootTableList.register(i.getLocation());
    }

    static {
        for(var i : classesEarly) {
            try {
                Class.forName(i);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
