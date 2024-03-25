package ca.mcmaster.se2aa4.island.team101;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void testUpdateCompassDirection() {
        Compass compass = new Compass("North");
        
        // Turn the compass direction to the right
        compass.turnRight();

        // Check if the compass direction is correctly updated
        assertEquals("East", compass.getDirection());
    }
    
    @Test
    public void testTileIsEmpty() {
        Tile tile = new Tile();
        
        // Check if the tile is empty
        assertTrue(tile.isEmpty());
    }

    @Test
    public void testTileIsNotEmpty() {
        Tile tile = new Tile();
        tile.setCreekID("creek1");
        
        // Check if the tile is not empty
        assertFalse(tile.isEmpty());
    }
    
    @Test
    public void testInitializeWithValidData() {
        Initializer initializer = new Initializer("data");
        
        // Check if the initializer is successfully created with valid data
        assertNotNull(initializer);
    }
    
    @Test
    public void testAssembleDrone() {
        Initializer initializer = new Initializer("data");
        
        // Assemble a drone using the initializer
        Drone drone = initializer.assembleDrone();

        // Check if the drone is successfully assembled
        assertNotNull(drone);
    }
    
    @Test
    public void testEchoResponseType() {
        EchoResponse response = new EchoResponse(0, null, null);
        
        // Check if the response type is "Echo"
        assertEquals("Echo", response.getType());
    }
    
    @Test
    public void testGenericResponseType() {
        GenericResponse response = new GenericResponse(0, null, null);
        
        // Check if the response type is "Generic"
        assertEquals("Generic", response.getType());
    }
    
    @Test
    public void testScanResponseType() {
        ScanResponse response = new ScanResponse(0, null, null);
        
        // Check if the response type is "Scan"
        assertEquals("Scan", response.getType());
    }
}
