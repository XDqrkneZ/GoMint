package io.gomint.server.world.block;

import io.gomint.world.block.BlockType;

import io.gomint.inventory.item.*;
import io.gomint.server.registry.RegisterInfo;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 125 )
public class Dropper extends Block implements io.gomint.world.block.BlockDropper {

    @Override
    public int getBlockId() {
        return 125;
    }

    @Override
    public long getBreakTime() {
        return 5250;
    }

    @Override
    public float getBlastResistance() {
        return 17.5f;
    }

    @Override
    public Class<? extends ItemStack>[] getToolInterfaces() {
        return new Class[]{
            ItemWoodenPickaxe.class,
            ItemStonePickaxe.class,
            ItemGoldenPickaxe.class,
            ItemIronPickaxe.class,
            ItemDiamondPickaxe.class
        };
    }

    @Override
    public BlockType getType() {
        return BlockType.DROPPER;
    }

    @Override
    public boolean canBeBrokenWithHand() {
        return true;
    }

}
