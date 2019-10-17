/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
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
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author STAS
 */

@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest(RigitBodyControl.class)
public class PhysicsIT {
    @Mock
    private PhysicsSpace space;
    @Mock 
    private AssetManager assetManager;
    
    @InjectMocks 
    private Physics instance = new Physics();
    
    private GameController c = new GameController();
    
    public PhysicsIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        GameController g = new GameController();
        when(assetManager.loadModel("Models/space-shuttle-orbiter.obj")).thenReturn(new Geometry());
        when(assetManager.loadMaterial("Common/MatDefs/Misc/ShowNormals.j3md")).thenReturn(new Material());
        when(assetManager.loadTexture((TextureKey)any())).thenReturn(new Texture2D());
        doNothing().when(space).add((RigidBodyControl)any());
    }
    
    @After
    public void tearDown() {
    }
   
    @Test
    public void testSetMaterial_Voyager_AssetManager() {
        Voyager obj = new Voyager("Voyager2");      
        instance.setMaterial(obj, assetManager,new Vector3f(-500,0,1005),true);
        assertNotEquals(obj.getGeometry(), null);//Object is created
        assertEquals(new Vector3f(-500,0,1005), obj.getGeometry().getLocalTranslation());//position is right
        assertEquals("Voyager2", obj.getGeometry().getName());//name is correct
        verify(assetManager,times(1)).loadModel((String) any());
        verify(assetManager,times(1)).loadMaterial((String) any());
    }


    @Test
    public void testSetMaterial_Jupiter_AssetManager() {
        Jupiter jupiter = new Jupiter("Jupiter");
        instance.setMaterial(jupiter, assetManager,new Vector3f(0,2,-15), true);
        assertNotEquals(jupiter.getGeometry(), null);//Object is created
        assertEquals(new Vector3f(0,2,-15), jupiter.getGeometry().getLocalTranslation());//position is right
        assertEquals("Jupiter", jupiter.getGeometry().getName());//name is correct
        verify(assetManager,times(1)).loadMaterial((String) any());
    }
       
    @Test
    public void testSetPhysics_3args_2() {
        Jupiter obj = new Jupiter("Jupiter");
        instance.setMaterial(obj, assetManager,new Vector3f(0,2,-15) , true);
        instance.setPhysics(obj, space);
        assertNotEquals(null, obj.getPhysics());
        assertEquals(new Vector3f(0,2,-15), obj.getGeometry().getLocalTranslation());
        verify(assetManager,times(1)).loadMaterial((String) any());
        verify(assetManager,times(1)).loadTexture((TextureKey) any());
        verify(space,times(1)).add(any());
    }
    
    @Test
        public void testSetPhysics_3args_1() {
        Voyager obj = new Voyager("Voyager");
        instance.setMaterial(obj, assetManager,new Vector3f(0,2,-15),true);
        Spatial s = obj.getGeometry();
        s = mock(Spatial.class);
        obj.setGeometry(s);
        doNothing().when(s).addControl((RigidBodyControl) any());
        instance.setPhysics(obj, space);
        assertNotEquals(null, obj.getPhysics());
        verify(assetManager,times(1)).loadModel((String) any());
        verify(space,times(1)).add(any());
        verify(s,times(1)).addControl((RigidBodyControl)any());
    }
    
}
