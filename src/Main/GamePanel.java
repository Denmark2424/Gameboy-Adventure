package Main;

import Entity.Entity;
import Entity.Player;
import Environment.EnvironmentManager;
import Tile.Map;
import Tile.TileManager;
import Tile_Interactive.InteractiveTile;
import ai.Pathfinder;
import data.SaveLoad;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int MaxScreenCol = 20;
    public final int MaxScreenRow = 12;
    public final int screenWidth = tileSize * MaxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * MaxScreenRow; // 576 pixels
    
    //World Map Settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;
    
    
    // For Full Screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
    // FPS
    int FPS = 60;
    
    // System
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public Pathfinder pFinder = new Pathfinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);
    SaveLoad SaveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread; //Keeps program to run
    
    // Entity and Object
    public Player player = new Player(this,keyH);
    public Entity objs[][] = new Entity[maxMap][50];
    public Entity npc[][] = new Entity[maxMap][50];
    public Entity monster[][] = new Entity[maxMap][50];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public Entity projectile[][] = new Entity[maxMap][20];
    //public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    
    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutsceneState = 11;
    
    public GamePanel (){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();
        
        //playMusic(0);
        //stopMusic();
        gameState = titleState;
        
       tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
       g2 = (Graphics2D) tempScreen.getGraphics();
       
       if(fullScreenOn == true){
           setFullScreen();
       }
    }
    public void resetGame(boolean restart){
        
        player.setDefaultPosition();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();
        
        if(restart == true){
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();
            
        }
    }
    public void setFullScreen(){
        
        // Get Local Screen Device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        
        // Get Full Screen Width and Height
        screenWidth2 =  Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
       
        double drawInterval = 1000000000/FPS; // 0.16666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        
        while(gameThread != null){
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
               
                update();
//              repaint();
                drawToTempScreen();
                drawToScreen();
                delta--;
                drawCount++;
                
            }
           
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        
        if(gameState == playState){
            //Player
            player.update();
            //NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
             for(int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null){
                    if(projectile[currentMap][i].alive == true){
                        projectile[currentMap][i].update();
                    }
                    if(projectile[currentMap][i].alive == false){
                       projectile[currentMap][i] = null;
                    }
                }
            }
             
              for(int i = 0; i <particleList.size(); i++){
                if(particleList.get(i) != null){
                    if(particleList.get(i).alive == true){
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }
             for(int i = 0; i < iTile[1].length; i++){
                 if(iTile[currentMap][i] != null){
                     iTile[currentMap][i].update();
                 }
             }
             eManager.update();
        }
        if(gameState == pauseState){
            //nothing
        }
       
    }
    public void drawToTempScreen(){
        
         // Debug
        long drawStart = 0;
        if(keyH.showDebugText == true){
            drawStart = System.nanoTime();
        }
        
        //title screen
        if(gameState == titleState){
            ui.draw(g2);
        }
        // Map Screen 
        else if(gameState == mapState){
            map.drawFullMapScreen(g2);
        }
        //others
        else{
        // tile
        tileM.draw(g2);
        
        //Interactive Tile
        for(int i = 0; i < iTile[1].length; i++){
            if(iTile[currentMap][i] != null){
                iTile[currentMap][i].draw(g2);
            }
        }
        
        // Adds entity to the list
        entityList.add(player);
        
        for(int i = 0; i < npc[1].length; i++){
            if(npc[currentMap][i] != null){
                entityList.add(npc[currentMap][i]);
            }
        }
        for(int i = 0; i < objs[1].length; i++){
            if(objs[currentMap][i] != null){
                entityList.add(objs[currentMap][i]);
            }
        }
        for(int i = 0; i < monster[1].length; i++){
            if(monster[currentMap][i] != null){
                entityList.add(monster[currentMap][i]);
            }
        }
        for(int i = 0; i < projectile[1].length; i++){
            if(projectile[currentMap][i] != null){
                entityList.add(projectile[currentMap][i]);
            }
        }
        for(int i = 0; i < particleList.size(); i++){
            if(particleList.get(i) != null){
                entityList.add(particleList.get(i));
            }
        }
        
        
        // Sort
        Collections.sort(entityList, new Comparator<Entity>(){
            
            @Override
            public int compare(Entity e1, Entity e2){
                
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
            
        });
        
        // Draw Entities
        for(int i = 0; i < entityList.size(); i++){
            entityList.get(i).draw(g2);
        }
        // Empty Entity List
        entityList.clear();
        
        // Environment
        eManager.draw(g2);
        
        // Mini Map
        map.drawMiniMap(g2);
        
        // Cutscene
        csManager.draw(g2);
        
        // UI
        ui.draw(g2);
        
        }
        
        // Debug
        if(keyH.showDebugText == true){
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        
        g2.setFont(new Font("Arial", Font.PLAIN,20));
        g2.setColor(Color.white);
        int x = 10;
        int y = 400;
        int lineHeight = 20;
        
        g2.drawString("WorldX: " + player.worldX,x,y); y+= lineHeight;
        g2.drawString("WorldY: " + player.worldY,x,y); y+= lineHeight;
        g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x,y); y+= lineHeight;
        g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize,x,y); y+= lineHeight;
        g2.drawString("Draw Time: " + passed, x, y);
        }
    }
    public void drawToScreen(){
        
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0, screenWidth2, screenHeight2,null);
        g.dispose();
    }
    public void playMusic(int i){
        
        sound.setFile(i);
        //sound.play();
        //sound.stop();
    }
    public void stopMusic(){
        
        sound.stop();
    }
    public void playSE(int i){
        
        sound.setFile(i);
        sound.play();
    }
}
