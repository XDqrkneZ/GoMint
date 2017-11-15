package io.gomint.server.world.block;

import io.gomint.server.registry.RegisterInfo;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 137 )
public class CommandBlock extends Block {

    @Override
    public int getBlockId() {
        return 137;
    }

    @Override
    public float getBlastResistance() {
        return 1.8E7f;
    }

}
