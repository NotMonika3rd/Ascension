package rip.sayori.ascension.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class ItemUtils {
    public static final List<Item> items = new ArrayList<>();

    public static CreativeTabs creativeTab;

    public static Item newItem(Item base, String name){
        return base.setRegistryName("ascension", name).setTranslationKey("ascension." + name);
    }

    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> e){
        creativeTab = new CreativeTabs("ascension") {
            @Override
            public ItemStack createIcon() {
                return ModItems.soulGem.getDefaultInstance();
            }
        };
        for(var i : items) i.setCreativeTab(creativeTab);
        items.forEach(e.getRegistry()::register);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelReg(ModelRegistryEvent event) {
        for(var i : items) ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
    }

    static {
        Arrays.stream(ModItems.class.getDeclaredFields()).iterator().forEachRemaining((field) -> {
            try {
                var f = field.get(null);
                if(f instanceof Item) items.add((Item) field.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
