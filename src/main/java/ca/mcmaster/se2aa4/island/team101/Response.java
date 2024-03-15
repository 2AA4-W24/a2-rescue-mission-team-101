package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static ca.mcmaster.se2aa4.island.team101.CommandStrings.*;

public class Response<T> {
    private JSONObject response;
    private JSONObject extras;
    private String cmdType;

    private Map<String, ResponseHandler<T>> responseHandlers; 

    public Response(String cmdType, String data) {
        this.response = new JSONObject(new JSONTokener(new StringReader(data)));
        this.extras = response.optJSONObject("extras");
        this.cmdType = cmdType;
        initializeResponseHandlers();
    }

    // Initialize response handlers based on command type
    private void initializeResponseHandlers() {
        responseHandlers = new HashMap<>();
        responseHandlers.put(ECHO, new EchoHandler());
        responseHandlers.put(SCAN, new ScanHandler());
        responseHandlers.put(FLY, new GenericHandler());
        responseHandlers.put(STOP, new GenericHandler());
        responseHandlers.put(HEADING, new GenericHandler());
    }
    
    // Use generics to specify the return type
    public T handleResponse() {
        ResponseHandler<T> handler = responseHandlers.get(cmdType);
        if (handler != null) {
            return handler.handle(response, extras);
        } else {
            // Default handler if no specific handler found
            return (T) new GenericResponse(response.optInt("cost"), response.optString("status"));
        }
    }
}
