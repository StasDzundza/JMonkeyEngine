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
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

/**
 *
 * @author STAS
 */
public final class Jupiter {
    private Material jupiterMaterial;
    private RigidBodyControl    jupiterPhy;
    private final Sphere jupiter;
    private Geometry jupiterGeometry;
    private final int JupiterMas;
    private static final float jupiterRadius;
    
    public Jupiter(AssetManager assetManager,BulletAppState bulletAppState,Node rootNode){
        JupiterMas = 5000000;
        jupiter = new Sphere(32, 32, jupiterRadius, true, false);
        jupiter.setTextureMode(Sphere.TextureMode.Projected);
        
        initMaterials(assetManager);
        initModel(bulletAppState,rootNode);
    }
    static{
        jupiterRadius = 205f;
    }
    
    public void initMaterials(AssetManager assetManager){
        jupiterMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key1 = new TextureKey("Textures/jupiter3.png");
        key1.setGenerateMips(true);
        Texture texture1 = assetManager.loadTexture(key1);
        texture1.setWrap(Texture.WrapMode.Repeat);
        jupiterMaterial.setTexture("ColorMap", texture1);
    }
    
    public void initModel(BulletAppState bulletAppState,Node rootNode){
        jupiterGeometry = new Geometry("Planet", jupiter);
        jupiterGeometry.setMaterial(jupiterMaterial);
        rootNode.attachChild(jupiterGeometry);
        jupiterGeometry.setLocalTranslation(0,2,-15);
        jupiterGeometry.rotate(1.6f, 0, 0);        
        jupiterPhy = new RigidBodyControl(JupiterMas);        
        jupiterGeometry.addControl(jupiterPhy);
        bulletAppState.getPhysicsSpace().add(jupiterPhy);
    }
    
    public Geometry getGeometry(){
        return this.jupiterGeometry;
    }
    
    public RigidBodyControl getPhysics(){
        return jupiterPhy;
    }
    
    public Sphere getShape(){
        return jupiter;
    }
    
    public Material getMaterial(){
        return jupiterMaterial;
    }
    
    public int getMass(){
        return JupiterMas;
    }
    
}
