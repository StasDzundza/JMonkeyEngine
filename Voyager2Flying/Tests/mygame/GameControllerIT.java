/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author STAS
 */

public class GameControllerIT {
    private Jupiter jupiter;
    private Voyager voyager;
    private static final float G;
    
    private float deltaX,deltaY,deltaZ,length,gravity;
    
    public GameControllerIT() {
        jupiter = new Jupiter("Jupiter");
        voyager = new Voyager("Voyager2");
        Geometry jupiterGeometry = new Geometry("Jupiter", jupiter.getShape());
        jupiterGeometry.setLocalTranslation(0,2,-15);
        jupiter.setGeometry(jupiterGeometry);
        Spatial s = new Geometry("Voyager");
        s.setLocalTranslation(-500,0,1005);
        voyager.setGeometry(s);
        
        Vector3f jupiterPosition = jupiter.getGeometry().getLocalTranslation();
        Vector3f voyagerPosition = voyager.getGeometry().getLocalTranslation();
        deltaX = jupiterPosition.x - voyagerPosition.x;
        deltaY = jupiterPosition.y - voyagerPosition.y;
        deltaZ = jupiterPosition.z - voyagerPosition.z;
        
        length = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
        gravity = G * jupiter.getMass()/(length * length);       
    }
    
    static{
        G = 3;
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of simpleInitApp method, of class GameController.
     */
    @Test
    public void simpleUpdateTest() {
        for(int i = 0; i < 600; i++){
            voyager.setPosition(voyager.getGeometry().getLocalTranslation().add(-0.5f,0, -1));
            Vector3f jupiterPosition = jupiter.getGeometry().getLocalTranslation();
            Vector3f voyagerPosition = voyager.getGeometry().getLocalTranslation();
            deltaX = jupiterPosition.x - voyagerPosition.x;
            deltaY = jupiterPosition.y - voyagerPosition.y;
            deltaZ = jupiterPosition.z - voyagerPosition.z;
        
            float newLength = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
            float newGravity = G * jupiter.getMass()/(newLength * newLength);
            boolean result = (newLength < length && newGravity > gravity);
            assertTrue(result);
            length = newLength;
            gravity = newGravity;
        }
        
        voyager.setPosition(voyager.getGeometry().getLocalTranslation().add(-50f,0, -100));
        
        for(int i = 800; i < 1000; i++){
            voyager.setPosition(voyager.getGeometry().getLocalTranslation().add(-0.5f,0, -1));
            Vector3f jupiterPosition = jupiter.getGeometry().getLocalTranslation();
            Vector3f voyagerPosition = voyager.getGeometry().getLocalTranslation();
            deltaX = jupiterPosition.x - voyagerPosition.x;
            deltaY = jupiterPosition.y - voyagerPosition.y;
            deltaZ = jupiterPosition.z - voyagerPosition.z;
        
            float newLength = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
            float newGravity = G * jupiter.getMass()/(newLength * newLength);
            boolean result = (newLength > length && newGravity < gravity);
            assertTrue(result);
            length = newLength;
            gravity = newGravity;
        }
    }
}
