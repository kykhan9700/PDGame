/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdgameapp;

/**
 *
 * @author Kamran
 */
public class GameStat {
   int userYears,comYears;
   int Strategy;
   public GameStat()
   {
       userYears=0;
       comYears=0;
   }
   
   public void update(int userSentence,int compSentence)
   {
       userYears += userSentence;
       comYears += compSentence;
   }
   public String getWinner()
   {
       if(userYears < comYears)
           return "\nWinner is -- player";
       else if(userYears > comYears)
           return "\nWinner is -- Computer ";
       else return "\nGame is a draw";
   }
   public String getStrategy()
   {
       switch(Strategy)
       {
           case 1 : return " Computer Reads Strategy From Input File";
           
           case 2 : return " Tit - for - Tat";
           
           default : return " Random choice";
       }
   }
   public int getuserScore()
   {
       return userYears;
   }
   public int getcompScore()
   {
       return comYears;
   }
   public void setStrategy(int Strategy)
   {
       this.Strategy = Strategy;
   }
}

