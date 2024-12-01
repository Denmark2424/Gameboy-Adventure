/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Main.GamePanel;
import Object.ObjectAxe;
import Object.ObjectBoots;
import Object.ObjectKey;
import Object.ObjectLantern;
import Object.ObjectPotionRed;
import Object.ObjectShield;
import Object.ObjectShieldRare;
import Object.ObjectSword;

/**
 *
 * @author Administrator
 */
public class NPC_Merchant extends Entity{
    
      public NPC_Merchant(GamePanel gp){
        super(gp);
        
        direction = "down";
        speed = 1;
        
        getImage();
        setDialogue();
        setItems();
    }
     public void getImage(){
        
        Up1 = setup("/res/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        Up2 = setup("/res/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        Down1 = setup("/res/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        Down2 = setup("/res/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        Left1 = setup("/res/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        Left2 = setup("/res/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        Right1 = setup("/res/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        Right2 = setup("/res/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        
    }
     public void setDialogue(){
         
         dialogues[0][0] = "Ho ho, Welcome my dear costumer.\nI have some good stuff.\nDo you want to trade?";
         dialogues[1][0] = "Come again, hoho!";
         dialogues[2][0] = "You need more coin to buy that!";
         dialogues[3][0] = "You cannot carry any more!";
         dialogues[4][0] = "You cannot sell an equipped item!";

     }
     public void setItems(){
         
         inventory.add(new ObjectPotionRed(gp));
         inventory.add(new ObjectKey(gp));
         inventory.add(new ObjectSword(gp));
         inventory.add(new ObjectShieldRare(gp));
         inventory.add(new ObjectLantern(gp));

     }
     public void speak(){
         
         facePlayer();
         gp.gameState = gp.tradeState;
         gp.ui.npc = this;
         
     }
}
