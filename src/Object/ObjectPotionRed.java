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
public class ObjectPotionRed extends Entity{
    
    public static final String objName = "Red Potion";

    GamePanel gp;
    
    
    public ObjectPotionRed(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_consumables;
        name = objName;
        value = 2;
        Down1 = setup("/res/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Red Potion]\nHeals your life by " +value +".";
        price = 5;
        stackable = true;
        
        setDialogue();
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You drink the " + name + "!\n" + "Life recovers to " + value + "." ;
    }
    public boolean use(Entity entity){
        
        startDialogue(this,0);
        entity.life += value;
        //gp.playSE(2);
        return true;
    }
    
}
