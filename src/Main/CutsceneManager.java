/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.ObjectOnepiece;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Administrator
 */
public class CutsceneManager {
    
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;
    
    // Scene Number
    public final int NA = 0;
    public final int ending = 1;
    
    public CutsceneManager(GamePanel gp){
        this.gp = gp;
        
        endCredit = "BSIT 3B\n"+
                "TechTrek4\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "Special Thanks to:\n" +
                "Our Classmates\n" +
                "Sir Philip\n" + 
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "Thank You for Playing!";
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        switch(sceneNum){
            case ending: 
                scene_ending();
                break;
        }
    }
    public void scene_ending(){
        
        if(scenePhase == 0){
            
            gp.ui.npc = new ObjectOnepiece(gp);
            scenePhase++;
        }
        if(scenePhase == 1){
            
            // Display Dialogues
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 2){
            if(counterReached(300) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 3){
            
            // The scene gets darker
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlack(alpha);
            
            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 4){
            
            drawBlack(alpha);
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            
            String text = "After battling with the monsters,\n"
                    + "Jimboy finally found the legendary treasure.\n"
                    + "But this is not the end of his journey.\n"
                    + "The era of adventurer's has just began.";
            drawString(alpha, 25f, 200,text,70);
            
            if(counterReached(600) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 5){
            drawBlack(1f);
            
            drawString(1f,60f,gp.screenHeight/2,"Gameboy Adventure",40);
            
            if(counterReached(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 6){
            
            drawBlack(1f);
            
            y = gp.screenHeight/2;
            drawString(1f,25f,y,endCredit,40);
            
            if(counterReached(480) == true){
                scenePhase++;
            }
    }
        if(scenePhase == 7){
            
            drawBlack(1f);
            
            // Scrolling the Credits
            y--;
            drawString(1f,25f,y,endCredit,40);

        }
 }
    public boolean counterReached(int target){
        
        boolean counterReached = false;
        
        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlack(float alpha){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n")){
            int x = gp.ui.getX(line);
            g2.drawString(line,x,y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
}
