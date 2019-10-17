/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.system.NativeLibraryLoader;
import com.jme3.texture.Texture;

/**
 *
 * @author STAS
 */
public class Physics {
    private final String voyagerModel = "Models/space-shuttle-orbiter.obj";
    private final String jupiterTexture = "Textures/jupiter3.png";
    private final String voyagerJ3MD = "Common/MatDefs/Misc/ShowNormals.j3md";
    private final String jupiterJ3MD = "Common/MatDefs/Misc/Unshaded.j3md";
    
    public void setPhysics(Voyager obj,PhysicsSpace space){         
        if (NativeLibraryLoader.isUsingNativeBullet()) 
            NativeLibraryLoader.loadNativeLibrary("bulletjme", true);
        RigidBodyControl voyagerPhy = new RigidBodyControl(1f); 
        obj.getGeometry().addControl(voyagerPhy);    
        space.add(voyagerPhy);   
        obj.setPhysics(voyagerPhy);
    }
    
    public void setMaterial(Voyager obj,AssetManager assetManager,Vector3f position,boolean modeFlag){
        Spatial voyager;
        voyager = assetManager.loadModel(voyagerModel);
        voyager.setLocalTranslation(position);
        voyager.scale(0.01f);
        Material voyagerMaterial;
        if(!modeFlag)
            voyagerMaterial = new Material(assetManager, voyagerJ3MD);
        else
            voyagerMaterial = assetManager.loadMaterial(voyagerJ3MD);
        
        voyager.setMaterial(voyagerMaterial);
        voyager.setName(obj.getName());
        obj.setGeometry(voyager);
        obj.getGeometry().setLocalTranslation(position);
    }
    
    public void setPhysics(Jupiter obj,PhysicsSpace space){     
        if (NativeLibraryLoader.isUsingNativeBullet()) {
            NativeLibraryLoader.loadNativeLibrary("bulletjme", true);
}
        RigidBodyControl jupiterPhy = new RigidBodyControl(obj.getMass());  
        obj.getGeometry().addControl(jupiterPhy);
        space.add(jupiterPhy);
        obj.setPhysics(jupiterPhy);
    }

    void setMaterial(Jupiter jupiter, AssetManager assetManager,Vector3f position,boolean modeFlag) {
        Material jupiterMaterial;
        if(!modeFlag)
            jupiterMaterial = new Material(assetManager, jupiterJ3MD);
        else
            jupiterMaterial = assetManager.loadMaterial(jupiterJ3MD);
        TextureKey key1 = new TextureKey(jupiterTexture);
        key1.setGenerateMips(true);
        Texture texture1 = assetManager.loadTexture(key1);
        texture1.setWrap(Texture.WrapMode.Repeat);
        if(!modeFlag)
            jupiterMaterial.setTexture("ColorMap", texture1);
        jupiter.setMaterial(jupiterMaterial);
        
        Geometry jupiterGeometry = new Geometry(jupiter.getName(), jupiter.getShape());
        jupiterGeometry.setName(jupiter.getName());
        if(!modeFlag)
        jupiterGeometry.setMaterial(jupiter.getMaterial());
        jupiterGeometry.setLocalTranslation(position);
        jupiterGeometry.rotate(1.6f, 0, 0);  
        jupiter.setGeometry(jupiterGeometry);
    }
}
