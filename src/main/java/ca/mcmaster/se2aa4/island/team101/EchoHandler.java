package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;

public class EchoHandler implements ResponseHandler<EchoResponse> {
    @Override
    public EchoResponse handle(JSONObject response, JSONObject extras) {
        int cost = response.optInt("cost");
        String status = response.optString("status");
        int range = extras.optInt("range", 0);
        String found = extras.optString("found", "Unknown");
        return new EchoResponse(cost, status, range, found);
    }
}