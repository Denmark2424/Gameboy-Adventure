/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Main.GamePanel;
import java.awt.Rectangle;
import java.util.Random;


/**
 *
 * @author Administrator
 */
public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp){
        super(gp);
        
        direction = "down";
        defaultSpeed = 1;
        speed = defaultSpeed;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;
        
        dialogueSet = -1;
        
        getImage();
        setDialogue();
    }
     public void getImage(){
        
        Up1 = setup("/res/npc/oldman_up_1",gp.tileSize, gp.tileSize);
        Up2 = setup("/res/npc/oldman_up_2",gp.tileSize, gp.tileSize);
        Down1 = setup("/res/npc/oldman_down_1",gp.tileSize, gp.tileSize);
        Down2 = setup("/res/npc/oldman_down_2",gp.tileSize, gp.tileSize);
        Left1 = setup("/res/npc/oldman_left_1",gp.tileSize, gp.tileSize);
        Left2 = setup("/res/npc/oldman_left_2",gp.tileSize, gp.tileSize);
        Right1 = setup("/res/npc/oldman_right_1",gp.tileSize, gp.tileSize);
        Right2 = setup("/res/npc/oldman_right_2",gp.tileSize, gp.tileSize);
        
    }
     public void setDialogue(){
         
         dialogues[0][0] = "Well hello there, Adventurer.";
         dialogues[0][1] = "So you come to this island to \n find the treasure?";
         dialogues[0][2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
         dialogues[0][3] = "Well, goodluck on you.";

         dialogues[1][0] = "If you become tired, rest at the water.";
         dialogues[1][1] = "Monsters respawn when it's dawn.";
         dialogues[1][2] = "In any case, don't push yourself too hard.";
         
         dialogues[2][0] = "To open a door you must need a key.";
     }
    public void setAction(){
        
        if(onPath == true){
            
            int goalCol = 12;
            int goalRow = 9;
          //int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
          //int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            
            searchPath(goalCol, goalRow);
        }
        else{
             actionLockedCounter++;
        
            if(actionLockedCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
        
                if(i <= 25){
                    direction = "up";
                }
                if(i > 25 && i <= 50){
                    direction = "down";
                }
                if(i > 50 && i <= 75){
                    direction = "left";
                }
                if(i > 75 && i <= 100){
                    direction = "right";
                }
        
            actionLockedCounter = 0;
        }
        }
       
    }
    public void speak(){
        
       facePlayer();
       startDialogue(this,dialogueSet);
        
       dialogueSet++;
       
       if(dialogues[dialogueSet][0] == null){
           
           dialogueSet--;
       }
       // onPath = true;
    }
}