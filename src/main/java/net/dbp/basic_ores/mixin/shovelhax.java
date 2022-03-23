package net.dbp.basic_ores.mixin;

import java.util.Map;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.block.*;
import net.minecraft.item.ShovelItem;

@Mixin(ShovelItem.class)
public interface shovelhax {
    @Accessor("PATH_STATES") static Map<Block, BlockState> getPathed() { throw new AssertionError(); }
}
