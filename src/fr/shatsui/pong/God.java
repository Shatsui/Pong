package fr.shatsui.pong;

public class God {
	
	public static void goToBall(Ball b,final Bat ba){
		if(ba.getY() < b.getY())
			ba.setDy(1);
		else if(ba.getY() > b.getY())
			ba.setDy(-1);
		else
			ba.setDy(0);
	}
}
