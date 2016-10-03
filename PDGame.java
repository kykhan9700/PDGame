/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdgameapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kamran
 */
public class PDGame {
    GameStat gstat = new GameStat();
    private ArrayList<String>  AL = new ArrayList<>();
    ArrayList<Integer> userHistory = new ArrayList<>();
    Scanner filescan;
    int computerStrategy,inline;
    int round=1;
    
     PDGame(String s) {
     AL.add("1. Computer Reads Strategy from input file");
     AL.add("2. Tit - for - Tat");
     AL.add("3. Random Strategy");
     try{
     File computerInput = new File(s);
     filescan = new Scanner(computerInput);
     }
catch(Exception e) 
{
    System.out.println("file not found");
    System.exit(0);
}    
     }
     public ArrayList<String> getStrategy()
     {
         return AL;
     }
     public String playRound(int decision)
     {
         int cDecision = getComputerdecision();
         userHistory.add(decision);
         
         if(cDecision == 1 && decision == 1)
         {
             gstat.update(2, 2);
             return "You and your partner remain silent\nYou both get 2 years in prison.";
         }
         else if(cDecision == 1 && decision == 2)
         {
             gstat.update(1, 5);
             return "You testify against your partner and they remain silent\nYou get 1 year in prison and they get 5";
         }
         else if(cDecision == 2 && decision == 1)
         {
             gstat.update(5, 1);
             return "you remain silent and your part testifies against you\nyou get 5 years and your partner gets 1 year in prison";
         }
         else
         {
             gstat.update(3, 3);
             return "you both testify against each other\nyou both get 3 years in prison";   
         }       
         
     }
     private int getComputerdecision()
     {  
         
         if(computerStrategy == 1)
         {
         inline = filescan.nextInt();
         //System.out.println("file strategy"+inline); 
         return inline;
         }
         else if (computerStrategy == 2) {
             
            if(round == 1){
                round++;
                return 1;
            }
            else {
                round++;
                return userHistory.get(userHistory.size()-1);
            }
                
         }
         else{
             System.out.println("in random strategy");
             return (int)Math.random()*2 +1;
         }
         //round++;
     }
     public void setStrategy(int strategy)
     {
         this.computerStrategy = strategy;
     }
     public String getScores()
     {
         String s = "END OF ROUNDS, GAME OVER --GAME STATS --Your prison sentence is:"+gstat.getuserScore()+
"\n------Your partner's prison sentence is:"+gstat.getcompScore();
         return s;
     }
     public GameStat getGameStatobj()
     {
         gstat.setStrategy(computerStrategy);
         return gstat;
     }
}
