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
public class ObjectTent extends Entity{

    public static final String objName = "Tent";

    GamePanel gp;
    
    public ObjectTent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_consumables;
        name = objName;
        Down1 = setup("/res/objects/tent",gp.tileSize,gp.tileSize);
        description = "[Tent]\nYou can sleep until\nnext morning";
        price = 30;
        stackable = false;
    }
    public boolean use(Entity entity){
        
        gp.gameState = gp.sleepState;
        //gp.playSE(life);
        //gp.player.life = gp.player.maxLife;
        //gp.player.mana = gp.player.maxMana;
        gp.aSetter.setMonster();
        gp.player.getSleep(Down1);
        return false;
    }
}
