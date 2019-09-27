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
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

/**
 *
 * @author STAS
 */
public class Physics {
    public void setPhysics(Voyager obj,BulletAppState bulletAppState,Node rootNode){     
        obj.getGeometry().setLocalTranslation(-500,0,1005);
        rootNode.attachChild(obj.getGeometry());
        RigidBodyControl voyagerPhy = new RigidBodyControl(1f);
        obj.getGeometry().addControl(voyagerPhy);
        bulletAppState.getPhysicsSpace().add(voyagerPhy);       
        obj.setPhysics(voyagerPhy);
    }
    
    public void setMaterial(Voyager obj,AssetManager assetManager){
        Spatial voyager;
        voyager = assetManager.loadModel("Models/space-shuttle-orbiter.obj");
        voyager.scale(0.01f);
        Material voyagerMaterial;
        voyagerMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        voyager.setMaterial(voyagerMaterial);
        obj.setGeometry(voyager);
    }
    
    public void setPhysics(Jupiter obj,BulletAppState bulletAppState,Node rootNode){     
        Geometry jupiterGeometry = new Geometry("Planet", obj.getShape());
        jupiterGeometry.setMaterial(obj.getMaterial());
        rootNode.attachChild(jupiterGeometry);
        jupiterGeometry.setLocalTranslation(0,2,-15);
        jupiterGeometry.rotate(1.6f, 0, 0);        
        RigidBodyControl jupiterPhy = new RigidBodyControl(obj.getMass());       
        jupiterGeometry.addControl(jupiterPhy);
        bulletAppState.getPhysicsSpace().add(jupiterPhy);
        obj.setPhysics(jupiterPhy);
        obj.setGeometry(jupiterGeometry);
    }

    void setMaterial(Jupiter jupiter, AssetManager assetManager) {
        Material jupiterMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key1 = new TextureKey("Textures/jupiter3.png");
        key1.setGenerateMips(true);
        Texture texture1 = assetManager.loadTexture(key1);
        texture1.setWrap(Texture.WrapMode.Repeat);
        jupiterMaterial.setTexture("ColorMap", texture1);
        jupiter.setMaterial(jupiterMaterial);
    }
}
