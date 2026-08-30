package rip.sayori.ascension.world;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import rip.sayori.ascension.Configs;
import rip.sayori.ascension.world.limbo.LimboWorldProvider;

// WIP
@Mod.EventBusSubscriber
public class WorldUtils {
    public static final DimensionType LIMBO = DimensionType.register("limbo", "_limbo", Configs.dim_id_limbo, LimboWorldProvider.class, false);

    public static void preInit() {
        DimensionManager.registerDimension(Configs.dim_id_limbo, LIMBO);
    }
}
