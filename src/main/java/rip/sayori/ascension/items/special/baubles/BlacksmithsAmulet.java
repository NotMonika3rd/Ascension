package rip.sayori.ascension.items.special.baubles;

import baubles.api.BaubleType;
import baubles.api.BaubleTypeEx;
import baubles.api.BaublesApi;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jspecify.annotations.Nullable;
import rip.sayori.ascension.items.ModItems;

import java.util.List;

@Mod.EventBusSubscriber
public class BlacksmithsAmulet extends BaubleItemBase{
    @Override
    public List<BaubleTypeEx> getTypes(ItemStack itemStack) {
        return List.of(BaubleType.AMULET.getExpansion(), BaubleType.CHARM.getExpansion());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(1, List.of(I18n.format("ascension.tooltip.blacksmiths_amulet").split("\\\\n")));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAnvil(AnvilUpdateEvent e){
        if(BaublesApi.isBaubleEquipped((EntityLivingBase) e.getPlayer(), ModItems.blacksmithsAmulet))
            e.setCost(Math.min((int) Math.ceil(e.getCost() * 0.9), 30));
    }
}
