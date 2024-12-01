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
public class ObjectAxe extends Entity{
    
    public static final String objName = "Axe";
    
    public ObjectAxe(GamePanel gp) {
        super(gp);
        
        type = type_axe;
        name = objName;
        Down1 = setup("/res/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nCan cut trees and \nmonsters.";
        knockBackPower = 2;
        motion1_duration = 15;
        motion2_duration = 35;
    }
    
}
