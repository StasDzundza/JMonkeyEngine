/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author STAS
 */
@RunWith(MockitoJUnitRunner.class)
public class PhysicsIT {
    @Mock
    private BulletAppState bulletAppState;
    @Mock 
    private AssetManager assetManager;
    
    @InjectMocks 
    private Physics instance = new Physics();
    
    public PhysicsIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(assetManager.loadModel("Models/space-shuttle-orbiter.obj")).thenReturn(new Geometry());
        when(assetManager.loadMaterial("Common/MatDefs/Misc/ShowNormals.j3md")).thenReturn(new Material());
        when(assetManager.loadTexture((TextureKey)any())).thenReturn(new Texture2D());

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setPhysics method, of class Physics.
     */
   /* @Test
    public void testSetPhysics_3args_1() {
        System.out.println("setPhysics");
        Voyager obj = new Voyager();
        instance.setMaterial(obj, assetManager,true);
        instance.setPhysics(obj, bulletAppState);
    }*/

   
    @Test
    public void testSetMaterial_Voyager_AssetManager() {
        System.out.println("setMaterial");
        Voyager obj = new Voyager("Voyager2");      
        instance.setMaterial(obj, assetManager,new Vector3f(-500,0,1005),true);
        assertNotEquals(obj.getGeometry(), null);//Object is created
        assertEquals(new Vector3f(-500,0,1005), obj.getGeometry().getLocalTranslation());//position is right
        assertEquals("Voyager2", obj.getGeometry().getName());//name is correct
    }

   
   /* @Test
    public void testSetPhysics_3args_2() {
        System.out.println("setPhysics");
        Jupiter obj = null;
        Physics instance = new Physics();
        instance.setPhysics(obj, bulletAppState, rootNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/


    @Test
    public void testSetMaterial_Jupiter_AssetManager() {
        System.out.println("setMaterial");
        Jupiter jupiter = new Jupiter("Jupiter");
        instance.setMaterial(jupiter, assetManager,new Vector3f(0,2,-15), true);
        assertNotEquals(jupiter.getGeometry(), null);//Object is created
        assertEquals(new Vector3f(0,2,-15), jupiter.getGeometry().getLocalTranslation());//position is right
        assertEquals("Jupiter", jupiter.getGeometry().getName());//name is correct
    }
    
}
