/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import Entity.Entity;
import Main.GamePanel;

/**
 *
 * @author Administrator
 */
public class ObjectLantern extends Entity{
    
    public static final String objName = "Lantern";
    GamePanel gp;
    
    public ObjectLantern(GamePanel gp) {
        super(gp);
        
        type = type_light;
        name = objName;
        Down1 = setup("/res/objects/lantern",gp.tileSize,gp.tileSize);
        description = "[Lantern]\nIlluminates the \ndarkness.";
        price = 20;
        lightRadius = 250;
    }
    
}
