/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesolver;

/**
 *
 * @author Jacob
 */
public class TicTacToeRecursive {
    StateData sd;
    TicTacToeRecursive[] stateList;
    TicTacToeRecursive[] nextStates;
    
    TicTacToeRecursive(StateData sdIn, TicTacToeRecursive[] stateListIn){
        sd = sdIn;
        stateList = stateListIn;
        
        getNextStates();
    }
    
    void getNextStates(){
        if(endState()){
            //System.out.println("Game Complete!");
            return;
        }
        
        int value = -1;
        if(sd.sum() == 0){
            value = 1;
        }
        
        nextStates = new TicTacToeRecursive[9];
        boolean[] newState = new boolean[9];
        int newStates = 0;
        StateData sdTemp = sd.copy();
        int nextNum = 0;
        
        for(int i = 0; i<sd.data.length; i++){
            for(int j = 0; j<sd.data[i].length; j++){
                if(sd.data[i][j] == 0){
                    sdTemp.data[i][j] = value;
                    //System.out.println("Semaphore Acquired");
                    //TicTacToeConcurrent tempNext = new TicTacToeConcurrent(sdTemp, stateList, modifyStateList);
                    //int x = tempNext.isIn(stateList);
                    int x = sdTemp.isIn(stateList);
                    //System.out.println("StateListChecked");
                    if(x>=0){
                        nextStates[nextNum] = stateList[x];
                        newState[nextNum] = false;
                        nextNum++;

                    }else{
                        StateData temp2 = sdTemp.copy();
                        nextStates[nextNum] = new TicTacToeRecursive(temp2, stateList);
                        newState[nextNum] = true;
                        nextNum++;
                        newStates++;
                        for(int k = 0; k<stateList.length; k++){
                            if(stateList[k] == null){
                                stateList[k] = nextStates[nextNum - 1];
                                break;
                            }
                        }
                    }
                    //System.out.println("NextStateFound!");
                    sdTemp.data[i][j] = 0;
                }
            }
        }
        //System.out.println(nextNum + " possible moves.");
        
        
    }
    
    boolean endState(){
        boolean leftCrossSame = true;
        boolean rightCrossSame = true;
        boolean movesExist = false;
        
        int leftCrossValue = sd.data[0][0];
        if(leftCrossValue == 0){
            leftCrossSame = false;
        }
        
        int rightCrossValue = sd.data[sd.data.length-1][sd.data[0].length-1];
        if(rightCrossValue == 0){
            rightCrossSame = false;
        }
        
        for(int i = 0; i<sd.data.length; i++){
            if(rowSame(i) || columnSame(i)){
                return true;
            }
            if(sd.data[i][i] != leftCrossValue){
                leftCrossSame = false;
            }
            if(sd.data[sd.data.length-i-1][sd.data[0].length-i-1] != rightCrossValue){
                rightCrossSame = false;
            }
            
            for(int j = 0; j<sd.data[i].length; j++){
                if(sd.data[i][j] == 0){
                    movesExist = true;
                }
            }
        }
        if(rightCrossSame || leftCrossSame){
            return true;
        }
        
        if(!movesExist){
            //System.out.println("Cat's Game!");
            return true;
        }
        
        return false;
    }
    
    boolean rowSame(int i){
        int temp = sd.data[i][0];
        if(temp == 0){
            return false;
        }
        boolean allSame = true;
        for(int j = 1; j<sd.data.length; j++){
            if(sd.data[i][j] != temp){
                allSame = false;
            }
        }
        
        return allSame;
    }
    
    boolean columnSame(int i){
        int temp = sd.data[0][i];
        if(temp == 0){
            return false;
        }
        boolean allSame = true;
        for(int j = 1; j<sd.data.length; j++){
            if(sd.data[j][i] != temp){
                allSame = false;
            }
        }
        
        return allSame;
    }
}
