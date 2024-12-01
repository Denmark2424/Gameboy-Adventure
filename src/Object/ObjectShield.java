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
public class ObjectShield extends Entity{
    
    public static final String objName = "Wooden Shield";
    
    GamePanel gp;
    
    public ObjectShield(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = objName;
        Down1 = setup("/res/objects/shield_wood",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade of woods.";

    }
    
}
