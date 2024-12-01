/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Entity.Entity;
import Object.ObjectAxe;
import Object.ObjectBoots;
import Object.ObjectChest;
import Object.ObjectCoin;
import Object.ObjectDoor;
import Object.ObjectFireball;
import Object.ObjectHeart;
import Object.ObjectKey;
import Object.ObjectLantern;
import Object.ObjectManaCrystal;
import Object.ObjectPotionRed;
import Object.ObjectRock;
import Object.ObjectShield;
import Object.ObjectShieldRare;
import Object.ObjectSword;
import Object.ObjectTent;

/**
 *
 * @author Administrator
 */
public class EntityGenerator {
    
    GamePanel gp;
    
    public EntityGenerator(GamePanel gp){
        this.gp = gp;
        
        
    }
    public Entity getObject(String itemName){
        
        Entity objs = null;
        
        switch(itemName){
            case ObjectAxe.objName:
                objs = new ObjectAxe(gp);
                break;
            case ObjectBoots.objName:
                objs = new ObjectBoots(gp);
                break;
            case ObjectChest.objName:
                objs = new ObjectChest(gp);
                break;
            case ObjectTent.objName:
                objs = new ObjectTent(gp);
                break;
            case ObjectDoor.objName:
                objs = new ObjectDoor(gp);
                break;
            case ObjectKey.objName:
                objs = new ObjectKey(gp);
                break;
            case ObjectLantern.objName:
                objs = new ObjectLantern(gp);
                break;
            case ObjectPotionRed.objName:
                objs = new ObjectPotionRed(gp);
                break;
            case ObjectShield.objName:
                objs = new ObjectShield(gp);
                break;
            case ObjectShieldRare.objName:
                objs = new ObjectShieldRare(gp);
                break;
            case ObjectSword.objName:
                objs = new ObjectSword(gp);
                break;
            case ObjectCoin.objName:
                objs = new ObjectCoin(gp);
                break;
            case ObjectFireball.objName:
                objs = new ObjectFireball(gp);
                break;
            case ObjectHeart.objName:
                objs = new ObjectHeart(gp);
                break;
            case ObjectManaCrystal.objName:
                objs = new ObjectManaCrystal(gp);
                break;
            case ObjectRock.objName:
                objs = new ObjectRock(gp);
                break;
        }
        return objs;
    }
}
