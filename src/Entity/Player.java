package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import Object.ObjectAxe;
import Object.ObjectFireball;
import Object.ObjectKey;
import Object.ObjectRock;
import Object.ObjectShield;
import Object.ObjectSword;
import java.awt.AlphaComposite;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Player extends Entity{
    
    
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCancel = false;
    public boolean lightUpdated = false;
    
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
            
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;      
        
        setDefaultValues();
      
    }
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 9;
        gp.currentMap = 0;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";
        
        //Player Status
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 3;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 10;
        coin = 10;
        currentWeapon = new ObjectAxe(gp);
        currentShield = new ObjectShield(gp);
        currentLight = null;
        projectile = new ObjectFireball(gp);
       // projectile = new ObjectRock(gp);
        attack = getAttack();
        defense = getDefense();
        
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();
        
    }
    public void setDefaultPosition(){
        
        worldX = gp.tileSize* 12;
        worldY = gp.tileSize * 9;
        direction = "down";
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You level up to " + level + "\n"
                    + "Stats increase!";
        
    }
    public void restoreStatus(){
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;

    }
    public void setItems(){
        
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        
        
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
      public int getCurrentShieldSlot(){
        int currentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }
    public void getImage(){
        
        Up1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
        Up2 = setup("/res/player/boy_up_2", gp.tileSize, gp.tileSize);
        Down1 = setup("/res/player/boy_down_1", gp.tileSize, gp.tileSize);
        Down2 = setup("/res/player/boy_down_2", gp.tileSize, gp.tileSize);
        Left1 = setup("/res/player/boy_left_1", gp.tileSize, gp.tileSize);
        Left2 = setup("/res/player/boy_left_2", gp.tileSize, gp.tileSize);
        Right1 = setup("/res/player/boy_right_1", gp.tileSize, gp.tileSize);
        Right2 = setup("/res/player/boy_right_2", gp.tileSize, gp.tileSize);
        
    }
    public void getSleep(BufferedImage image){
        
        
        Up1 = image;
        Up2 = image;
        Down1 = image;
        Down2 = image;
        Left1 = image;
        Left2 = image;
        Right1 = image;
        Right2 = image;
        gp.aSetter.setMonster();

    }
    public void getAttackImage(){
        
        if(currentWeapon.type == type_sword){
            attackUp1 = setup("/res/player/attackup1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/res/player/attackup2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/res/player/attackdown1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/attackdown2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/res/player/attackleft1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/res/player/attackleft2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/res/player/attackright1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/attackright2", gp.tileSize*2, gp.tileSize);
        }   
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("/res/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/res/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/res/player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/res/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/res/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/res/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
        } 
    }
    public void getGuardImage(){
        
        guardUp = setup("/res/player/boy_guard_up", gp.tileSize, gp.tileSize);
        guardDown = setup("/res/player/boy_guard_down", gp.tileSize, gp.tileSize);        
        guardLeft = setup("/res/player/boy_guard_left", gp.tileSize, gp.tileSize);
        guardRight = setup("/res/player/boy_guard_right", gp.tileSize, gp.tileSize);

        
    }
    public void update(){
        
        if(knockBack == true){
            
            collisionOn = false;
            gp.cChecker.checkTile(this);            
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this,gp.npc);        
            gp.cChecker.checkEntity(this,gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);
        
            
            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(collisionOn == false){
                switch(knockBackDirection){
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case"right":
                        worldX += speed;
                        break;   
                }
            }
            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        
        else if(attacking == true){
            attacking();
        }
        else if(keyH.spacePressed == true){
            guarding = true;
            guardCounter++;
        }
        
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            
        if(keyH.upPressed == true){
            direction = "up";
            
        }
        else if(keyH.downPressed == true){
            direction = "down";
           
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            
        }
        else if(keyH.rightPressed == true){
            direction = "right";
            
        }
        
        // Check Tile Collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        // Check NPC collision
        int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
        interactNPC(npcIndex);
        
        // Check Monster Collision
        int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
        contactMonster(monsterIndex);
        
        // Check Interactive Tile Collision
        int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
        
        //Check Event
        gp.eHandler.checkEvent();
        
        // Check object Collision
        int objsIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objsIndex);
        
        // If Collision is false, Player Can Move
        if (collisionOn == false && keyH.enterPressed == false){
            switch(direction){
                case"up":
                    worldY -= speed;
                    break;
                case"down":
                     worldY += speed;
                    break;
                case"left":
                    worldX -= speed;
                    break;
                case"right":
                    worldX += speed;
                    break;    
            }
        }
        
        if(keyH.enterPressed == true && attackCancel == false){
            attacking = true;
            spriteCounter = 0;
        }
        
        attackCancel = false;
        gp.keyH.enterPressed = false;
        guarding = false;
        guardCounter = 0;
        
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        }
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
            guarding = false;
            guardCounter = 0;
        }
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
                && projectile.haveResources(this) == true){
            
            // Set Default Coordinates, Direction and User
            projectile.set(worldX, worldY, direction, true, this);
            
            // Subtract the cost (MANA)
            projectile.subtractResources(this);
            
              // Check Vacancy
              for(int i = 0; i < gp.projectile[1].length; i++){
                  if(gp.projectile[gp.currentMap][i] == null){
                      gp.projectile[gp.currentMap][i] = projectile;
                      break;
                  }
              }

            shotAvailableCounter = 0;
            
           // gp.playSE(8);
        }
        
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter ++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            //gp.stopMusic();
            //gp.playSE(life);
        }
    }
    public void pickUpObject(int i){
        
        if(i != 999){
            
            //PickUp Only Items
            if(gp.objs[gp.currentMap][i].type == type_pickUpOnly){
                
                gp.objs[gp.currentMap][i].use(this);
                gp.objs[gp.currentMap][i] = null;
            }
            // Obstacle
            else if(gp.objs[gp.currentMap][i].type == type_obstacle){
                if(keyH.enterPressed == true){
                    attackCancel = true;
                    gp.objs[gp.currentMap][i].interact();
                }
            }
            
            // Inventory Items
            else{
                String text;
            
            if(canObtainItem(gp.objs[gp.currentMap][i]) == true){
                //gp.playSE(1);
                text = "Got a " + gp.objs[gp.currentMap][i].name + "!";
            }
            else{
             text = "You cannot carry any more!";
            }
            gp.ui.addMessage(text);
            gp.objs[gp.currentMap][i] = null;
            }
            }
            
        }
    public void interactNPC(int i){
        
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attackCancel = true;
                gp.npc[gp.currentMap][i].speak(); 
            }
    }
}
    public void contactMonster(int i){
        
        if(i != 999){
            
            if(invincible == false && gp.monster[gp.currentMap][i].dying == false){
 //               gp.playSE(5);
 
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                
                if(damage < 1){    
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower){
        
        if(i != 999){
            
            if(gp.monster[gp.currentMap][i].invincible == false){
                
   //             gp.playSE(4);
                if(knockBackPower > 0){
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }
                if(gp.monster[gp.currentMap][i].offBalance == true){
                    attack *= 5;
                }
   
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " Damage!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
                
                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("You killed a " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
            
        }
    }
    public void damageInteractiveTile(int i){
        
        if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false){
            
            //gp.iTile[gp.currentMap][i].playSe();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;
            
            // Generate Particle
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);
            
            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyed();
            }
        }
    }
    public void damageProjectile(int i){
        
        if(i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }
    public void checkLevelUp(){
        
        if(exp >= nextLevelExp){
            
            level++;
            nextLevelExp = nextLevelExp*3;
            maxLife += 1;
            strength++;
            dexterity++;
            maxMana += 1;
            attack = getAttack();
            defense = getDefense();
            
           // gp.playSE(6);
            gp.gameState = gp.dialogueState;
            setDialogue();
            startDialogue(this, 0);
        }
    }
    public void selectItem(){
        
        int itemIndex = gp.ui.getItemIndex(gp.ui.playerslotCol,gp.ui.playerslotRow);
        
        if(itemIndex < inventory.size()){
            
            Entity selectedItem = inventory.get(itemIndex);
            
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }
            if(selectedItem.type == type_shield){
                
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_light){
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if(selectedItem.type == type_consumables){
                
                if(selectedItem.use(this) == true){
                    if(selectedItem.amount > 1){
                        selectedItem.amount--;
                    }
                    else{
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
        
    }
    public int searchItemInventory(String itemName){
        
        int itemIndex = 999;
        
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        
        boolean canObtain = false;
        
        Entity newItem = gp.eGenerator.getObject(item.name);
        
        // Check if Stackble
        if(newItem.stackable == true){
            
            int index = searchItemInventory(newItem.name);
            
            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{ // New Item so need check vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
                
            }
        }
        else{ // Not stackble, so check vacancy
            if(inventory.size() != maxInventorySize){
                inventory.add(newItem);
                canObtain = true;
            }
            
        }
        return canObtain;
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            
          BufferedImage image = null;
          int tempScreenX = screenX;
          int tempScreenY = screenY;
          
          switch(direction){
              case "up":
                  if(attacking == false){
                    if(spriteNum == 1){image = Up1;}
                    if(spriteNum == 2){image = Up2;}
                  }
                  if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                  }
                  if(guarding == true){
                      image = guardUp;
                  }
                  break;
              case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = Down1;}
                    if(spriteNum == 2){image = Down2;}
                  }
                if(attacking == true){
                  if(spriteNum == 1){image = attackDown1;}
                  if(spriteNum == 2){image = attackDown2;}
                }
                if(guarding == true){
                      image = guardDown;
                  }
                  break;
              case"left":
                if(attacking == false){
                    if(spriteNum == 1){image = Left1;}
                    if(spriteNum == 2){image = Left2;}
                  }
                if(attacking == true){
                  tempScreenX = screenX - gp.tileSize;
                  if(spriteNum == 1){image = attackLeft1;}
                  if(spriteNum == 2){image = attackLeft2;}
                }
                if(guarding == true){
                      image = guardLeft;
                  }
                  break;
              case"right":
                if(attacking == false){
                    if(spriteNum == 1){image = Right1;}
                    if(spriteNum == 2){image = Right2;}
                  }
                if(attacking == true){
                  if(spriteNum == 1){image = attackRight1;}
                  if(spriteNum == 2){image = attackRight2;}
                }
                if(guarding == true){
                      image = guardRight;
                  }
                  break;
          }
          if(transparent == true){
              g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
          }
          if(drawing == true){
              g2.drawImage(image, tempScreenX , tempScreenY , null);
          }
          
          // reset alpha
          g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

          
          //g2.setColor(Color.red);
          //g2.drawRect(screenX + solidArea.x,screenY + solidArea.y,solidArea.width,solidArea.height);
          
          // Debug
       //   g2.setFont(new Font("Arial", Font.PLAIN, 26));
         // g2.setColor(Color.white);
          //g2.drawString("Invincible:" + invincibleCounter, 10, 400);
}
}
    














