/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdgameapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Kamran
 */
public class PDGameApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
        HashMap<String, GameStat> gameData = new HashMap<>();
        GameStat gs = new GameStat();
        Scanner scan = new Scanner(System.in);
        int computerStrategy, userStrategy;
        String currDate;
        boolean loop = false;
 
        System.out.println("***Starting A Game of Prisoner's Dilemma ***5 rounds in a game");
     while(loop == false)
     {
         int playround=1;
         currDate = new Date().toString();
         //System.out.println("in loop");
         PDGame pd = new PDGame("Strategies.txt");
         ArrayList<String> al = pd.getStrategy();
         //int size = al.size();
         //System.out.println("size of al is " +size);
         ListIterator<String> li = pd.getStrategy().listIterator();
         System.out.println("--Here are strategies available for the computer");
         
         while(li.hasNext())
         {
             System.out.println(li.next());
         }
         
         boolean loopFlag=false;  //boolean variable set to false
     
     while (loopFlag==false)   //while false (bad data)... keep spinning
       { 
                  
        try
         {
            System.out.println("Select a strategy from above for the Computer to use in the 5 rounds :");
            computerStrategy = scan.nextInt();
            
          if(computerStrategy<1 || computerStrategy>3) // we have int,check range
             System.out.println("\nINTEGER ENTERED IS OUT OF RANGE 1 TO 3 ");
          else{
              pd.setStrategy(computerStrategy);
              loopFlag=true;  //we have a valid int in range 1-10
            }
          }
        
        catch(InputMismatchException e)
         {  //code runs if user did not enter an integer..like.xc45
            
             System.out.println("\n CAUGHT ERROR-YOU HAVE NOT ENTERED AN INTEGER-PLEASE TRY AGAIN ");
             scan.nextLine(); //**this is needed for scanner to reset itself
         }
       }//end while loop1
      
            
            while(playround <=5)
            {
                loopFlag = false;
            while (loopFlag==false)   //while false (bad data)... keep spinning
           { 
                  
        try
         {
                System.out.println("BEGIN A ROUND - Here are your 2 choices");
                System.out.println("1. Cooperate and remain silent.");
                System.out.println("2. Betray and testify against.");
                playround++;
                userStrategy = scan.nextInt(); 
          if(userStrategy<1 || userStrategy>2) // we have int,check range
             System.out.println("\nINTEGER ENTERED IS OUT OF RANGE 1 TO 2 ");
          else  {
              System.out.println(pd.playRound(userStrategy));
              loopFlag=true;  //we have a valid int in range 1-10 
          }
          }
        catch(InputMismatchException e)
         {  //code runs if user did not enter an integer..like.xc45
            
             System.out.println("\n CAUGHT ERROR-YOU HAVE NOT ENTERED AN INTEGER-PLEASE TRY AGAIN ");
             scan.nextLine(); //**this is needed for scanner to reset itself
         }
       }//end while loop1
     
                
                
            }
            gs = pd.getGameStatobj();
            gameData.put(currDate, gs);
            System.out.println(pd.getScores());
           
            loopFlag=false;  //boolean variable set to false
            
        while (loopFlag==false)   //while false loop 2
        { 
         System.out.println("\nDo you want to play a game? Y/N  ");
                  
          String inAnswerString = scan.next();  //read in the letter/token
         
         if(inAnswerString.equalsIgnoreCase("y")){ //if we have a valid int, check range
             //System.out.println("\nPLAY GAME ....ETC, ....GAME ENDS "); //call method 
                                                        //to play game
             break;
         }
         else 
             if(inAnswerString.equalsIgnoreCase("n")){
                 loopFlag=true;  //turn on flag
                 loop = true;
             }
              else
                  System.out.println("\nYOUR ANSWER IS NOT VALID--PLEASE ENTER Y OR N ");  //we have a valid int in range 1-10
         } 

         
         
         
     }
     Set<String> keyset = gameData.keySet();
     for(String s: keyset)
     {
         gs = gameData.get(s);
         System.out.println(s+gs.getWinner()+"\nThe computer used "+gs.getStrategy());
     }
    }
    
}
