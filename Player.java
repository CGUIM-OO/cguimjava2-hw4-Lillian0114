import java.util.ArrayList;

public class Player {
	private String name; //玩家姓名
	private int chips; //玩家有的籌碼
	private int bet; //玩家此局下注的籌碼
	private ArrayList<Card> oneRoundCard; //此牌局的卡
	
	public Player(String name, int chips){
		this.name=name; //因為local variable與instance variable一樣(shadowing)，故當要呼叫instance variable時，需要用this關鍵字
		this.chips=chips;
	}
	public String getName(){
		return name;
	}
	public int getCurrentChips(){
		return chips; //回傳玩家現有籌碼
	}
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	
	
	public void increaseChips (int diff){   //玩家籌碼變動
		chips+=diff; //將傳進來的diff參數加入chips裡
	}
	public void setOneRoundCard(ArrayList<Card> cards){
		oneRoundCard=cards; //此牌局所得到的卡 
	}
	
	public int makeBet(){
		if(chips==0){
			System.out.println("您已沒籌碼，無法繼續下注了喔!");
			bet=0; //因為已經沒有籌碼了，所以也無法下注，故bet=0
		}
		else{
			bet=1;
		}
		return bet;
	}
	
	public boolean hitMe(){
		if(getTotalValue()<=16) //用呼叫getTotalValue() function的方法知道當局所得的卡的總點數
			return true; //判斷條件為:全部的點數<=16，故要牌
		else
			return false; //判斷條件為:全部的點數>16，故不要牌
	}
	
	public int getTotalValue(){
		int total=0; //total為此局牌的總點數
		int temp=0;  //temp為此局所得到的某張牌的rank
		for(int i=0; i<oneRoundCard.size();i++)
		{
			temp=oneRoundCard.get(i).getRank(); //利用for迴圈，取出oneRoundCard裡每張牌的rank
			if(temp==11|temp==12|temp==13)
			{
				temp=10; //21點的玩法為拿到:J、Q、K所算的點數為10
			}
			else if(temp==1)
			{
				temp=11; //21點的玩法為拿到:A所算的點數為1、11，故一開始我先判斷看到A為11，之後再視情況做改變
			}
			total+=temp; //將點數加至加總裡
		}
		
		for(int i=0; i<oneRoundCard.size();i++)
		{
			if(oneRoundCard.get(i).getRank()==1&&total>21) 
			{
				total-=10; //當oneRoundCard裡有的牌點數有1且total>21時，total-10(亦即此A的點數為1)
				if(total<=21) //但也有可能oneRoundCard裡有很多A的牌，所以一旦total<=21時，就break出去迴圈，不在把A當成1
					break;
			}
		}
		return total;
	}
	
}
