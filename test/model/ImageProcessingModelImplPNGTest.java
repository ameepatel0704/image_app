package model;


import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import controller.ImageFormatOperation;
import controller.imageformatoptions.PNG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the PNG test class.
 */
public class ImageProcessingModelImplPNGTest {

  private final String filePath = "res/stop.png";
  private final String testFilePath = "res/test_png/";
  private final String command = "load res/stop.png stop";

  private Pixel[][] testLoadHelper(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new PNG();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  private void testSaveHelper(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new PNG();
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
    ImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath + "stop_saved_from_model_test.png", "stop");
    testSaveHelper(testFilePath + "stop_saved_from_model_test.png", saveImage);
    File tmpDir = new File(testFilePath + "stop_saved_from_model_test.png");
    assertTrue(tmpDir.exists());
  }

  /**
   * A test to check if the program applies sepia color transformation the given image.
   */
  @Test
  public void testSepiatonePNG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_sepia_tone.png" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createSepiaTone("stop", "stop_sepia_tone");

    test.saveFile("res/temp/stop_sepia_tone.png", "stop_sepia_tone");
    Pixel[][] saveImage = test.saveFile(
        "res/temp/stop_sepia_tone.png", "stop_sepia_tone");
    testSaveHelper("res/temp/stop_sepia_tone.png", saveImage);

    Pixel[][] newImage2 = testLoadHelper(
        "load res/temp/stop_sepia_tone.png stop_sepia_tone");
    test.loadImage(newImage2, "stop_sepia_tone");

    Pixel[][] actual = test.getImage("stop_sepia_tone");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * A test to check if the program blurs the given image.
   */
  @Test
  public void testBlurPNG() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_blur.png" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createBlur("stop", "stop_blur");

    test.saveFile("res/temp/stop_blur.png", "stop_blur");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_blur.png", "stop_blur");
    testSaveHelper("res/temp/stop_blur.png", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_blur.png stop_blur");
    test.loadImage(newImage2, "stop_blur");

    Pixel[][] actual = test.getImage("stop_blur");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * A test to check if the program blurs the given image.
   */
  @Test
  public void testGreyscale() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_luma_greyscale.png" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.lumaGrayScale("stop", "stop_luma_greyscale");

    test.saveFile("res/temp/stop_luma_greyscale.png", "stop_luma_greyscale");
    Pixel[][] saveImage = test.saveFile(
        "res/temp/stop_luma_greyscale.png", "stop_luma_greyscale");
    testSaveHelper("res/temp/stop_luma_greyscale.png", saveImage);

    Pixel[][] newImage2 = testLoadHelper(
        "load res/temp/stop_luma_greyscale.png stop_luma_greyscale");
    test.loadImage(newImage2, "stop_luma_greyscale");

    Pixel[][] actual = test.getImage("stop_luma_greyscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

}
