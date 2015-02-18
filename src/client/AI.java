package client;

import client.model.Block;
import client.model.Cell;
import common.model.Direction;
import common.util.Constants;
import common.util.ServerConstants;

import java.util.Random;

/**
 * AI class.
 * You should fill body of the method {@link #doTurn}.
 * Do not change name or modifiers of the methods or fields.
 * You can add as many methods or fields as you want!
 * You could use model to get information about current state of
 * the game.
 */
public class AI {

    public void doTurn(World world) {
        long start = System.currentTimeMillis();
        System.out.println(start);
        System.out.println(world.getMyCells().size());
        Random rnd = new Random();
        for(Cell c : world.getMyCells())
        {
            Block block = world.getMap().at(c.getPos());
            if(c.getEnergy() >= Constants.CELL_MIN_ENERGY_FOR_MITOSIS && block.getType().equals(Constants.BLOCK_TYPE_MITOSIS))
            {
                System.out.println("mitosis");
                System.out.println(c.getEnergy());
                c.mitosis();
            }
            else if(c.getEnergy() < Constants.CELL_MAX_ENERGY && block.getType().equals(Constants.BLOCK_TYPE_RESOURCE) && block.getResource() > 0)
            {
                c.gainResource();
            }
            else {
                c.move(Direction.values()[rnd.nextInt(6)]);
            }
        }
    }

}
