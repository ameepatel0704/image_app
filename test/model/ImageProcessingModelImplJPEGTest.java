package model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import controller.ImageFormatOperation;
import controller.imageformatoptions.JPEG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is JPEG test class.
 */
public class ImageProcessingModelImplJPEGTest {

  private String filePath = "res/stop.jpeg";
  private String testFilePath = "res/test_jpeg/";
  private String command = "load res/stop.jpeg stop";


  /**
   * This is helper method.
   */
  private Pixel[][] testLoadHelper(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new JPEG();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  /**
   * This is helper method.
   */
  private void testSaveHelper(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new JPEG();
    formatObject.saveFile(filePath, image);
  }

  /**
   * This is the test method that checks if file is loaded properly or not.
   */
  @Test
  public void testLoadFuncJPEG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] getStoredImage = test.getImage("stop");
    assertTrue(getStoredImage.length > 0);
  }


  /**
   * This is the test method that checks if file is loaded properly or not.
   */
  @Test
  public void testSaveFunc() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(testFilePath + "stop_saved_from_model_test.jpeg",
        "stop");
    testSaveHelper(testFilePath + "stop_saved_from_model_test.jpeg", saveImage);
    File tmpDir = new File(testFilePath + "stop_saved_from_model_test.jpeg");
    assertTrue(tmpDir.exists());
  }

  /**
   * This is the test method that verifies that image is flipped vertically.
   */
  @Test
  public void testFlipVerticalJPEG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");

    String command2 = "load " + testFilePath + "stop_vertical.jpeg" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");

    int height = data.length;
    int width = data[0].length;
    test.flipVertically("stop", "stop_vertical");

    test.saveFile("res/temp/stop_vertical.jpeg", "stop_vertical");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_vertical.jpeg",
        "stop_vertical");
    testSaveHelper("res/temp/stop_vertical.jpeg", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_vertical.jpeg stop_vertical");
    test.loadImage(newImage2, "stop_vertical");

    Pixel[][] vertical = test.getImage("stop_vertical");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, vertical[i][j].red);
        assertEquals(expectedFile[i][j].green, vertical[i][j].green);
        assertEquals(expectedFile[i][j].blue, vertical[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is flipped horizontally.
   */
  @Test
  public void testFlipHorizontalJPEG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");

    String command2 = "load " + testFilePath + "stop_horizontal.jpeg" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");

    int height = data.length;
    int width = data[0].length;
    test.flipHorizontally("stop", "stop_horizontal");

    test.saveFile("res/temp/stop_horizontal.jpeg", "stop_horizontal");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_horizontal.jpeg",
        "stop_horizontal");
    testSaveHelper("res/temp/stop_horizontal.jpeg", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_horizontal.jpeg " +
        "stop_horizontal");
    test.loadImage(newImage2, "stop_horizontal");

    Pixel[][] horizontal = test.getImage("stop_horizontal");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, horizontal[i][j].red);
        assertEquals(expectedFile[i][j].green, horizontal[i][j].green);
        assertEquals(expectedFile[i][j].blue, horizontal[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is flipped horizontal vertical.
   */
  @Test
  public void testFlipHorizontalVerticalJPEG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");

    String command2 = "load " + testFilePath + "stop_horizontal_vertical.jpeg" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");

    int height = data.length;
    int width = data[0].length;

    test.flipHorizontally("stop", "stop_horizontal");

    test.flipVertically("stop_horizontal", "stop_horizontal_vertical");

    test.saveFile("res/temp/stop_horizontal_vertical.jpeg",
        "stop_horizontal_vertical");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_horizontal_vertical.jpeg",
        "stop_horizontal_vertical");
    testSaveHelper("res/temp/stop_horizontal_vertical.jpeg", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_horizontal_vertical.jpeg " +
        "stop_horizontal_vertical");
    test.loadImage(newImage2, "stop_horizontal_vertical");

    Pixel[][] horizontal_vertical = test.getImage("stop_horizontal_vertical");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, horizontal_vertical[i][j].red);
        assertEquals(expectedFile[i][j].green, horizontal_vertical[i][j].green);
        assertEquals(expectedFile[i][j].blue, horizontal_vertical[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is flipped vertical horizontal.
   */
  @Test
  public void testFlipVerticalHorizontalJPEG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();

    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");

    String command2 = "load " + testFilePath + "stop_vertical_horizontal.jpeg" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");

    int height = data.length;
    int width = data[0].length;

    test.flipVertically("stop", "stop_vertical");

    test.flipHorizontally("stop_vertical", "stop_vertical_horizontal");

    test.saveFile("res/temp/stop_vertical_horizontal.jpeg",
        "stop_vertical_horizontal");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_vertical_horizontal.jpeg",
        "stop_vertical_horizontal");
    testSaveHelper("res/temp/stop_vertical_horizontal.jpeg", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_vertical_horizontal.jpeg " +
        "stop_vertical_horizontal");
    test.loadImage(newImage2, "stop_vertical_horizontal");

    Pixel[][] vertical_horizontal = test.getImage("stop_vertical_horizontal");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, vertical_horizontal[i][j].red);
        assertEquals(expectedFile[i][j].green, vertical_horizontal[i][j].green);
        assertEquals(expectedFile[i][j].blue, vertical_horizontal[i][j].blue);
      }
    }
  }

}
