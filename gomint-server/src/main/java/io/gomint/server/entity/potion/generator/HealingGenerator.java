/*
 * Copyright (c) 2018, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.gomint.server.entity.potion.generator;

import io.gomint.server.entity.potion.effect.Healing;
import io.gomint.server.player.EffectManager;

/**
 * @author generated
 * @version 2.0
 */
public class HealingGenerator implements EffectGenerator {

    public Healing generate( EffectManager manager, int amplifier, long lengthInMS ) {
        return new Healing( manager, amplifier, lengthInMS );
    }

}
