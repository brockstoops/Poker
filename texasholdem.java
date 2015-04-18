//Brock Stoops
//Mathematical Modeling Poker Assignment
//
//4 of a kind Probability = (13 choose 1)*(48 choose 3)/(52 choose 5)
//Choose 1 rank for 4 of a kind then choose any 3 of the remaining cards
//Full House Probability = (13 choose 1)*(4 choose 3)*(12 choose 1)*(4 choose 2)*(11 choose 2)*(4 choose 1)*(4 choose 1)/(52 choose 5)
//Choose 1 rank for 3 of a kind, choose 3 of those 4 cards, then choose 1 of the remaining 12 ranks,
//choose 2 of those cards and then of the remaining 11 ranks choose any 2, then pick one each
//3 of a kind Probability = (13 choose 5)*(5 choose 1)*(4 choose 3)*(4 choose 1)*(4 choose 1)*(4 choose 1)*(4 choose 1)/(52 choose 5)
//Choose 1 rank for 3 of a kind, choose 3 of those 4 cards, then choose 1 card of remaining 4 ranks 

//The results of running the program are really really close to the actual probabilities
//The results vary for each time you run it obviously
//At most it is about .0001 off of the true probability

import java.util.Random;

public class TexasHoldEm {
	
	public static int foak = 0;
	public static int fullHouse = 0;
	public static int toak = 0;
	public static final int NUMHANDS = 133784560;
	public static final double FOAK = 224848.0/NUMHANDS;
	public static final double FULLHOUSE = 3473184.0/NUMHANDS;
	public static final double TOAK = 6461633.0/NUMHANDS;
	int[] hand = new int[7];
	
	//Create the hand with 7 random cards
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
		
		//Count up the instances if the hands
		//Doesn't count 4 of a kind as a 3 of a kind hand
		//Wasn't sure if I was supposed to count 4 of a kind and 3 of a kind for the same hand
		//In poker you would only count 1 hand so I calculated it that way
		if(checkFOAK(this.hand)){
			TexasHoldEm.foak++;
		}
		else if(checkFH(this.hand)){
			TexasHoldEm.fullHouse++;
		}
		else if(checkTOAK(this.hand)){
			TexasHoldEm.toak++;
		}
	}
	//Checks if a card is already in a hand
	public static boolean check(int[] hand, int num){
		for(int i =0; i<hand.length; i++){
			if(hand[i]==num)
				return true;
		}
		return false;
	}
	//Check if 4 of a kind, return true or false
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
	//Check if full house, return true or false
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
	//Check if it's 3 of a kind, return true
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
	//Run simulation
	public static void main(String args[]){
		for(int i=0; i<1000000; i++){
			TexasHoldEm the = new TexasHoldEm();
			the.createHand();
		}	
		System.out.println(TexasHoldEm.foak+" Four of a kind. \nActual Probability:\t"+(TexasHoldEm.foak/1000000.0)+". Expected Probability: "+FOAK);
		System.out.println(TexasHoldEm.fullHouse+" Full House. \nActual Probability:\t"+(TexasHoldEm.fullHouse/1000000.0)+". Expected Probability: "+FULLHOUSE);
		System.out.println(TexasHoldEm.toak+" Three of a kind. \nActual Probability:\t"+(TexasHoldEm.toak/1000000.0)+". Expected Probability: "+TOAK);
	}
}
