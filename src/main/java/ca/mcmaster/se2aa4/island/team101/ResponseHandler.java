package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public interface ResponseHandler<T> {
    T handle(JSONObject response, JSONObject extras);
}