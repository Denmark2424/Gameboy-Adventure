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
public class ObjectHeart extends Entity{
    
    public static final String objName = "Heart";

    GamePanel gp;
    
    public ObjectHeart(GamePanel gp){
        super(gp);
        this.gp = gp;
        
    type = type_pickUpOnly;    
    name = objName;
    value = 2;
    Down1 = setup("/res/objects/heart_full",gp.tileSize, gp.tileSize);
    image = setup("/res/objects/heart_full",gp.tileSize, gp.tileSize);
    image2 = setup("/res/objects/heart_half",gp.tileSize, gp.tileSize);
    image3 = setup("/res/objects/heart_blank",gp.tileSize, gp.tileSize);

    }   
    public boolean use(Entity entity){
        
        //gp.playSE(2);
        gp.ui.addMessage("Life + " + value);
        entity.life += value;
        return true;
    }
}
