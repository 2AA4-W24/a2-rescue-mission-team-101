package ca.mcmaster.se2aa4.island.team101;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import org.json.JSONArray;


public class ExampleTest {

    @Test
    public void testFlyAction() {
        String response = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        Response<?> responseObj = new Response<>("FLY", response);
        assertEquals(2, responseObj.getCost());
    }

    @Test
    public void testEchoAction() {
        String response = "{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }";
        Response<?> responseObj = new Response<>("ECHO", response);
        assertEquals(2, responseObj.getExtras().getInt("range"));
    }

    @Test
    public void testScanAction() {
        // Simulate a response from the game engine for the scan action
        String response = "{\"cost\": 2, \"extras\": {\"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}";

        // Create a Response object to parse the response
        Response<?> responseObj = new Response<>("SCAN", response);

        // Verify that the list of biomes is correctly parsed
        JSONArray biomes = responseObj.getExtras().getJSONArray("biomes");
        assertEquals(2, biomes.length());
        assertEquals("GLACIER", biomes.getString(0));
        assertEquals("ALPINE", biomes.getString(1));
    }
}
