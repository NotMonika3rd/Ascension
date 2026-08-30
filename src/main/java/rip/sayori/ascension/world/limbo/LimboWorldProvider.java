package rip.sayori.ascension.world.limbo;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeProviderSingle;
import rip.sayori.ascension.world.WorldUtils;

public class LimboWorldProvider extends WorldProviderSurface {
    @Override
    protected void init() {
        super.init();
        biomeProvider = new BiomeProviderSingle(Biomes.OCEAN);
    }

    @Override
    public DimensionType getDimensionType() {
        return WorldUtils.LIMBO;
    }
}
