import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;


public class OCR {

	public static void main(String[] args) throws NumberFormatException, Exception
	{

		{
			File f = new File("Test.txt");
			PrintWriter writer = new PrintWriter(f);
			writer.print("faffer2");
			writer.close();
		}
	}
	
	public static double read() throws NumberFormatException, Exception
	{
		return Double.parseDouble(OCRFactory.read(OCRConstants.ltx,OCRConstants.lty, OCRConstants.rbx-OCRConstants.ltx,OCRConstants.rby-OCRConstants.lty, "numbers").replaceAll("\\s+",""));
	}
}
