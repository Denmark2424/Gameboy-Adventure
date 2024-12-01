/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import Entity.Entity;
import Entity.Projectile;
import Main.GamePanel;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class ObjectRock extends Projectile{
    
    public static final String objName = "Rock";

    GamePanel gp;
    
    public ObjectRock(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        name = objName;
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        Up1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Up2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Down1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Down2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Left1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Left2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Right1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        Right2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);

    }
    public boolean haveResources(Entity user){
        
        boolean haveResource = false;
        if(user.ammo >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResources(Entity user){
        user.ammo -= useCost;
    }
        public Color getParticleColor(){
        Color color = new Color(40, 50, 0);
        return color;
    }
    public int getParticleSize(){
        int size = 10;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
    
