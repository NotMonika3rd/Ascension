package rip.sayori.ascension.looting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public record LootTableAccessor(String name) {
    public ResourceLocation getLocation(){
        return new ResourceLocation("ascension", name);
    }
    public List<ItemStack> get(EntityPlayer player){
        WorldServer world = (WorldServer) player.world;
        return world.getLootTableManager().getLootTableFromLocation(getLocation()).generateLootForPools(world.rand, new LootContext.Builder(world).withPlayer(player).build());
    }

    public static List<LootTableAccessor> accessors = new ArrayList<>();
    public static LootTableAccessor newAccessor(String name){
        var res = new LootTableAccessor(name);
        accessors.add(res);
        return res;
    }

}
