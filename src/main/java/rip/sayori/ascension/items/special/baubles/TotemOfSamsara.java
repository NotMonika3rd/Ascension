package rip.sayori.ascension.items.special.baubles;

import baubles.api.BaubleType;
import baubles.api.BaubleTypeEx;
import baubles.api.BaublesApi;
import baubles.api.cap.BaublesContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jspecify.annotations.Nullable;
import rip.sayori.ascension.items.ModItems;

import java.util.List;

import static rip.sayori.ascension.items.ItemUtils.compoundSafe;

@Mod.EventBusSubscriber
public class TotemOfSamsara extends BaubleItemBase {
    @Override
    public List<BaubleTypeEx> getTypes(ItemStack itemStack) {
        return List.of(BaubleType.CHARM.getExpansion());
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        var living = event.getEntityLiving();
        var world = living.world;
        int index = BaublesApi.getIndexInBaubles(living, ModItems.totemOfSamsara, 0);
        if(index != -1 && BaublesApi.getBaublesHandler(living) instanceof BaublesContainer container) {
            var item = compoundSafe(container.getStackInSlot(index));
            if(world.rand.nextInt(100) < Math.min(80, item.getTagCompound().getLong("count"))) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (BaublesApi.getBaublesHandler(living) instanceof BaublesContainer container) {
            int index = BaublesApi.getIndexInBaubles(living, ModItems.totemOfSamsara, 0);
            if (index != -1) {
                var tagCompound = compoundSafe(container.getStackInSlot(index)).getTagCompound();
                tagCompound.setLong("count", tagCompound.getLong("count") + 1);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(1, List.of(I18n.format("ascension.tooltip.totem_of_samsara", compoundSafe(stack).getTagCompound().getLong("count")).split("\\\\n")));
    }
}
