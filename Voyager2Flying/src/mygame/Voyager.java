/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author STAS
 */
public final class Voyager {
    private Material voyagerMaterial;
    private RigidBodyControl    voyagerPhy;
    private Spatial voyager;
    private Geometry voyagerGeometry;
    private float speed;
    private static final int size;
    
    public Voyager(AssetManager assetManager,BulletAppState bulletAppState,Node rootNode){
        voyager = assetManager.loadModel("Models/space-shuttle-orbiter.obj");
        voyager.scale(0.01f);
        speed = 90f;
        initMaterials(assetManager);
        initModel(bulletAppState,rootNode);
    }
    
    static{
        size = 3;
    }
    
    public void initMaterials(AssetManager assetManager){
        voyagerMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
    }
    
    public void initModel(BulletAppState bulletAppState,Node rootNode){
        voyager.setMaterial(voyagerMaterial);
        voyager.setLocalTranslation(-500,0,1005); // Move it 
        //voyager.rotate(1.6f, 0, 0);          // Rotate it 
        rootNode.attachChild(voyager);
        /* Make the jupiter physical with mass 0.0f! */
        voyagerPhy = new RigidBodyControl(1f);
        voyager.addControl(voyagerPhy);
        bulletAppState.getPhysicsSpace().add(voyagerPhy);       
        voyagerPhy.setLinearVelocity(new Vector3f(speed, 0, 0));
    }
    
    public void start(){
        voyagerPhy.setLinearVelocity(new Vector3f(speed, 0, 0));
    }
    
    public Spatial getGeometry(){
        return voyager;
    }
    
    public RigidBodyControl getPhysics(){
        return voyagerPhy;
    }
      
    public Material getMaterial(){
        return voyagerMaterial;
    }
    
}
