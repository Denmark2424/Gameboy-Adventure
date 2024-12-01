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
public class ObjectSword extends Entity{
    
    public static final String objName = "Normal Sword";

    GamePanel gp;
    
    public ObjectSword(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = objName;
        Down1 = setup("/res/objects/sword_normal",gp.tileSize,gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nSword of Hero.";
        price = 25;
        knockBackPower = 5;
        motion1_duration = 5;
        motion2_duration = 25;
    }
    
}
