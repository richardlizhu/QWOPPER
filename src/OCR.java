
public class OCR {
	private static int ltx = 2740;
	private static int lty = 380;
	private static int rbx = 2835;
	private static int rby = 415;
	public static void main(String[] args) throws NumberFormatException, Exception
	{
		while(true)
		{
			System.out.println(OCR.read());
		}
	}
	
	public static double read() throws NumberFormatException, Exception
	{
		return Double.parseDouble(OCRFactory.read(ltx,lty, rbx-ltx,rby-lty, "numbers"));
	}
}
