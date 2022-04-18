package net.dbp.basic_ores.machine_api;

import org.jetbrains.annotations.Nullable;

import net.dbp.basic_ores.Basic;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestFurnace extends AbstractFurnaceBlock{
    public TestFurnace(Settings settings) {
        super(settings);
    }

    @Override @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TestFurnaceBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return TestFurnace.checkType(world, type, Basic.TEST_FURNACE_BLOCK_ENTITY);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TestFurnaceBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
}