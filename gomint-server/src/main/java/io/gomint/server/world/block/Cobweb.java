package io.gomint.server.world.block;

import io.gomint.inventory.item.*;
import io.gomint.server.registry.RegisterInfo;
import io.gomint.world.block.BlockCobweb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 30 )
public class Cobweb extends Block implements BlockCobweb {

    @Override
    public int getBlockId() {
        return 30;
    }

    @Override
    public long getBreakTime() {
        return 6000;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Class<? extends ItemStack>[] getToolInterfaces() {
        return new Class[]{
            ItemDiamondSword.class,
            ItemStoneSword.class,
            ItemGoldenSword.class,
            ItemIronSword.class,
            ItemWoodenSword.class,
        };
    }

    @Override
    public List<ItemStack> getDrops( ItemStack itemInHand ) {
        if ( isCorrectTool( itemInHand ) ) {
            return new ArrayList<ItemStack>() {{
                add( ItemString.create( 1 ) );
            }};
        }

        return new ArrayList<>();
    }

    @Override
    public float getBlastResistance() {
        return 20.0f;
    }

}