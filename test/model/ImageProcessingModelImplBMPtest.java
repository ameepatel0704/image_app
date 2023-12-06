package model;


import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import controller.ImageFormatOperation;
import controller.imageformatoptions.BMP;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is BMP test class.
 */

public class ImageProcessingModelImplBMPtest {

  private String filePath = "res/stop.bmp";
  private String testFilePath = "res/test_bmp/";
  private String command = "load res/stop.bmp stop";

  /**
   * This is helper method.
   */
  private Pixel[][] testLoadHelper(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new BMP();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  /**
   * This is helper method.
   */
  private void testSaveHelper(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new BMP();
    formatObject.saveFile(filePath, image);
  }

  /**
   * This is helper method.
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
    Pixel[][] saveImage = test.saveFile(testFilePath + "stop_saved_from_model_test.bmp",
        "stop");
    testSaveHelper(testFilePath + "stop_saved_from_model_test.bmp", saveImage);
    File tmpDir = new File(testFilePath + "stop_saved_from_model_test.bmp");
    assertTrue(tmpDir.exists());
  }

  /**
   * A test to check if the program sharpens the given image.
   */
  @Test
  public void testSharpenBMP() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_sharpen.bmp" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.sharpenImage("stop", "stop_sharpen");

    test.saveFile("res/temp/stop_sharpen.bmp", "stop_sharpen");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_sharpen.bmp",
        "stop_sharpen");
    testSaveHelper("res/temp/stop_sharpen.bmp", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_sharpen.bmp stop_sharpen");
    test.loadImage(newImage2, "stop_sharpen");

    Pixel[][] actual = test.getImage("stop_sharpen");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * A test to check if the program dithers the given image.
   */
  @Test
  public void testDitheringBMP() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_dithered.bmp" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createDithering("stop", "stop_dithered");

    test.saveFile("res/temp/stop_sepia_tone.bmp", "stop_dithered");
    Pixel[][] saveImage = test.saveFile("res/temp/stop_dithered.bmp",
        "stop_dithered");
    testSaveHelper("res/temp/stop_dithered.bmp", saveImage);

    Pixel[][] newImage2 = testLoadHelper("load res/temp/stop_dithered.bmp " +
        "stop_dithered");
    test.loadImage(newImage2, "stop_dithered");

    Pixel[][] actual = test.getImage("stop_dithered");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }


}
