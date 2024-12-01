/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Monster;

import Entity.Entity;
import Main.GamePanel;
import Object.ObjectCoin;
import Object.ObjectHeart;
import Object.ObjectManaCrystal;
import Object.ObjectRock;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Orc extends Entity{
    
       GamePanel gp;
    
    public Orc(GamePanel gp){
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 16;
        life = maxLife;
        attack = 5;
        defense = 1;
        exp = 8;
        //knockBackPower = 5;
        
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 30;
        motion2_duration = 60;
        
        getImage();
        getAttackImage();
    }
  public void getImage(){
      
      Up1 = setup("/res/monster/orc_up_1",gp.tileSize, gp.tileSize); 
      Up2 = setup("/res/monster/orc_up_2",gp.tileSize, gp.tileSize);
      Down1 = setup("/res/monster/orc_down_1",gp.tileSize, gp.tileSize);
      Down2 = setup("/res/monster/orc_down_2",gp.tileSize, gp.tileSize);
      Left1 = setup("/res/monster/orc_left_1",gp.tileSize, gp.tileSize);
      Left2 = setup("/res/monster/orc_left_2",gp.tileSize, gp.tileSize);
      Right1 = setup("/res/monster/orc_right_1",gp.tileSize, gp.tileSize);
      Right2 = setup("/res/monster/orc_right_1",gp.tileSize, gp.tileSize);

  }
  public void getAttackImage(){
      
            attackUp1 = setup("/res/monster/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/res/monster/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/res/monster/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/monster/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/res/monster/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/res/monster/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/res/monster/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/monster/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
  }
  public void setAction(){
      
      if(onPath == true){

          // Check if it stops chasing
          checkStopChasing(gp.player, 15, 100);

          // Search direction to go
          searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            
      }
      else{
          // Check if it starts chasing
          checkStartChasing(gp.player, 5, 100);
          
         // Get a random direction
         getRandomDirection();
         
         // Check if it's attack
         if(attacking == false){
             checkAttack(30, gp.tileSize*4, gp.tileSize);
         }
      }
   }
  public void damageReaction(){
      
      actionLockedCounter = 0;
     //direction = gp.player.direction;
      onPath = true;
  }
  public void checkDrop(){
      
      // Cast A Die
      int i = new Random().nextInt(100)+1;
      
      // Set The Monster Drop
      if(i < 50){
          dropItem(new ObjectCoin(gp));
      }
      if(i >= 50 && i <= 75){
          dropItem(new ObjectHeart(gp));
      }
      if(i >= 75 && i <= 100){
          dropItem(new ObjectManaCrystal(gp));
      }
      
  }
}
