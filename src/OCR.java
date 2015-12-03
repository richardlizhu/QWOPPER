
public class OCR {
	private static int ltx = 2740;
	private static int lty = 375;
	private static int rbx = 2835;
	private static int rby = 405;
	
	public static double read() throws NumberFormatException, Exception
	{
		return Double.parseDouble(OCRFactory.read(ltx,lty, rbx-ltx,rby-lty, "numbers.txt"));
	}
}
