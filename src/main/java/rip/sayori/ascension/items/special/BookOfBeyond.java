package rip.sayori.ascension.items.special;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static rip.sayori.ascension.items.ItemUtils.compoundSafe;

@Mod.EventBusSubscriber
public class BookOfBeyond extends Item {
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(1, List.of(I18n.format("ascension.tooltip.book_of_beyond").split("\\\\n")));
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
            e.setCost(level.get());
            e.setMaterialCost(1);
        }
    }
}
