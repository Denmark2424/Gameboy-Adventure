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
public class ObjectFireball extends Projectile{
    
    public static final String objName = "Fireball";

    GamePanel gp;
    
    public ObjectFireball(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        name = objName;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 5;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        Up1 = setup("/res/projectile/fireball_up_1",gp.tileSize,gp.tileSize);
        Up2 = setup("/res/projectile/fireball_up_2",gp.tileSize,gp.tileSize);
        Down1 = setup("/res/projectile/fireball_down_1",gp.tileSize,gp.tileSize);
        Down2 = setup("/res/projectile/fireball_down_2",gp.tileSize,gp.tileSize);
        Left1 = setup("/res/projectile/fireball_left_1",gp.tileSize,gp.tileSize);
        Left2 = setup("/res/projectile/fireball_left_2",gp.tileSize,gp.tileSize);
        Right1 = setup("/res/projectile/fireball_right_1",gp.tileSize,gp.tileSize);
        Right2 = setup("/res/projectile/fireball_right_2",gp.tileSize,gp.tileSize);

    }
    public boolean haveResources(Entity user){
        
        boolean haveResource = false;
        if(user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResources(Entity user){
        user.mana -= useCost;
    }
     public Color getParticleColor(){
        Color color = new Color(240, 50, 0);
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
