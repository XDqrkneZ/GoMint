package io.gomint.server.world.block;

import io.gomint.inventory.item.*;
import io.gomint.server.registry.RegisterInfo;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 24 )
public class Sandstone extends Block {

    @Override
    public int getBlockId() {
        return 24;
    }

    @Override
    public long getBreakTime() {
        return 1200;
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
    public float getBlastResistance() {
        return 4.0f;
    }

}