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
public class ObjectManaCrystal extends Entity{

    public static final String objName = "Mana Crystal";

    GamePanel gp;
    
    public ObjectManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_pickUpOnly;
        name = objName;
        value = 1;
        Down1 = setup("/res/objects/manacrystal_full",gp.tileSize,gp.tileSize);       
        image = setup("/res/objects/manacrystal_full",gp.tileSize,gp.tileSize);       
        image2 = setup("/res/objects/manacrystal_blank",gp.tileSize,gp.tileSize);
        
    }
    public boolean use(Entity entity){
        
        //gp.playSE(2);
        gp.ui.addMessage("Mana + " + value);
        entity.mana += value;
        return true;
    }
}
