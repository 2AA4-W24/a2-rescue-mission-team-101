package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Initializer initializer;
    private Drone drone;
    private ResponseFactory responseFactory;

    @Override
    public void initialize(String s) {
        initializer = new Initializer(s);
        drone = initializer.assembleDrone();
        responseFactory = new ResponseFactory();
    }

    @Override
    public String takeDecision() {
        Command decision = drone.getNextMove();
        // it  transitions here
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        Response r = responseFactory.getResponse(drone.latestType(), s);
        drone.update(r);
        // want to transition here 
    }

    @Override
    public String deliverFinalReport() {
        String closest = drone.getClosestCreek();
        logger.info(closest);
        return closest;
    }

}
