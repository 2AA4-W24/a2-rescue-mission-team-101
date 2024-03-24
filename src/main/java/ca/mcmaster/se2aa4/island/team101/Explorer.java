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
        logger.info(" Initializing the Exploration Command Center");
        initializer = Initializer.getInstance(s); // Use singleton instance of initializer
        logger.info(initializer.toString());
        drone = initializer.assembleDrone();
        responseFactory = new ResponseFactory();
    }

    @Override
    public String takeDecision() {
        Command decision = drone.getNextMove();
        logger.info(decision);
        // it  transitions here
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        Response r = responseFactory.getResponse(drone.latestType(), s);
        logger.info("** Response received:\n"+r.toString());
        drone.update(r);
        // want to transition here 
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
