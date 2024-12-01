package Object;

import Entity.Entity;
import Main.GamePanel;



public class ObjectBoots extends Entity{
    
    public static final String objName = "Boots";

    GamePanel gp;
    
    public ObjectBoots(GamePanel gp){
        super(gp);
        
    name = objName;
    Down1 = setup("/res/objects/boots",gp.tileSize, gp.tileSize);
    collision = true;
    description = "[" + name + "]\nIncreases Speed";
    price = 20;
    }
    
}

