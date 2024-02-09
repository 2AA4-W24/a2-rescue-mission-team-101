package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TakeDecision{
    private final Logger logger = LogManager.getLogger();

    private JSONObject decision;
    private String choice = "fly";
    public TakeDecision(JSONObject j){
        decision = j;
    }

    public String choice(){
        decision.put("action", choice); // we stop the exploration immediately
        logger.info("** Decision: {}",decision.toString());
        logger.info("this is json" + decision.toString());
        return decision.toString();
    }

    public void setChoice(String choice){
        this.choice = choice;
    }
}
