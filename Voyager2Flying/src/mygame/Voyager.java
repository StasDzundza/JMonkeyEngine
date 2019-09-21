/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @author STAS
 */
public final class Voyager {
    private Material voyagerMaterial;
    private RigidBodyControl    voyagerPhy;
    private Box voyager;
    private Geometry voyagerGeometry;
    private float speed;
    
    public Voyager(AssetManager assetManager,BulletAppState bulletAppState,Node rootNode){
        voyager = new Box(3,3,3);
        speed = 85f;
        initMaterials(assetManager);
        initModel(bulletAppState,rootNode);
    }
    
    public void initMaterials(AssetManager assetManager){
        voyagerMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        voyagerMaterial.setColor("Color", ColorRGBA.Blue);
    }
    
    public void initModel(BulletAppState bulletAppState,Node rootNode){
        voyagerGeometry = new Geometry("Voyager", voyager);
        voyagerGeometry.setMaterial(voyagerMaterial);
        voyagerGeometry.setLocalTranslation(-500,0,1005); // Move it a bit
        rootNode.attachChild(voyagerGeometry);
        /* Make the jupiter physical with mass 1.0f! */
        voyagerPhy = new RigidBodyControl(1f);
        voyagerGeometry.addControl(voyagerPhy);
        bulletAppState.getPhysicsSpace().add(voyagerPhy);
    }
    
    public void start(){
        voyagerPhy.setLinearVelocity(new Vector3f(speed, 0, 0));
    }
    
    public Geometry getGeometry(){
        return this.voyagerGeometry;
    }
    
    public RigidBodyControl getPhysics(){
        return voyagerPhy;
    }
     
    public Box getShape(){
        return voyager;
    }
      
    public Material getMaterial(){
        return voyagerMaterial;
    }
    
}
