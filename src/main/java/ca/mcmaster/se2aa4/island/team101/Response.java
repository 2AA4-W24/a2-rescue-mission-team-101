package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.StringReader;

import static ca.mcmaster.se2aa4.island.team101.CommandStrings.*;

public class Response<T> {
    private JSONObject response;
    private JSONObject extras;
    private String cmdType;
    private String data;

    public Response(String cmdType, String data) {
        this.response = new JSONObject(new JSONTokener(new StringReader(data)));
        this.extras = response.optJSONObject("extras");
        this.cmdType = cmdType;
        this.data = data;
    }

    public GenericResponse handleResponse() {
        if (cmdType.equals(ECHO)) {
            return new EchoHandler().handle(response, extras);
        } else if (cmdType.equals(SCAN)) {
            return new ScanHandler().handle(response, extras);
        } else {
            // Default handler
            return new GenericResponse(response.optInt("cost"), response.optString("status"));
        }
    }
    @Override
    public String toString(){
        return data;
    }
}
