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
public class ObjectShieldRare extends Entity{
    
    public static final String objName = "Rare Shield";
    
    GamePanel gp;
    
    public ObjectShieldRare(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = objName;
        Down1 = setup("/res/objects/shield_blue",gp.tileSize,gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nCrafted by a famous \nartisan.";
        price = 25;
    }
    
}
