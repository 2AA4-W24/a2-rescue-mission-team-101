package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TakeDecision{
    private final Logger logger = LogManager.getLogger();
    private JSONObject decision;
    private String choice = "fly";
    private int counter = 0;
    public TakeDecision(JSONObject j){
        decision = j;
    }

    public JSONObject choice(){
        if (counter > 50){
            decision.put("action", "stop"); 
        } else{
            decision.put("action", "fly"); 
        }
        logger.info("** Decision: {}",decision.toString());
        counter +=1;
        return decision;
    }

    public void setChoice(String choice){
        this.choice = choice;
    }
}