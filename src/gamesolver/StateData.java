/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesolver;

/**
 *
 * @author Jacob
 */
public class StateData {
    int[][] data;
    
    StateData(int[][] input){
        data = input;
    }
    
    StateData(int x, int y){
        data = new int[x][y];
        for(int i = 0; i<x; i++){
            for(int j = 0; j<y; j++){
                data[i][j] = 0;
            }
        }
    }
    public StateData copy(){
        int[][] temp = new int[data.length][data[0].length];
        
        for(int i = 0; i<data.length; i++){
            for(int j = 0; j<data[i].length; j++){
                temp[i][j] = data[i][j];
            }
        }
        
        return new StateData(temp);
    }
    
    public boolean compare(StateData other){
        if(other.data.length != data.length){
            return false;
        }
        
        for(int i = 0; i<data.length; i++){
            if(other.data[i].length != data[i].length){
                return false;
            }
            for(int j = 0; j<data[i].length; j++){
                if(other.data[i][j] != data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int isIn(GameState[] array){
        int index = -1;
        
        for(int i = 0; i<array.length; i++){
            if(array[i] == null){
                break;
            }
            
            
            if(array[i].sd.compare(this)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int isIn(TicTacToeRecursive[] array){
        int index = -1;
        
        for(int i = 0; i<array.length; i++){
            if(array[i] == null){
                break;
            }
            
            
            if(array[i].sd.compare(this)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int isIn(TicTacToeIterative[] array){
        int index = -1;
        
        for(int i = 0; i<array.length; i++){
            if(array[i] == null){
                break;
            }
            
            
            if(array[i].sd.compare(this)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int isIn(ConnectXRecursive[] array){
        int index = -1;
        
        for(int i = 0; i<array.length; i++){
            if(array[i] == null){
                break;
            }
            
            
            if(array[i].sd.compare(this)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int sum(){
        int sum = 0;
        
        for(int i = 0; i<data.length; i++){
            for(int j = 0; j<data[i].length; j++){
                sum += data[i][j];
            }
        }
        return sum;
    }
    
    
}
