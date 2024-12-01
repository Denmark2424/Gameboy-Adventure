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
public class ObjectChest extends Entity{
    
    public static final String objName = "Chest";

     GamePanel gp;
 
    
    public ObjectChest(GamePanel gp){
        super(gp);
        this.gp = gp;

        
    type = type_obstacle;    
    name = objName;
    image = setup("/res/objects/chest",gp.tileSize, gp.tileSize);
    image2 = setup("/res/objects/chest_opened",gp.tileSize, gp.tileSize);
    Down1 = image;
    collision = true;
    
    solidArea.x = 4;
    solidArea.y = 16;
    solidArea.width = 40;
    solidArea.height = 32;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
        
    }
    public void setLoot(Entity loot){
        
        this.loot = loot;
        
        setDialogue();

    }
    public void setDialogue(){
        
        dialogues[0][0] = "You open the chest and find a " + loot.name + "\n...But you cannot carry any more!";
        dialogues[1][0] = "You open the chest and find a " + loot.name + "\nYou obtain the " + loot.name + "!";
        dialogues[2][0] = "It's empty";

    }
    public void interact(){
     
        
        if(opened == false){
            //gp.playSE(life);
            
            if(gp.player.canObtainItem(loot) == false){
                startDialogue(this,0);
            }
            else{
                startDialogue(this,1);
                Down1 = image2;
                opened = true;
            }
        }
        else{
            startDialogue(this,2);
        }
    }
}