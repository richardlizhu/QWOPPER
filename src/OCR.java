import java.awt.Robot;
import java.awt.event.KeyEvent;


public class OCR {

	public static void main(String[] args) throws NumberFormatException, Exception
	{
		while(true)
		{
			Robot r = new Robot();
			System.out.println(OCR.read());
			r.keyRelease(KeyEvent.VK_Q);
		}
	}
	
	public static double read() throws NumberFormatException, Exception
	{
		return Double.parseDouble(OCRFactory.read(OCRConstants.ltx,OCRConstants.lty, OCRConstants.rbx-OCRConstants.ltx,OCRConstants.rby-OCRConstants.lty, "numbers").replaceAll("\\s+",""));
	}
}
