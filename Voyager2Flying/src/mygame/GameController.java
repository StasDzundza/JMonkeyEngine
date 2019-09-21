/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;

/**
 *
 * @author STAS
 */
public class GameController extends SimpleApplication {
     
    private BulletAppState bulletAppState;
    private Jupiter jupiter;
    private Voyager voyager;
    private Light light;
    private BitmapText footer;
    
    public GameController(){
        
        

    }
    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(400);
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState); 
        
        bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, 0, 0));
        
        jupiter = new Jupiter(assetManager,bulletAppState,rootNode);
        voyager = new Voyager(assetManager,bulletAppState,rootNode);
        light = new Light(rootNode);
        cam.setLocation(jupiter.getGeometry().getLocalTranslation().add(-900, 400, 500));
        
        initCrossHair();
        voyager.start();
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        Vector3f jupiterPosition = jupiter.getGeometry().getLocalTranslation();
        Vector3f voyagerPosition = voyager.getGeometry().getLocalTranslation();
        
        float deltaX = jupiterPosition.x - voyagerPosition.x;
        float deltaY = jupiterPosition.y - voyagerPosition.y;
        float deltaZ = jupiterPosition.z - voyagerPosition.z;
        
        float length = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
        float G = 3;
        float gravity = G*jupiter.getMass()/(length*length);
        voyager.getPhysics().setGravity(new Vector3f(gravity*deltaX/length, gravity*deltaY/length, gravity*deltaZ/length));
        cam.lookAt(jupiterPosition, voyagerPosition);
        cam.setLocation(voyagerPosition.add(new Vector3f(-deltaX*0.2f,30,-deltaZ*0.2f)));
        
        footer.setText("Speed : "+voyager.getPhysics().getLinearVelocity().length()+
                ";\t Gravity : "+gravity +";\t Distance : " + length);
    }

    private void initCrossHair() {
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+");        // fake crosshairs :)
        ch.setLocalTranslation( // center
        settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
        settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
        
        footer = new BitmapText(guiFont, false);
        footer.setText("1");
        footer.setLocalTranslation(settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2, 20, 0);
        guiNode.attachChild(footer);  
  }   
       
}