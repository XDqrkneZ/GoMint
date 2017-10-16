package io.gomint.plugin;

import io.gomint.plugin.command.CommandVelocity;
import io.gomint.plugin.listener.PlayerJoinListener;
import io.gomint.plugin.listener.PlayerMoveListener;

/**
 * @author geNAZt
 * @version 1.0
 */
@Name( "TestPlugin" )
@Version( major = 1, minor = 0 )
public class TestPlugin extends Plugin {

    @Override
    public void onStartup() {
        getLogger().info( "Started " + this.getName() + " " + this.getVersion() );
    }

    @Override
    public void onInstall() {
        // Register listener
        registerListener( new PlayerMoveListener() );
        registerListener( new PlayerJoinListener() );

        // Register commands
        registerCommand( new CommandVelocity() );
    }

    @Override
    public void onUninstall() {
        // This is like Bukkits onDisable();
    }

}
