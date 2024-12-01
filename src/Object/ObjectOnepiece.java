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
public class ObjectOnepiece extends Entity{
    
    public static final String objName = "One Piece";
    
    GamePanel gp;
    
    public ObjectOnepiece(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_pickUpOnly;
        name = objName;
        Down1 = setup("/res/objects/blueheart",gp.tileSize,gp.tileSize);
        
        setDialogue();
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You pick up a beautiful blue gem.";
        dialogues[0][1] = "You find the One Piece, the legendary treasure!"; 
    }
    public boolean use(Entity entity){
        
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
