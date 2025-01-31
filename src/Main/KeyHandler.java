package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed, shotKeyPressed,spacePressed;
    
    // Debug
    boolean showDebugText = false;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        //TitleState
        if(gp.gameState == gp.titleState){
           titleState(code);
    }
     
        //Play State
        else if(gp.gameState == gp.playState){
            playState(code);
       
        }
     //Pause State
        else if(gp.gameState == gp.pauseState){
          pauseState(code);
     }
     
     //Dialogue State
        else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState){
        dialogueState(code);
    }
        //Character State
        else if(gp.gameState == gp.characterState){
           characterState(code);
        }
        // Option State
        else if(gp.gameState == gp.optionsState){
           optionsState(code);
        }
         // Game Over State
        else if(gp.gameState == gp.gameOverState){
           gameOverState(code);
        }
        //Trade State
        else if(gp.gameState == gp.tradeState){
           tradeState(code);
        }
        // Map State
        else if(gp.gameState == gp.mapState){
           mapState(code);
        }
}
    public void titleState(int code){
        
         if (code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
        }
            if (code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
        }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    //gp.playMusic[0];
                }
                if(gp.ui.commandNum == 1){
                    gp.SaveLoad.save();
                    gp.gameState = gp.playState;

                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
    }
    public void playState(int code){
        
         if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
            }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
            }
        if (code == KeyEvent.VK_F){
            shotKeyPressed = true;
            }
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
            }
        if (code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
            }
        if (code == KeyEvent.VK_X){
            if(gp.map.miniMapOn == false){
                gp.map.miniMapOn = true;
            }
            else{
                gp.map.miniMapOn = false;
            }
        }
        if (code == KeyEvent.VK_SPACE){
            spacePressed = true;
            }
  
        // Debug
        if (code == KeyEvent.VK_T){
            if(showDebugText == false){
                showDebugText = true;
            }
            else if(showDebugText == true){
                showDebugText = false;
            }
    }
        if(code == KeyEvent.VK_R){
            switch(gp.currentMap){
                case 0: gp.tileM.loadMap("/res/maps/worldV3.txt", 0);break;
                case 1: gp.tileM.loadMap("/res/maps/interior01.txt", 1);break;

            }
            }
}
    public void pauseState(int code){
        
          if (code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
            }
    }
    public void dialogueState(int code){
        
         if (code == KeyEvent.VK_ENTER){
             enterPressed = true;
         }
    }
    public void characterState(int code){
        
         if(code == KeyEvent.VK_C){
                gp.gameState = gp.playState;
            }
         if(code == KeyEvent.VK_ENTER){
             gp.player.selectItem();
             
         }
         playerInventory(code);
    }
    public void optionsState(int code){
        
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        
        int maxCommandNum = 0;
        
        switch(gp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
            
        }
        
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            //gp.playSE();
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            //gp.playSE(code);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
         if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            //gp.playSE();
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            //gp.playSE(code);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
}
    public void gameOverState(int code){
        
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
            //gp.playSE(code);
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
            //gp.playSE(code);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.resetGame(false);
                //gp.playSE(0);
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
        
    }
    public void tradeState(int code){
        
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        
        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                //gp.playSE(code);
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                //gp.playSE(code);
            }
            if(code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                //gp.playSE(code);
            }
            if(code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                //gp.playSE(code);
            }
        }
        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }
    public void mapState(int code){
        
        if(code == KeyEvent.VK_M){
            gp.gameState = gp.playState;
        }
    }
    public void playerInventory(int code){
        
        if(code == KeyEvent.VK_W){
             if(gp.ui.playerslotRow != 0){
                gp.ui.playerslotRow--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_A){
             if(gp.ui.playerslotCol != 0){
                gp.ui.playerslotCol--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_S){
             if(gp.ui.playerslotRow != 3){
                gp.ui.playerslotRow++;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_D){
             if(gp.ui.playerslotCol != 4){
                gp.ui.playerslotCol++;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_UP){
            if(gp.ui.playerslotRow != 0){
                gp.ui.playerslotRow--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_LEFT){
            if(gp.ui.playerslotCol != 0){
                gp.ui.playerslotCol--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_DOWN){
            if(gp.ui.playerslotRow != 3){
                gp.ui.playerslotRow++;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_RIGHT){
           if(gp.ui.playerslotCol != 4){
                gp.ui.playerslotCol++;
             //gp.playSE(7);
             }
         }
        
    }
     public void npcInventory(int code){
        
        if(code == KeyEvent.VK_W){
             if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_A){
             if(gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_S){
             if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_D){
             if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_UP){
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_LEFT){
            if(gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_DOWN){
            if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
             //gp.playSE(7);
             }
         }
         if(code == KeyEvent.VK_RIGHT){
           if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
             //gp.playSE(7);
             }
         }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F){
            shotKeyPressed = false;
            }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
            }
        if (code == KeyEvent.VK_SPACE){
            spacePressed = false;
            }
    }
    
}
