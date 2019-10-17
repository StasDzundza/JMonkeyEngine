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
    private static final int G;
    private static final int camSpeed;
    private Physics p;
    private static final String crossHair;
    
    public GameController(){
        p = new Physics();
    }
    
    static{
        G = 3;
        camSpeed = 400; 
        crossHair = "Interface/Fonts/Default.fnt";
    }
    
    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(camSpeed);
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState); 
        bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, 0, 0));
        
        jupiter = new Jupiter("Jupiter");
        p.setMaterial(jupiter, assetManager,new Vector3f(0,2,-15),false);
        p.setPhysics(jupiter, bulletAppState.getPhysicsSpace());
        rootNode.attachChild(jupiter.getGeometry());

        voyager = new Voyager("Voyager2");
        p.setMaterial(voyager, assetManager,new Vector3f(-500,0,1005),false);
        p.setPhysics(voyager, bulletAppState.getPhysicsSpace());
        rootNode.attachChild(voyager.getGeometry());

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
        float gravity = G*jupiter.getMass()/(length*length);
        voyager.getPhysics().setGravity(new Vector3f(gravity*deltaX/length, gravity*deltaY/length, gravity*deltaZ/length));
        cam.lookAt(jupiterPosition, voyagerPosition);
        cam.setLocation(voyagerPosition.add(new Vector3f(-deltaX*0.2f,30,-deltaZ*0.2f)));
        
        footer.setText("Speed : "+voyager.getPhysics().getLinearVelocity().length()+
                ";\t Gravity : "+gravity +";\t Distance : " + length);
    }

    private void initCrossHair() {
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont(crossHair);
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
