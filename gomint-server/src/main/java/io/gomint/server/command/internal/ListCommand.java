package io.gomint.server.command.internal;

import io.gomint.GoMint;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.annotation.*;
import io.gomint.command.validator.StringValidator;
import io.gomint.entity.Player;

import java.util.Collection;
import java.util.Map;

/**
 * @author geNAZt
 * @version 1.0
 */
@Name( "list" )
@Description( "List online players" )
@Permission( "gomint.commands.list" )
@Overload() // Empty overload for "/list"
@Overload( {
    @Parameter( name = "filter", validator = StringValidator.class, arguments = { "[A-Za-z0-9_]+" } )
} )     // Overload for "/list <filter>"
public class ListCommand extends Command {

    @Override
    public CommandOutput execute( Player player, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        Collection<Player> players = GoMint.instance().getPlayers();
        output.success( "§7[§aSYSTEM§7] §fCurrently online: §e%%s", players.size() );

        if ( arguments.containsKey( "filter" ) ) {
            // There is a filter present
            String filter = (String) arguments.get( "filter" );
            int i = 0;
            for ( Player player1 : players ) {
                if ( player1.getName().startsWith( filter ) ) {
                    if ( i++ > 20 ) {
                        output.success( "§7[§aSYSTEM§7] §f ... and more. Please use §e'/list <filter: string>'" );
                        break;
                    }

                    output.success( "§7[§aSYSTEM§7] §f%%s", player1.getName() );
                }
            }
        } else {
            int i = 0;
            for ( Player player1 : players ) {
                if ( i++ > 20 ) {
                    output.success( "§7[§aSYSTEM§7] §f ... and more. Please use §e'/list <filter: string>'" );
                    break;
                }

                output.success( "§7[§aSYSTEM§7] §f%%s", player1.getName() );
            }
        }

        return output;
    }

}
