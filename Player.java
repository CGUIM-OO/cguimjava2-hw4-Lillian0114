import java.util.ArrayList;

public class Player {
	private String name;
	private int chips;
	private int bet;
	private ArrayList<Card> oneRoundCard;
	
	public Player(String name, int chips){
		this.name=name;
		this.chips=chips;
	}
	public String getName(){
		return name;
	}
	public int getCurrentChips(){
		return chips;
	}
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	public void increaseChips (int diff){
		chips+=diff;
	}
	public void setOneRoundCard(ArrayList<Card> cards){
		oneRoundCard=cards;
	}
	
	public int makeBet(){
		if(chips==0){
			System.out.println("您已沒籌碼，無法繼續下注了喔!");
			bet=0;
		}
		else{
			bet=1;
		}
		return bet;
	}
	
	public boolean hitMe(){
		if(getTotalValue()<=16)
			return true;
		else
			return false;
	}
	
	public int getTotalValue(){
		int total=0;
		int temp=0;
		for(int i=0; i<oneRoundCard.size();i++)
		{
			temp=oneRoundCard.get(i).getRank();
			if(temp==11|temp==12|temp==13)
			{
				temp=10;
			}
			else if(temp==1)
			{
				temp=11;
			}
			total+=temp;
		}
		
		for(int i=0; i<oneRoundCard.size();i++)
		{
			if(oneRoundCard.get(i).getRank()==1&&total>21)
			{
				total-=10;
				if(total<=21)
					break;
			}
		}
		return total;
	}
	
}
