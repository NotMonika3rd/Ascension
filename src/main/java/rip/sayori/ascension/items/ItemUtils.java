package rip.sayori.ascension.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class ItemUtils {
    public static final List<Item> items = new ArrayList<>();

    public static CreativeTabs creativeTab;

    public static Item newItem(Item base, String name){
        Item item = base.setRegistryName("ascension", name).setTranslationKey("ascension." + name);
        items.add(item);
        return item;
    }

    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> e) {
        creativeTab = new CreativeTabs("ascension") {
            @Override
            public ItemStack createIcon() {
                return ModItems.bookOfBeyond.getDefaultInstance();
            }
        };
        for(var i : items) i.setCreativeTab(creativeTab);
        items.forEach(e.getRegistry()::register);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelReg(ModelRegistryEvent event) {
        for(var i : items) ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(Objects.requireNonNull(i.getRegistryName()), "inventory"));
    }

    public static ItemStack compoundSafe(ItemStack item){
        if(!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());
        return item;
    }
}
