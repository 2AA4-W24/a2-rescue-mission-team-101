package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private JSONInitialization initializer;
    private AirDecision ad;
    private Drone drone;
    String decisionType;
    
    @Override
    public void initialize(String s) {
        logger.info(" Initializing the Exploration Command Center");
        initializer = new JSONInitialization(s);
        logger.info(initializer.toString());
        drone = new Drone(initializer);
        ad = new AirDecision(drone);
        //logger.info("The drone is facing {}", initializer.getDirection());
        //logger.info("Battery level is {}", initializer.getBatteryLevel());
    }

    @Override
    public String takeDecision() {
        String decision = ad.decide();
        decisionType = ad.getType();
        return decision;
    }

    @Override
    public void acknowledgeResults(String s) {
        Response response = new Response(decisionType, s);
        GenericResponse r = response.handleResponse();
        logger.info("** Response received:\n"+response.toString());
        logger.info("The cost of the action was {}", r.getCost());
        //logger.info("Additional information received: {}", response.getExtraInfo());
        drone.update(response);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
