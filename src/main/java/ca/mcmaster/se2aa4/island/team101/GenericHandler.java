package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public class GenericHandler implements ResponseHandler<GenericResponse> {
    @Override
    public GenericResponse handle(JSONObject response, JSONObject extras) {
        int cost = response.optInt("cost");
        String status = response.optString("status");
        return new GenericResponse(cost, status);
    }
}
