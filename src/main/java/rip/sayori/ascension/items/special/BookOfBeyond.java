package rip.sayori.ascension.items.special;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber
public class BookOfBeyond extends Item {
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @SubscribeEvent
    public static void onAnvil(AnvilUpdateEvent e){
        if(!e.getLeft().getEnchantmentTagList().isEmpty() && e.getRight().getItem() instanceof BookOfBeyond){
            var res = e.getLeft().copy();
            var enchs = EnchantmentHelper.getEnchantments(res);
            var new_enchs = new HashMap<Enchantment, Integer>();
            AtomicInteger level = new AtomicInteger();
            enchs.forEach((k, v) -> {
                new_enchs.put(k,v+1);
                level.addAndGet(v);
            });
            EnchantmentHelper.setEnchantments(new_enchs, res);
            e.setOutput(res);
            e.setCost(Math.min(level.get(), 30));
            e.setMaterialCost(1);
        }
    }
}
