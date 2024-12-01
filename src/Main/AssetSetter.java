package Main;

import Entity.NPC_Merchant;
import Entity.NPC_OldMan;
import Monster.Orc;
import Monster.Slime;
import Object.ObjectAxe;
import Object.ObjectChest;
import Object.ObjectCoin;
import Object.ObjectDoor;
import Object.ObjectHeart;
import Object.ObjectKey;
import Object.ObjectLantern;
import Object.ObjectManaCrystal;
import Object.ObjectOnepiece;
import Object.ObjectPotionRed;
import Object.ObjectShieldRare;
import Object.ObjectTent;
import Tile_Interactive.IT_DryTree;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        
       this.gp = gp;
       
    }
    public void setObject(){
        
        int mapNum = 0;
        int i = 0;
        
        gp.objs[mapNum][i] = new ObjectDoor(gp);
        gp.objs[mapNum][i].worldX = gp.tileSize*36;
        gp.objs[mapNum][i].worldY = gp.tileSize*30;
        i++;
        
        gp.objs[mapNum][i] = new ObjectDoor(gp);
        gp.objs[mapNum][i].worldX = gp.tileSize*11;
        gp.objs[mapNum][i].worldY = gp.tileSize*75;
        i++;
        
        gp.objs[mapNum][i] = new ObjectDoor(gp);
        gp.objs[mapNum][i].worldX = gp.tileSize*42;
        gp.objs[mapNum][i].worldY = gp.tileSize*41;
        i++;
        
        gp.objs[mapNum][i] = new ObjectDoor(gp);
        gp.objs[mapNum][i].worldX = gp.tileSize*72;
        gp.objs[mapNum][i].worldY = gp.tileSize*13;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectLantern(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*38;
        gp.objs[mapNum][i].worldY = gp.tileSize*8;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectPotionRed(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*51;
        gp.objs[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectPotionRed(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*65;
        gp.objs[mapNum][i].worldY = gp.tileSize*81;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectKey(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*52;
        gp.objs[mapNum][i].worldY = gp.tileSize*36;
        i++;
        
        gp.objs[mapNum][i] = new ObjectOnepiece(gp);
        gp.objs[mapNum][i].worldX = gp.tileSize*11;
        gp.objs[mapNum][i].worldY = gp.tileSize*71;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectKey(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*31;
        gp.objs[mapNum][i].worldY = gp.tileSize*75;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectKey(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*39;
        gp.objs[mapNum][i].worldY = gp.tileSize*48;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectKey(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*26;
        gp.objs[mapNum][i].worldY = gp.tileSize*46;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectPotionRed(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*67;
        gp.objs[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectPotionRed(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*49;
        gp.objs[mapNum][i].worldY = gp.tileSize*72;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectPotionRed(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*49;
        gp.objs[mapNum][i].worldY = gp.tileSize*64;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectTent(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*79;
        gp.objs[mapNum][i].worldY = gp.tileSize*41;
        i++;
        
        gp.objs[mapNum][i] = new ObjectChest(gp);
        gp.objs[mapNum][i].setLoot(new ObjectShieldRare(gp));
        gp.objs[mapNum][i].worldX = gp.tileSize*92;
        gp.objs[mapNum][i].worldY = gp.tileSize*93;
        i++;
        
    }
    public void setNPC(){
        
        // Map 0
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;
        i++;
        
        // Map 1
        mapNum = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*10;
        gp.npc[mapNum][i].worldY = gp.tileSize*39;
        i++;
        
        mapNum = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*90;
        gp.npc[mapNum][i].worldY = gp.tileSize*10;
        i++;

        
        mapNum = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*10;
        gp.npc[mapNum][i].worldY = gp.tileSize*58;
        i++;
        
        mapNum = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*90;
        gp.npc[mapNum][i].worldY = gp.tileSize*61;
        i++;

    }
    public void setMonster(){
        
        int i = 0;
        int mapNum = 0;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*14;
        gp.monster[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*21;
        gp.monster[mapNum][i].worldY = gp.tileSize*46;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*43;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++; 
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*11;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*9;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*9;
        i++; 
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*40;
        gp.monster[mapNum][i].worldY = gp.tileSize*9;
        i++; 
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*8;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++; 
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*39;
        gp.monster[mapNum][i].worldY = gp.tileSize*41;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*63;
        gp.monster[mapNum][i].worldY = gp.tileSize*33;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*63;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*83;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*83;
        gp.monster[mapNum][i].worldY = gp.tileSize*21;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*73;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*67;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*73;
        gp.monster[mapNum][i].worldY = gp.tileSize*43;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*77;
        gp.monster[mapNum][i].worldY = gp.tileSize*41;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*59;
        gp.monster[mapNum][i].worldY = gp.tileSize*71;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*66;
        gp.monster[mapNum][i].worldY = gp.tileSize*71;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*61;
        gp.monster[mapNum][i].worldY = gp.tileSize*74;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*78;
        gp.monster[mapNum][i].worldY = gp.tileSize*69;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*82;
        gp.monster[mapNum][i].worldY = gp.tileSize*69;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*85;
        gp.monster[mapNum][i].worldY = gp.tileSize*83;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*85;
        gp.monster[mapNum][i].worldY = gp.tileSize*91;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*89;
        gp.monster[mapNum][i].worldY = gp.tileSize*85;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*89;
        gp.monster[mapNum][i].worldY = gp.tileSize*88;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*67;
        i++;
        
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*73;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*30;
        gp.monster[mapNum][i].worldY = gp.tileSize*69;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*9;
        gp.monster[mapNum][i].worldY = gp.tileSize*92;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*12;
        gp.monster[mapNum][i].worldY = gp.tileSize*86;
        i++;
        
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*88;
        i++;
    }
    public void setInteractiveTile(){
        
        int mapNum = 0;
        int i = 0;
        
        
              
    }
}
