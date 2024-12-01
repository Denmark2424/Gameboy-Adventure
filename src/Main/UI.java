package Main;

import Entity.Entity;
import Object.ObjectCoin;
import Object.ObjectHeart;
import Object.ObjectManaCrystal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.util.ArrayList;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_50;
    BufferedImage heart_full, heart_half, heart_blank, manacrystal_full,manacrystal_blank,coin;
    public boolean messageOn = false;
  //  public String message = "";
  //  int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int playerslotCol = 0;
    public int playerslotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";
    
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_50 = new Font("Arial", Font.BOLD, 50);
        
        
        //Create HUD object
        Entity heart = new ObjectHeart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new ObjectManaCrystal(gp);
        manacrystal_full = crystal.image;
        manacrystal_blank = crystal.image2;
        Entity bronzecoin = new ObjectCoin(gp);
        coin = bronzecoin.Down1;
    }
    
    public void addMessage(String text){
        
       // message = text;
       // messageOn = true;
       
       message.add(text);
       messageCounter.add(0);
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        //TitleState
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        
        //PlayState
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
            
        }
        //PauseState
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
            
        }
        //DialogueState
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
            
        }
        //Character State
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player,true);
            
        }
        // Option State
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }
        
        // Game Over State
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        // Transition State
        if(gp.gameState == gp.transitionState){
            drawTransition();
        }
        // Trade State
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        // Sleep State
        if(gp.gameState == gp.sleepState){
            drawSleepScreen();
        }
        
    }
    public void  drawPlayerLife(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        //Draw Max Heart
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        //Reset
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;
         
         //Draw Current Life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
         
        // Draw Max Mana
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(manacrystal_blank, x, y, null);
            i++;
            x += 35;
        }
        
         // Draw Max Mana
        x = (gp.tileSize/2)-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(manacrystal_full, x, y, null);
            i++;
            x += 35;
        }
    }
    public void drawMessage(){
        
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        
        for(int i = 0; i < message.size(); i++){
            
            if(message.get(i) != null){
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+2, messageY+2);
                
                g2.setColor(Color.white);
                g2.drawString(message.get(i),messageX, messageY);
                
                int counter = messageCounter.get(i) +1;
                messageCounter.set(i, counter);
                messageY += 50;
                
                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
                
                
            }
        }
    }
    public void drawTitleScreen(){
        
        // Title Screen Background
        //g2.setColor(new Color(70, 120, 80));
        //g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        // Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String text = "GameBoy Adventure";
        int textLength = 0;
        int x = getX(text);
        int y = gp.tileSize*3;
        
        //Shadow Color
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        
        //Main Color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        
        // Displaying player image
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.Down1,x ,y ,gp.tileSize*2, gp.tileSize*2, null);
        
        //Menu Game
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        
        text = "NEW GAME";
        x = getX(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        text = "LOAD GAME";
        x = getX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        text = "QUIT";
        x = getX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    public void drawPauseScreen(){
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        String text = "PAUSED";
        int x = getX(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        
        //Dialogue Window
        int x = gp.tileSize*3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*6);
        int height = gp.tileSize*4;
        
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null){
            
//            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            
            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            
            if(charIndex < characters.length){
                
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }
            
            if(gp.keyH.enterPressed == true){
                
                charIndex = 0;
                combinedText = "";
                
                if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState){
                    
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else{
            npc.dialogueIndex = 0;
            
            if(gp.gameState == gp.dialogueState){
                gp.gameState = gp.playState;
            }
            if(gp.gameState == gp.cutsceneState){
                gp.csManager.scenePhase++;
            }
        }
        
        
        for (String line : currentDialogue.split("\n")){
            
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen(){
        
        //Create a frame
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // Text Window
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(25F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        // Name
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;        
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        
        // Values
        int tailX = (frameX + frameWidth) - 30;
        
        // Reset TextY
        textY = frameY + gp.tileSize;
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        
        value = String.valueOf(gp.player.strength);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.dexterity);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.defense);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.exp);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.coin);
        textX = getXAlligned(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.Down1,tailX - gp.tileSize,textY - 24,null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.Down1,tailX - gp.tileSize,textY - 24,null);


        
    }
    public void drawInventory(Entity entity, boolean cursor){
     
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        
        if(entity == gp.player){
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = playerslotCol;
            slotRow = playerslotRow;
        }
        else{
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        
        // Frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;
        
        // Draw Player Items
        for(int i = 0; i < entity.inventory.size(); i++){
            
            // Equip cursor
            if(entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield
              || entity.inventory.get(i) == entity.currentLight){
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).Down1, slotX, slotY, null);
            
            // Display Amount
            if(entity == gp.player && entity.inventory.get(i).amount > 1){
                
                g2.setFont(g2.getFont().deriveFont(25f));
                int amountX;
                int amountY;
                
                String s = "" + entity.inventory.get(i).amount;
                amountX = getXAlligned(s, slotX + 44);
                amountY = slotY + gp.tileSize;
                
                // Shadow
                g2.setColor(new Color(60,60,60));
                g2.drawString(s,amountX,amountY);
                
                // Number
                g2.setColor(Color.white);
                g2.drawString(s,amountX-3,amountY-3);
            }
            
            slotX += slotSize;
            
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        // Cursor
        if(cursor == true){
            
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
        
            // Draw Cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        
            // Description Frame
            int dframeX = frameX;
            int dframeY = frameY + frameHeight;
            int dframeWidth = frameWidth;
            int dframeHeight = gp.tileSize*3;
        
            // Draw Description Text
            int textX = dframeX + 20;
            int textY = dframeY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(25F));
        
            int itemIndex = getItemIndex(slotCol,slotRow);
        
            if(itemIndex < entity.inventory.size()){
                drawSubWindow(dframeX, dframeY, dframeWidth, dframeHeight);

                for(String line: entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line, textX, textY);
                    textY += 32;
            }
        }
        }
        
    }
    public void drawGameOverScreen(){
        
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        
        text = "Game Over!";
        
        // Shadow
        g2.setColor(Color.black);
        x = getX(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4,y-4);
        
        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
        }
        
        // Back to the title Screen
        text = "Quit";
        x = getX(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }
        
    }
    public void drawOptionsScreen(){
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(25f));
        
        // Sub Window
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        switch(subState){
            case 0: options_top(frameX,frameY); break;
            case 1: options_FullScreenNotification(frameX,frameY); break;
            case 2: options_control(frameX, frameY);break;
            case 3: options_endGameConfirmation(frameX, frameY);break;

        }
        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        
        int textX; 
        int textY;
        
        // Title
        String text = "Options";
        textX = getX(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        // Full Screen ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }  
        
        // Control
        textY += gp.tileSize*1.50;
        g2.drawString("Control", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
              if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }
        
        // End Game
        textY += gp.tileSize*1.50;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;
            }
        }
        
        // Back
        textY += gp.tileSize*2.50;
        g2.drawString("Back", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
        // Full Screen Check Box
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY,24,24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY,24,24);
        }
        
        gp.config.saveConfig();
    }
    public void options_FullScreenNotification(int frameX, int frameY){
        
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        
        currentDialogue = "The change will take \neffect after restarting \nthe game.";
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        
        // Back
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY){
        
        int textX;
        int textY;
        
        // Title
        String text = "Control";
        textX = getX(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Block", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WSAD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("SPACE", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY){
        
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        
        currentDialogue = "Quit the game and \nreturn to the title screen";
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        
        // Yes
        String text = "Yes";
        textX = getX(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
        // No
        text = "No";
        textX = getX(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public void drawTransition(){
     
        counter ++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);
        
        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            
        }
    }
    public void drawTradeScreen(){
        
        switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select(){
        
        npc.dialogueSet = 0;
        drawDialogueScreen();
        
        // Draw Window
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);
        
        // Draw Texts
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if(commandNum == 0){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }
        y += gp.tileSize;
        
        g2.drawString("Sell", x, y);
        if(commandNum == 1){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += gp.tileSize;
        
        g2.drawString("Leave", x, y);
        if(commandNum == 2){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                commandNum = 0;
                npc.startDialogue(npc,1);
            }
    }
}
    public void trade_buy(){
        
        // Draw Player Inventory
        drawInventory(gp.player,false);
        // Draw NPC Inventory
        drawInventory(npc,true);
        
        // Draw Hint Window
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);
        
        // Draw Player Coin Window
         x = gp.tileSize*12;
         y = gp.tileSize*9;
         width = gp.tileSize*6;
         height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your coin: " + gp.player.coin, x+24, y+60);

        // Draw Price Window
        int itemIndex = getItemIndex(npcSlotCol,npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            
            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8,32,32,null);
            
            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXAlligned(text,gp.tileSize*8-20);
            g2.drawString(text, x, y+34);
            
            // Buy an Item
            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    npc.startDialogue(npc,2);
                }
                else{
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                       gp.player.coin -= npc.inventory.get(itemIndex).price;

                    }
                    else{
                         subState = 0;
                         npc.startDialogue(npc,3);

                    }
                }
            }
        }
    
    }
    public void trade_sell(){
        
        // Draw Player Iventory
        drawInventory(gp.player,true);
        
        int x;
        int y;
        int width;
        int height;
        
           // Draw Hint Window
        x = gp.tileSize*2;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);
        
        // Draw Player Coin Window
         x = gp.tileSize*12;
         y = gp.tileSize*9;
         width = gp.tileSize*6;
         height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your coin: " + gp.player.coin, x+24, y+60);

        // Draw Price Window
        int itemIndex = getItemIndex(playerslotCol,playerslotRow);
        if(itemIndex < gp.player.inventory.size()){
            
            x = (int)(gp.tileSize*15.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8,32,32,null);
            
            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x = getXAlligned(text,gp.tileSize*18-20);
            g2.drawString(text, x, y+34);
            
            // Sell an Item
            if(gp.keyH.enterPressed == true){
                
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc,4);

                }
                else{
                    if(gp.player.inventory.get(itemIndex).amount > 1){
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }
            }
    }
}
    public void drawSleepScreen(){
        
        counter ++;
        
        if(counter < 120){
            gp.eManager.lighting.filterAlpha += 0.01f;
            if(gp.eManager.lighting.filterAlpha > 1f){
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if(counter >= 120){
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if(gp.eManager.lighting.filterAlpha <= 0f){
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }
    public int getItemIndex(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35,35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);
        
    }
    public int getX(String text){
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXAlligned(String text, int tailX){
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}





