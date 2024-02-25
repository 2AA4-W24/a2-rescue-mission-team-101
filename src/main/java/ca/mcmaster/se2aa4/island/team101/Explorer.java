package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private JSONInitialization initializer;
    private TakeDecision m;
    private Drone drone;
    
    @Override
    public void initialize(String s) {
        logger.info(" Initializing the Exploration Command Center");
        initializer = new JSONInitialization(s);
        logger.info(initializer.toString());
        drone = new Drone(initializer);
        m = new TakeDecision(initializer.getObject());
        //logger.info("The drone is facing {}", initializer.getDirection());
        //logger.info("Battery level is {}", initializer.getBatteryLevel());
    }

    @Override
    public String takeDecision() {
        return m.choice().toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        /*
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");   
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        */
        JSONResponse response = new JSONResponse(s);
        drone.update(response);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
