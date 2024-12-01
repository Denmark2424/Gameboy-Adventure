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
public class ObjectDoor extends Entity {
    
    public static final String objName = "Door";

    
    GamePanel gp;
    
    public ObjectDoor(GamePanel gp){
        super(gp);
        this.gp = gp;
        
    type = type_obstacle;
    name = objName;
    Down1 = setup("/res/objects/door",gp.tileSize, gp.tileSize);
    collision = true;
    
    solidArea.x = 0;
    solidArea.y = 16;
    solidArea.width = 48;
    solidArea.height = 32;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    
    setDialogue();
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You need a key to open this.";
    }
    public void interact(){
        startDialogue(this,0);        
    }
}
