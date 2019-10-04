/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;


import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
/**
 *
 * @author STAS
 */
public final class Voyager{
    private Material voyagerMaterial;
    private RigidBodyControl    voyagerPhy;
    private Spatial voyager;
    private float speed;
    private static final int size;
    private String name;
    
    public Voyager(String name){
        this.name = name;
        speed = 90f;
    }
    
    static{
        size = 3;
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
    
    public void setGeometry(Spatial s){
        voyager = s;
    }
    
    public void setPhysics(RigidBodyControl r){
        voyagerPhy = r;
    }
    
    public void setMaterial(Material m){
        voyagerMaterial = m;
    }
    
    public void setSpeed(float speed){
        this.speed = speed;
    }
    
    public float getSpeed(){
        return speed;
    }
       
    public void setPosition(Vector3f pos){
        voyager.setLocalTranslation(pos);
    }
    
    public String getName(){
        return name;
    }
    
}
