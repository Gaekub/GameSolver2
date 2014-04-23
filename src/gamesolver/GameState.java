/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesolver;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
/**
 *
 * @author Jacob
 */
public class GameState implements Runnable{
    StateData sd;
    GameState[] nextStates;
    GameState[] stateList;
    Semaphore modifyStateList;
    
    GameState(StateData sdIn, GameState[] stateListIn, Semaphore modifyStateListIn){
        sd = sdIn;
        stateList = stateListIn;
        modifyStateList = modifyStateListIn;
    }
    
    @Override public void run(){
        getNextStates();
    }
    
    void getNextStates(){
        
    }
    
    int stateDataSum(){
        int sum = 0;
        for(int i=0; i<sd.data.length; i++){
            for(int j=0; j<sd.data[i].length; j++){
                sum += sd.data[i][j];
            }
        }
        
        return sum;
    }
    
    boolean endState(){
        return false;
    }
}
