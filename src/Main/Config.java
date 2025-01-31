/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class Config {
    
    GamePanel gp;
    
    public Config(GamePanel gp){
        this.gp = gp;
        
    }
    public void saveConfig(){
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            
            // Full Screen
            if(gp.fullScreenOn == true){
                bw.write("On");
            }
            if(gp.fullScreenOn == false){
                bw.write("Off");
            }
            bw.newLine();
            
            
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public void loadConfig(){
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            
            String s = br.readLine();
            
            // Full Screen
            if(s.equals("On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreenOn = false;
            }
            
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();

        }
        
    }
}
