import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Bot
{
	public static void press(QWOP qwop) throws Exception
	{
		Robot r = QLearning.r;

		if (!qwop.p)
			r.keyRelease(KeyEvent.VK_P);
		if (!qwop.q)
			r.keyRelease(KeyEvent.VK_Q);
		if (!qwop.w)
			r.keyRelease(KeyEvent.VK_W);
		if (!qwop.o)
			r.keyRelease(KeyEvent.VK_O);
		
		if (qwop.q)
			r.keyPress(KeyEvent.VK_Q);
		if (qwop.w)
			r.keyPress(KeyEvent.VK_W);
		if (qwop.o)
			r.keyPress(KeyEvent.VK_O);
		if (qwop.p)
			r.keyPress(KeyEvent.VK_P);
		
		
	}
}