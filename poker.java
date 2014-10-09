//Brock Stoops
//Mathematical Modeling Poker Assignment
//
//4 of a kind Probability = (13 choose 1)*(48 choose 1)/(52 choose 5)
//Choose 1 rank for 4 of a kind then choose any of the remaining cards
//Full House Probability = (13 choose 1)*(4 choose 3)*(12 choose 1)*(4 choose 2)/(52 choose 5)
//Choose 1 rank for 3 of a kind, choose 3 of those 4 cards, then choose 1 of the remaining 12 ranks, choose 2 of those cards 
//3 of a kind Probability = (13 choose 1)*(4 choose 3)*(12 choose 2)*(4 choose 1)*(4 choose 1)/(52 choose 5)
//Choose 1 rank for 3 of a kind, choose 3 of those 4 cards, then choose 2 ranks of remaining 12 and choose 1 of the four cards for each rank

//The results of running the program are really really close to the actual probabilities
//The results vary for each time you run it obviously
//At most it is about .0001 off of the true probability

import java.util.Random;

public class Poker {
	
	public static int foak = 0;
	public static int fullHouse = 0;
	public static int toak = 0;
	public static final int NUMHANDS = 2598960;
	public static final double FOAK = 13.0*48/NUMHANDS;
	public static final double FULLHOUSE = 3744.0/NUMHANDS;
	public static final double TOAK = 54912.0/NUMHANDS;
	int[] hand = new int[5];
	
	//Create the hands of 5 cards
	public void createHand(){
		Random rand = new Random();
		for(int i =0; i < this.hand.length; i++){
			int temp = rand.nextInt(52);
			if(check(this.hand, temp)){
				i--;
				continue;
			}
			else
				this.hand[i] = temp;
		}
		//for(int i=0; i<5; i++)
			//System.out.print(this.hand[i]+" ");
	//	System.out.println();
		
		//Count the times each hand occures
		//If sees a 4 of a kind hand, will not count towards 3 of a kind
		if(checkFOAK(this.hand)){
			Poker.foak++;
		}
		else if(checkFH(this.hand)){
			Poker.fullHouse++;
		}
		else if(checkTOAK(this.hand)){
			Poker.toak++;
		}
	}
	//Method to check if card is in hand before adding it
	public static boolean check(int[] hand, int num){
		for(int i =0; i<hand.length; i++){
			if(hand[i]==num)
				return true;
		}
		return false;
	}
	//Checks if hand has 4 of a kind in it
	public static boolean checkFOAK (int [] hand){
		int[] cards = new int[13];
		for(int i = 0; i < hand.length; i++){
			cards[hand[i]%13]++;
		}
		for(int i=0; i<cards.length; i++){
			if(cards[i]==4)
				return true;
		}
		return false;
	}
	//Checks if hand is a full house
	public static boolean checkFH (int [] hand){
		int[] cards = new int[13];
		for(int i = 0; i < hand.length; i++){
			cards[hand[i]%13]++;
		}
		for(int i=0; i<cards.length; i++){
			if(cards[i]==3)
				for(int j=0; j<cards.length; j++)
					if(cards[j]==2)
						return true;
		}
		return false;
	}
	//Checks if a hand has exactly a 3 of a kind in it
	public static boolean checkTOAK (int [] hand){
		int[] cards = new int[13];
		for(int i = 0; i < hand.length; i++){
			cards[hand[i]%13]++;
		}
		for(int i=0; i<cards.length; i++){
			if(cards[i]==3)
				return true;
		}
		return false;
	}
	//Runs simulation
	public static void main(String args[]){
		for(int i=0; i<1000000; i++){
			Poker p = new Poker();
			p.createHand();
		}	
		System.out.println(Poker.foak+" Four of a kind. \nActual Probability:\t"+(Poker.foak/1000000.0)+". Expected Probability:\t"+FOAK);
		System.out.println(Poker.fullHouse+" Full House. \nActual Probability:\t"+(Poker.fullHouse/1000000.0)+". Expected Probability:\t"+FULLHOUSE);
		System.out.println(Poker.toak+" Three of a kind. \nActual Probability:\t"+(Poker.toak/1000000.0)+". Expected Probability: \t"+TOAK);
	}
}
