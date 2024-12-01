/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import Entity.Entity;
import Main.GamePanel;
import Object.ObjectAxe;
import Object.ObjectBoots;
import Object.ObjectChest;
import Object.ObjectDoor;
import Object.ObjectKey;
import Object.ObjectLantern;
import Object.ObjectPotionRed;
import Object.ObjectShield;
import Object.ObjectShieldRare;
import Object.ObjectSword;
import Object.ObjectTent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Administrator
 */
public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp){
        this.gp = gp;
        
    }
    public void save(){
        
        try{
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;
            
            // Player Inventory
            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemName.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            
            // Player Equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
            
            // Objects On Map
            ds.mapObjectName = new String[gp.maxMap][gp.objs[1].length];
            ds.mapObjectWorldX = new int [gp.maxMap][gp.objs[1].length];
            ds.mapObjectWorldY = new int [gp.maxMap][gp.objs[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.objs[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.objs[1].length];
            
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                
                for(int i = 0; i < gp.objs[1].length; i++){
                    
                    if(gp.objs[mapNum][i] == null){
                        ds.mapObjectName[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapObjectName[mapNum][i] = gp.objs[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.objs[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.objs[mapNum][i].worldY;
                        if(gp.objs[mapNum][i].loot != null){
                            ds.mapObjectLootNames[mapNum][i] = gp.objs[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.objs[mapNum][i].opened;
                    }
                }
            }
            
            
            // Write the datastorage object
            oos.writeObject(ds);
            
        }
        catch(Exception e){
            System.out.println("Save Exception!");
        }
    }
    public void load(){
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            // Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
            
            // Player Inventory
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemName.size(); i++){
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemName.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            // Player Equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();
            
            // Objects on Map
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                
                for(int i = 0; i < gp.objs[1].length;i++){
                    
                    if(ds.mapObjectName[mapNum][i].equals("NA")){
                        gp.objs[mapNum][i] = null;
                }
                    else{
                        gp.objs[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectName[mapNum][i]);
                        gp.objs[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.objs[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapObjectLootNames[mapNum][i] != null){
                            gp.objs[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.objs[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.objs[mapNum][i].opened == true){
                            gp.objs[mapNum][i].Down1 = gp.objs[mapNum][i].image2;
                        }
                    }
            }
        }
    }
        catch(Exception e){
            System.out.println("Load Exception!");
            }
        }
    }
