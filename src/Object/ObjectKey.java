package Object;

import Entity.Entity;
import Main.GamePanel;



public class ObjectKey extends Entity{
    
    public static final String objName = "Key";

    GamePanel gp;
    
    public ObjectKey(GamePanel gp){
        super(gp);
        this.gp = gp;
        
    type = type_consumables;    
    name = objName;
    Down1 = setup("/res/objects/key",gp.tileSize, gp.tileSize);
    description = "[" + name + "]\nTo unlock doors.";
    price = 20;
    stackable = true;
    
    setDialogue();
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You use the " + name + " and open the door";
        dialogues[1][0] = "What are you doing?";
    }
    public boolean use(Entity entity){
        
        
        int objIndex = getDetected(entity, gp.objs, "Door");
        
        if(objIndex != 999){
            startDialogue(this,0);
            //gp.playSE(life);
            gp.objs[gp.currentMap][objIndex] = null;
            return true;
        }
        else{
            startDialogue(this,1);
            return false;
        }
    }
}
