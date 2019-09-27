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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

/**
 *
 * @author STAS
 */
public final class Jupiter{
    private Material jupiterMaterial;
    private RigidBodyControl    jupiterPhy;
    private final Sphere jupiter;
    private Geometry jupiterGeometry;
    private final int JupiterMas;
    private static final float jupiterRadius;
    
    public Jupiter(){
        JupiterMas = 5000000;
        jupiter = new Sphere(32, 32, jupiterRadius, true, false);
        jupiter.setTextureMode(Sphere.TextureMode.Projected);
    }
    
    static{
        jupiterRadius = 205f;
    }
    
    public Geometry getGeometry(){
        return this.jupiterGeometry;
    }
    
    public RigidBodyControl getPhysics(){
        return jupiterPhy;
    }
    
    public Material getMaterial(){
        return jupiterMaterial;
    }
    
    public void setGeometry(Geometry geometry){
        jupiterGeometry = geometry;
    }
    
    public void setPhysics(RigidBodyControl r){
        jupiterPhy = r;
    }
    
    public void setMaterial(Material m){
        jupiterMaterial = m;
    }
    
    public int getMass(){
        return JupiterMas;
    }
    
    public Sphere getShape(){
        return jupiter;
    }

    
}
