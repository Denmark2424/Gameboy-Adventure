/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tile_Interactive;

import Main.GamePanel;

/**
 *
 * @author Administrator
 */
public class IT_Trunk extends InteractiveTile{
    
    GamePanel gp;
    
    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        
        this.gp = gp;
        
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        
        Down1 = setup("/res/tile_interactive/trunk", gp.tileSize, gp.tileSize);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    
}
