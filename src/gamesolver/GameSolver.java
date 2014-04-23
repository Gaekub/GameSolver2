/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesolver;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
/**
 *
 * @author Jacob
 */
public class GameSolver {
    
    static boolean runTTT = false;
    static boolean runTTTR = false;
    static boolean runCX = false;
    static boolean runCXR = true;
    static int connect = 4;
    static int height = 4;
    static int width = 4;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        
        if(runTTT){
            Semaphore modifyStateList = new Semaphore(1, true);
            StateData startState  = new StateData(3,3);
            GameState[] gameStates = new GameState[256901116];
            // TODO code application logic here
            GameState start = new TicTacToeConcurrent(startState, gameStates, modifyStateList);


            ExecutorService executorService = null;
            executorService = Executors.newFixedThreadPool(1);

            executorService.execute(start);
            Thread.sleep(2000);
            executorService.shutdown();

            for(int k = 0; k<gameStates.length; k++){
                if(gameStates[k] == null){
                    System.out.println("Contains a total of " + k + " states.");
                    break;
                }
            }
        }
        
        if(runTTTR){
            StateData startState = new StateData(3,3);
            TicTacToeRecursive[] gameStates = new TicTacToeRecursive[256901116];
            // TODO code application logic here
            TicTacToeRecursive start = new TicTacToeRecursive(startState, gameStates);




            for(int k = 0; k<gameStates.length; k++){
                if(gameStates[k] == null){
                    System.out.println("Contains a total of " + k + " states.");
                    break;
                }
            }
        } 
        
        if(runCX){
            Semaphore modifyStateList = new Semaphore(1, true);
            StateData startState = new StateData(height,width);
            GameState[] gameStates = new GameState[256901116];
            // TODO code application logic here
            GameState start = new ConnectXConcurrent(startState, gameStates, modifyStateList, connect);


            ExecutorService executorService = null;
            executorService = Executors.newFixedThreadPool(1);

            executorService.execute(start);
            Thread.sleep(2000);
            executorService.shutdown();

            for(int k = 0; k<gameStates.length; k++){
                if(gameStates[k] == null){
                    System.out.println("Contains a total of " + k + " states.");
                    break;
                }
            }
        }
        
        if(runCXR){
            StateData startState = new StateData(height,width);
            ConnectXRecursive[] gameStates = new ConnectXRecursive[256901116];
            // TODO code application logic here
            ConnectXRecursive start = new ConnectXRecursive(startState, gameStates, connect);

            for(int k = 0; k<gameStates.length; k++){
                if(gameStates[k] == null){
                    System.out.println("Contains a total of " + k + " states.");
                    break;
                }
            }
        }
    }
}
