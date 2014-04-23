/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesolver;



/**
 *
 * @author Jacob
 */
public class ConnectXRecursive{
    StateData sd;
    int winValue;
    ConnectXRecursive[] stateList;
    ConnectXRecursive[] nextStates;
    
    
    ConnectXRecursive(StateData sdIn, ConnectXRecursive[] stateListIn, int winValueIn){
        stateList = stateListIn;
        sd = sdIn;
        winValue = winValueIn;
        getNextStates();
    }
    
    void getNextStates(){
        if(endState()){
            //System.out.println("Game Over");
            return;
        }
        
        int value = -1;
        if(sd.sum()== 0){
            value = 1;
        }
        
        nextStates = new ConnectXRecursive[sd.data[0].length];
        boolean[] newState = new boolean[sd.data[0].length];
        int newStates = 0;
        StateData sdTemp = sd.copy();
        int nextNum = 0;
        
        for(int i = 0; i<sd.data[0].length; i++){
            int available = getAvailable(i);
            if(available >= 0){
                //System.out.println("Piece can be placed in row " + available + " in column " + i);
                sdTemp.data[available][i] = value;

                //System.out.println("Semaphore Acquired");
                //TicTacToeRecursive tempNext = new TicTacToeRecursive(sdTemp, stateList, modifyStateList);
                //int x = tempNext.isIn(stateList);
                int x = sdTemp.isIn(stateList);
                //System.out.println("StateListChecked");
                if(x>=0){
                    nextStates[nextNum] = stateList[x];
                    newState[nextNum] = false;
                    nextNum++;

                }else{
                    StateData temp2 = sdTemp.copy();
                    nextStates[nextNum] = new ConnectXRecursive(temp2, stateList, winValue);
                    newState[nextNum] = true;
                    nextNum++;
                    newStates++;
                    for(int k = 0; k<stateList.length; k++){
                        if(stateList[k] == null){
                            stateList[k] = nextStates[nextNum - 1];
                            
                            System.out.println("At " + k + " states");
                            break;
                        }
                    }
                }
                    //System.out.println("NextStateFound!");


            }
            sdTemp = sd.copy();
        }
        //System.out.println(nextNum + " possible moves.");
        

        
    }
    
    boolean endState(){
        int adjacentPieces = 0;
        int storedValue = 0;
        
        boolean movesExist = false;
        boolean isEndState = false;
        
        for(int i = 0; i<sd.data.length; i++){
            for(int j = 0; j<sd.data[0].length; j++){
                if(sd.data[i][j] != 0){
                    if(sd.data[i][j] == storedValue){
                        adjacentPieces++;
                        if(adjacentPieces >= winValue){
                            //System.out.println("Connected Horizantally");
                            isEndState = true;
                        }
                    }else{
                        storedValue = sd.data[i][j];
                        adjacentPieces = 1;
                    }
                }else{
                    movesExist = true;
                    storedValue = 0;
                    adjacentPieces = 0;
                }
            }
            adjacentPieces = 0;
            storedValue = 0;
        }
        
        if(!movesExist){
            isEndState = true;
        }
        
        adjacentPieces = 0;
        storedValue = 0;
        
        for(int j = 0; j<sd.data[0].length; j++){
            for(int i = 0; i<sd.data.length; i++){
                if(sd.data[i][j] != 0){
                    if(sd.data[i][j] == storedValue){
                        adjacentPieces++;
                        if(adjacentPieces >= winValue){
                            //System.out.println("Connected Vertically");
                            isEndState = true;
                        }
                    }else{
                        storedValue = sd.data[i][j];
                        adjacentPieces = 1;
                    }
                }else{
                    storedValue = 0;
                    adjacentPieces = 0;
                }
            }
            adjacentPieces = 0;
            storedValue = 0;
        }
        
        int possibleDiagonals = sd.data.length+sd.data[0].length-(2*winValue)+1;
        int heightMod = sd.data.length-winValue;
        int widthMod = 0;
        
        int adjacentPieces1 = 0;
        int storedValue1 = 0;
        
        int adjacentPieces2 = 0;
        int storedValue2 = 0;
        
        for(int i = 0; i< possibleDiagonals; i++){
            for(int j = 0; j<min(sd.data.length, sd.data[0].length); j++){
                if(heightMod+j<sd.data.length && widthMod+j<sd.data[0].length){
                    if(sd.data[heightMod+j][widthMod+j] != 0){
                        if(sd.data[heightMod+j][widthMod+j] == storedValue1){
                            adjacentPieces1++;
                            if(adjacentPieces1 >= winValue){
                                //System.out.println("Connected Diagonally");
                                isEndState = true;
                            }
                        }else{
                            storedValue1 = sd.data[heightMod+j][widthMod+j];
                            adjacentPieces1 = 1;
                        }
                    }else{
                        storedValue1 = 0;
                        adjacentPieces1 = 0;
                    }
                }
                    //System.out.println("HeightMod is " + heightMod);
                if(heightMod+j<sd.data.length && sd.data[0].length - widthMod - 1 - j >= 0){
                    if(sd.data[heightMod+j][sd.data[0].length - widthMod - 1 - j] != 0){
                        if(sd.data[heightMod+j][sd.data[0].length - widthMod - 1 - j] == storedValue2){
                            adjacentPieces2++;
                            if(adjacentPieces2 >= winValue){
                                //System.out.println("Connected Diagonally");
                                isEndState = true;
                            }
                        }else{
                            storedValue2 = sd.data[heightMod+j][sd.data[0].length - widthMod - 1 - j];
                            adjacentPieces2 = 1;
                        }
                    }else{
                        storedValue2 = 0;
                        adjacentPieces2 = 0;
                    }
                }
            }
            
            adjacentPieces1 = 0;
            storedValue1 = 0;
        
            adjacentPieces2 = 0;
            storedValue2 = 0;
            
            if(heightMod > 0){
                heightMod--;
            }else{
                widthMod++;
            }
        }
        
        
        
        
        
        return isEndState;
    }
    
    int max(int x, int y){
        if(x>y)
            return x;
        return y;
    }
    
    int min(int x, int y){
        if(x>y){
            return y;
        }
        return x;
    }
    
    int getAvailable(int column){
        for(int i = 0; i<sd.data.length; i++){
            if(sd.data[i][column] == 0){
                return i;
            }
        }
        return -1;
    }
}
