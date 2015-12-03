import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Bot
{
	static Robot r = new Robot();
	
	public static void press(QWOP qwop) throws Exception
	{
		if (qwop.q)
		{
			r.keyPress(KeyEvent.VK_Q);
		}
		else
		{
			r.keyRelease(KeyEvent.VK_Q);
		}
		if (qwop.w)
		{
			r.keyPress(KeyEvent.VK_W);
		}
		else
		{
			r.keyRelease(KeyEvent.VK_W);
		}
		if (qwop.o)
		{
			r.keyPress(KeyEvent.VK_O);
		}
		else
		{
			r.keyRelease(KeyEvent.VK_O);
		}
		if (qwop.p)
		{
			r.keyPress(KeyEvent.VK_P);
		}
		else
		{
			r.keyRelease(KeyEvent.VK_P);
		}
	}
}