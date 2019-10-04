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
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 *
 * @author STAS
 */
public class Physics {
    public void setPhysics(Voyager obj,BulletAppState bulletAppState){    
        obj.getGeometry().setLocalTranslation(-500,0,1005);
        RigidBodyControl voyagerPhy = new RigidBodyControl(1f); 
        obj.getGeometry().addControl(voyagerPhy);    
        bulletAppState.getPhysicsSpace().add(voyagerPhy);   
        obj.setPhysics(voyagerPhy);
    }
    
    public void setMaterial(Voyager obj,AssetManager assetManager,Vector3f position,boolean test){
        Spatial voyager;
        voyager = assetManager.loadModel("Models/space-shuttle-orbiter.obj");
        voyager.setLocalTranslation(position);
        voyager.scale(0.01f);
        Material voyagerMaterial;
        if(!test)
            voyagerMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        else
            voyagerMaterial = assetManager.loadMaterial("Common/MatDefs/Misc/ShowNormals.j3md");
        
        voyager.setMaterial(voyagerMaterial);
        voyager.setName(obj.getName());
        obj.setGeometry(voyager);
    }
    
    public void setPhysics(Jupiter obj,BulletAppState bulletAppState){     
        RigidBodyControl jupiterPhy = new RigidBodyControl(obj.getMass());  
        obj.getGeometry().addControl(jupiterPhy);
        System.out.println("hello");
        bulletAppState.getPhysicsSpace().add(jupiterPhy);
        obj.setPhysics(jupiterPhy);
    }

    void setMaterial(Jupiter jupiter, AssetManager assetManager,Vector3f position,boolean test) {
        Material jupiterMaterial;
        if(!test)
            jupiterMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        else
            jupiterMaterial = assetManager.loadMaterial("Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key1 = new TextureKey("Textures/jupiter3.png");
        key1.setGenerateMips(true);
        Texture texture1 = assetManager.loadTexture(key1);
        texture1.setWrap(Texture.WrapMode.Repeat);
        if(!test)
            jupiterMaterial.setTexture("ColorMap", texture1);
        jupiter.setMaterial(jupiterMaterial);
        
        Geometry jupiterGeometry = new Geometry(jupiter.getName(), jupiter.getShape());
        jupiterGeometry.setName(jupiter.getName());
        jupiterGeometry.setMaterial(jupiter.getMaterial());
        jupiterGeometry.setLocalTranslation(position);
        jupiterGeometry.rotate(1.6f, 0, 0);  
        jupiter.setGeometry(jupiterGeometry);
    }
}
