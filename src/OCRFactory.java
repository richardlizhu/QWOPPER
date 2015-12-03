import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;



// Dependencies are JAI ImageIO and JNA.
import net.sourceforge.tess4j.*;

public class OCRFactory {
	
	public static String read(int tlx, int tly, int dx, int dy, String filter) throws Exception {
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(tlx, tly, dx, dy));
		return readText(screenShot, filter);
	}
	
	// Format should be PNG or TIFF or BMP or etc...
	public static void takeScreenshot(String name, String format, int tlx, int tly, int dx, int dy) throws Exception {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
		//Calendar now = Calendar.getInstance();
		Robot robot = new Robot();
		//Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
		//int height = dimensions.height;
		//int width = dimensions.width;
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(tlx, tly, dx, dy));
		File image = new File("./images/" + name);
		ImageIO.write(screenShot, format, image);
	}
	
	public static void blackAndWhite(String name, String format) throws IOException {
		BufferedImage coloredImage = ImageIO.read(new File("./images/" + name));
		BufferedImage blackNWhite = new BufferedImage(coloredImage.getWidth(),coloredImage.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D graphics = blackNWhite.createGraphics();
		graphics.drawImage(coloredImage, 0, 0, null);

		ImageIO.write(blackNWhite, format, new File("./images/bw_" + name));
	}
	
	public static void delete(String name) {
		String path = "./images/" + name;
		File image = new File(path);
		try {

			if (image.delete()) {
				System.out.println(image.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes in an image name under the ./images/ directory and estimates
	 * its level.
	 * 
	 * 8/21/2015: UPDATE.  I don't know what happened, but I think League updated
	 * its icons to be slightly different for skill levels in the HUD.  Either way,
	 * we need to find a way to walk around this (blob detection with OpenCV maybe?).
	 * 
	 * @param  The name of the image file
	 * @return The skill level
	 */
	public static int readSkillLevel(String name) throws IOException {
		double similarity = 0.0;
		double temp;
		int skill = 0;
		for (int i = 0; i < 6; i++) {
			temp = compareHistograms("NewSkills/skill0" + i + ".tiff", name);
			if (temp > similarity) {
				similarity = temp;
				skill = i;
			}
		}
		return skill;
	}
	


	
	
	
	public static String readText(String name, String filter) {
		String path = "./images/" + name;
		File imageFile = new File(path);
	    Tesseract instance = new Tesseract();  // JNA Interface Mapping
	    if (filter != null && !filter.equals("")) {
	    	List<String> configs = new ArrayList<String>();
		    configs.add("./tessdata/configs/" + filter);
		    instance.setConfigs(configs);
	    }
	    //Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
	    try {
	        String result = instance.doOCR(imageFile);
	        return result;
	    } catch (TesseractException e) {
	        System.err.println(e.getMessage());
	        return "ERROR";
	    }
	}
	
	public static String readText(BufferedImage image, String filter) {
		Tesseract instance = new Tesseract();
		if (filter != null && !filter.equals("")) {
	    	List<String> configs = new ArrayList<String>();
		    configs.add("./tessdata/configs/" + filter);
		    instance.setConfigs(configs);
	    }
	    //Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
	    try {
	        String result = instance.doOCR(image);
	        return result;
	    } catch (TesseractException e) {
	        System.err.println(e.getMessage());
	        return "ERROR";
	    }
	}
}