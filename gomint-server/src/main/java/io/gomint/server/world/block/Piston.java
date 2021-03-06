package io.gomint.server.world.block;

import io.gomint.world.block.BlockType;

import io.gomint.math.BlockPosition;
import io.gomint.server.entity.tileentity.PistonArmTileEntity;
import io.gomint.server.entity.tileentity.TileEntity;
import io.gomint.server.registry.RegisterInfo;
import io.gomint.taglib.NBTTagCompound;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 33 )
public class Piston extends Block implements io.gomint.world.block.BlockPiston {

    @Override
    public int getBlockId() {
        return 33;
    }

    @Override
    public long getBreakTime() {
        return 750;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }


    @Override
    public boolean needsTileEntity() {
        return true;
    }

    @Override
    TileEntity createTileEntity( NBTTagCompound compound ) {
        super.createTileEntity( compound );
        return new PistonArmTileEntity( compound, this.world );
    }

    @Override
    public float getBlastResistance() {
        return 2.5f;
    }

    @Override
    public BlockType getType() {
        return BlockType.PISTON;
    }

    @Override
    public boolean canBeBrokenWithHand() {
        return true;
    }

}
