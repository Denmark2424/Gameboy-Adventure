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
public class Slime extends Entity{
    GamePanel gp;
    
    public Slime(GamePanel gp){
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 2;
        projectile = new ObjectRock(gp);
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
  public void getImage(){
      
      Up1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize); 
      Up2 = setup("/res/monster/greenslime_down_2",gp.tileSize, gp.tileSize);
      Down1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
      Down2 = setup("/res/monster/greenslime_down_2",gp.tileSize, gp.tileSize);
      Left1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
      Left2 = setup("/res/monster/greenslime_down_2",gp.tileSize, gp.tileSize);
      Right1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
      Right2 = setup("/res/monster/greenslime_down_2",gp.tileSize, gp.tileSize);

  }
  public void setAction(){
      
      if(onPath == true){

          // Check if it stops chasing
          checkStopChasing(gp.player, 15, 100);

          // Search direction to go
          searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            
            // Check if it shoots projectile
           checkShoot(200,30);
            
      }
      else{
          // Check if it starts chasing
          checkStartChasing(gp.player, 5, 100);
          // Get a random direction
          getRandomDirection();
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
