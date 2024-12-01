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
public class ObjectCoin extends Entity{
    
    public static final String objName = "Bronze Coin";

    GamePanel gp;
    
    public ObjectCoin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_pickUpOnly;
        name = objName;
        value = 2;
        Down1 = setup("/res/objects/coin_bronze", gp.tileSize, gp.tileSize);
        
    }
    public boolean use(Entity entity){
        
        //gp.playSE(0);
        gp.ui.addMessage("Coin + " + value);
        gp.player.coin += value;
        return true;
    }
    
}
