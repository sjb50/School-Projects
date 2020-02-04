package edu.century.pa1;
public class Pa1Driver {

	public static void main(String[] args) {
		try{
		PayCalculator sam = new PayCalculator("Sam Bedell",13.00);
		PayCalGooey gooey = new PayCalGooey(sam);
		}
		catch (Exception e) {
		}
	}
}
