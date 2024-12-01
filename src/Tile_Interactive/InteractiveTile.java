/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tile_Interactive;

import Entity.Entity;
import Main.GamePanel;
import java.awt.Graphics2D;

/**
 *
 * @author Administrator
 */
public class InteractiveTile extends Entity{
    
    GamePanel gp;
    public boolean destructible = false;
    
    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
        
    }
    public void playSe(){
        
    }
    public InteractiveTile getDestroyed(){
        InteractiveTile tile = null;
        return tile;
    }
    public void update(){
        
        if(invincible == true){
            
            invincibleCounter ++;
            if(invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            
            g2.drawImage(Down1,screenX,screenY,null);
        }

    }
}
