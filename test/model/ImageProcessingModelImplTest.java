package model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import controller.ImageFormatOperation;
import controller.imageformatoptions.BMP;
import controller.imageformatoptions.JPEG;
import controller.imageformatoptions.PNG;
import controller.imageformatoptions.PPM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class contains test cases of model class.
 */
public class ImageProcessingModelImplTest {

  private final String testFilePath2 = "res/different_format/";
  private final String filePath = "res/stop.ppm";
  private final String testFilePath = "res/test_ppm/";
  private final String command = "load res/stop.ppm stop";

  private Pixel[][] testLoadHelper(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new PPM();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  /**
   * test helper class.
   */
  private Pixel[][] testLoadHelper2(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new JPEG();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  /**
   * test helper class.
   */
  private Pixel[][] testLoadHelper3(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new PNG();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  /**
   * test helper class.
   */
  private Pixel[][] testLoadHelper4(String command) throws FileNotFoundException {
    ImageFormatOperation formatObject = new BMP();
    String[] commandList = command.split(" ");
    Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
    return inputImage;
  }

  /**
   * test helper class.
   */

  private void testSaveHelper(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new PPM();
    formatObject.saveFile(filePath, image);
  }

  /**
   * test helper class.
   */

  private void testSaveHelper2(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new JPEG();
    formatObject.saveFile(filePath, image);
  }

  /**
   * test helper class.
   */
  private void testSaveHelper3(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new PNG();
    formatObject.saveFile(filePath, image);
  }

  /**
   * test helper class.
   */
  private void testSaveHelper4(String filePath, Pixel[][] image) throws FileNotFoundException {
    ImageFormatOperation formatObject = new BMP();
    formatObject.saveFile(filePath, image);
  }

  /**
   * This is the test method that checks if file is loaded properly or not.
   */
  @Test
  public void testLoadFunc() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] getStoredImage = test.getImage("stop");
    assertTrue(getStoredImage.length > 0);
  }

  /**
   * A test to check if the program throws exception if the input file is missing in the path.
   */
  @Test
  public void testLoadFuncFail() {
    try {
      EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
      Pixel[][] newImage = testLoadHelper("load res/stopp.ppm stop");
      test.loadImage(newImage, "stop");
    } catch (Exception e) {
      String expected = "File not found!";
      assertEquals(expected, e.getMessage());
    }
  }


  /**
   * This is the test method that checks if file is saved properly or not.
   */
  @Test
  public void testSaveFunc() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath + "stop_saved_from_model_test.ppm", "stop");
    testSaveHelper(testFilePath + "stop_saved_from_test.ppm", saveImage);
    File tmpDir = new File(testFilePath + "stop_saved_from_test.ppm");
    assertTrue(tmpDir.exists());
  }

  /**
   * This is the test method that verifies that image is flipped vertically.
   */
  @Test
  public void testFlipVertical() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_vertical.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.flipVertically("stop", "stop_vertical");
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
  public void testFlipHorizontal() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_horizontal.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.flipHorizontally("stop", "stop_horizontal");
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
  public void testFlipHorizontalVertical() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_horizontal_vertical.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.flipHorizontally("stop", "stop_horizontal");
    test.flipVertically("stop_horizontal", "stop_horizontal_vertical");
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
  public void testFlipVerticalHorizontal() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_vertical_horizontal.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.flipVertically("stop", "stop_vertical");
    test.flipHorizontally("stop_vertical", "stop_vertical_horizontal");
    Pixel[][] vertical_horizontal = test.getImage("stop_vertical_horizontal");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, vertical_horizontal[i][j].red);
        assertEquals(expectedFile[i][j].green, vertical_horizontal[i][j].green);
        assertEquals(expectedFile[i][j].blue, vertical_horizontal[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is brightened.
   */
  @Test
  public void testBrightenTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_brighten_50.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.imageBrightenDarken("stop", "stop_brighten", 50);
    Pixel[][] actual = test.getImage("stop_brighten");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is darkened.
   */
  @Test
  public void testDarkenTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_darken_50.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.imageBrightenDarken("stop", "stop_darken", -50);
    Pixel[][] actual = test.getImage("stop_darken");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that grayscale of the image is created using value
   * component.
   */
  @Test
  public void testValueGrayScaleTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_value_greyscale.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.valueGrayScale("stop", "stop_value_grayscale");
    Pixel[][] actual = test.getImage("stop_value_grayscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that grayscale of the image is created using luma
   * component.
   */
  @Test
  public void testLumaGrayScaleTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_luma_greyscale.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.lumaGrayScale("stop", "stop_luma_grayscale");
    Pixel[][] actual = test.getImage("stop_luma_grayscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that grayscale of the image is created using intensity
   * component.
   */
  @Test
  public void testIntensityGrayScaleTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_intensity_greyscale.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.intensityGrayScale("stop", "stop_intensity_grayscale");
    Pixel[][] actual = test.getImage("stop_intensity_grayscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that grayscale of the image is created using red
   * component.
   */
  @Test
  public void testedRedGrayscaleTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_red_grayscale.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createRedGray("stop", "stop_red_greyscale");
    Pixel[][] actual = test.getImage("stop_red_greyscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that grayscale of the image is created using green
   * component.
   */
  @Test
  public void testgreenGrayscaleTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_green_grayscale.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createGreenGray("stop", "stop_green_greyscale");
    Pixel[][] actual = test.getImage("stop_green_greyscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is combined using red, green, blue greyscale
   * components.
   */
  @Test
  public void testcombineRGBTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper("load res/test_ppm/stop.ppm stop");
    test.loadImage(newImage, "stop");
    Pixel[][] expectedFile = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_red_grayscale.ppm" + " stop_red_grayscale";
    String command3 = "load " + testFilePath + "stop_green_grayscale.ppm" + " stop_red_grayscale";
    String command4 = "load " + testFilePath + "stop_blue_grayscale.ppm" + " stop_blue_grayscale";
    Pixel[][] newImage2 = testLoadHelper(command2);
    test.loadImage(newImage2, "stop_red_grayscale");
    Pixel[][] newImage3 = testLoadHelper(command3);
    test.loadImage(newImage3, "stop_green_grayscale");
    Pixel[][] newImage4 = testLoadHelper(command4);
    test.loadImage(newImage4, "stop_blue_grayscale");
    int height = expectedFile.length;
    int width = expectedFile[0].length;
    String[] grayscales = new String[5];
    grayscales[1] = "stop_red_grayscale";
    grayscales[2] = "stop_green_grayscale";
    grayscales[3] = "stop_blue_grayscale";
    test.combineImage(grayscales, "Combined_Image");
    Pixel[][] actual = test.getImage("Combined_Image");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that image is splitted to three red, green, blue
   * greyscale components.
   */
  @Test
  public void testsplitRGBTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper("load res/stop.ppm stop");
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    Pixel[][] newImage2 = testLoadHelper(
        "load res/test_ppm/stop_red_grayscale.ppm stop_red_grayscale");
    test.loadImage(newImage2, "stop_red_grayscale");
    Pixel[][] newImage3 = testLoadHelper(
        "load res/test_ppm/stop_green_grayscale.ppm stop_green_grayscale");
    test.loadImage(newImage3, "stop_green_grayscale");
    Pixel[][] newImage4 = testLoadHelper(
        "load res/test_ppm/stop_blue_grayscale.ppm stop_blue_grayscale");
    test.loadImage(newImage4, "stop_blue_grayscale");

    Pixel[][] expectedFile1 = test.getImage("stop_red_grayscale");

    Pixel[][] expectedFile2 = test.getImage("stop_green_grayscale");

    Pixel[][] expectedFile3 = test.getImage("stop_blue_grayscale");
    int height = data.length;
    int width = data[0].length;
    String[] grayscales = new String[5];
    grayscales[2] = "stop_red_grayscale_new";
    grayscales[3] = "stop_green_grayscale_new";
    grayscales[4] = "stop_blue_grayscale_new";
    test.splitImage("stop", grayscales);
    Pixel[][] actual_red = test.getImage("stop_red_grayscale_new");
    Pixel[][] actual_green = test.getImage("stop_green_grayscale_new");
    Pixel[][] actual_blue = test.getImage("stop_blue_grayscale_new");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile1[i][j].red, actual_red[i][j].red);
        assertEquals(expectedFile1[i][j].green, actual_red[i][j].green);
        assertEquals(expectedFile1[i][j].blue, actual_red[i][j].blue);
        assertEquals(expectedFile2[i][j].red, actual_green[i][j].red);
        assertEquals(expectedFile2[i][j].green, actual_green[i][j].green);
        assertEquals(expectedFile2[i][j].blue, actual_green[i][j].blue);
        assertEquals(expectedFile3[i][j].red, actual_blue[i][j].red);
        assertEquals(expectedFile3[i][j].green, actual_blue[i][j].green);
        assertEquals(expectedFile3[i][j].blue, actual_blue[i][j].blue);
      }
    }
  }

  /**
   * This is the test method that verifies that grayscale of the image is created using blue
   * component.
   */
  @Test
  public void testblueGrayscaleTest() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_blue_grayscale.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createBlueGray("stop", "stop_blue_greyscale");
    Pixel[][] actual = test.getImage("stop_blue_greyscale");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * A test to check if the program applies sepia color transformation the given image.
   */
  @Test
  public void testSepiatone() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_sepia_tone.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createSepiaTone("stop", "stop_sepia_tone");
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
  public void testBlur() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_blur.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createBlur("stop", "stop_blur");
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
   * A test to check if the program sharpens the given image.
   */
  @Test
  public void testSharpen() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_sharpen.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.sharpenImage("stop", "stop_sharpen");
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
  public void testDithering() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] data = test.getImage("stop");
    String command2 = "load " + testFilePath + "stop_dithered.ppm" + " expected";
    Pixel[][] expected = testLoadHelper(command2);
    test.loadImage(expected, "expected");
    Pixel[][] expectedFile = test.getImage("expected");
    int height = data.length;
    int width = data[0].length;
    test.createDithering("stop", "stop_dithered");
    Pixel[][] actual = test.getImage("stop_dithered");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expectedFile[i][j].red, actual[i][j].red);
        assertEquals(expectedFile[i][j].green, actual[i][j].green);
        assertEquals(expectedFile[i][j].blue, actual[i][j].blue);
      }
    }
  }

  /**
   * This is the test if the program takes ppm input file and saves it in another format.
   */
  @Test
  public void testDiffFormat() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(testFilePath2 +
        "stop_saved_from_model_test.jpeg", "stop");
    testSaveHelper2(testFilePath2 + "stop_saved_from_model_test.jpeg", saveImage);
    Pixel[][] newImage2 = testLoadHelper2("load res/different_format" +
        "/stop_saved_from_model_test" + ".jpeg stop_actual");
    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }


  /**
   * This is the test if the program takes ppm input file and saves it in another format.
   */
  @Test
  public void testDiffFormat2() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(testFilePath2 + "stop_saved_from_model_test.png",
        "stop");
    testSaveHelper3(testFilePath2 + "stop_saved_from_model_test.png", saveImage);
    Pixel[][] newImage2 = testLoadHelper3(
        "load res/different_format/stop_saved_from_model_test.png stop_actual");
    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes ppm input file and saves it in another format.
   */
  @Test
  public void testDiffFormat3() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper(command);
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage =
        test.saveFile(testFilePath2 + "stop_saved_from_model_test.bmp", "stop");
    testSaveHelper4(testFilePath2 + "stop_saved_from_model_test.bmp", saveImage);
    Pixel[][] newImage2 = testLoadHelper4(
        "load res/different_format/stop_saved_from_model_test.bmp stop_actual");
    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes jpeg input file and saves it in another format.
   */
  @Test
  public void testDiffFormat4() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper2("load res/stop.jpeg stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.ppm", "stop");
    testSaveHelper(testFilePath2 + "stop_saved_from_model_test.ppm", saveImage);
    Pixel[][] newImage2 = testLoadHelper(
        "load res/different_format/stop_saved_from_model_test.ppm stop_actual");
    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);

  }

  /**
   * This is the test if the program takes jpeg input file and saves it in another format.
   */
  @Test
  public void testDiffFormat5() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper2("load res/stop.jpeg stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.png", "stop");
    testSaveHelper3(testFilePath2 + "stop_saved_from_model_test.png", saveImage);
    Pixel[][] newImage2 = testLoadHelper3(
        "load res/different_format/stop_saved_from_model_test.png stop_actual");
    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes jpeg input file and saves it in another format.
   */
  @Test
  public void testDiffFormat6() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper2("load res/stop.jpeg stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.bmp", "stop");

    testSaveHelper4(testFilePath2 + "stop_saved_from_model_test.bmp", saveImage);
    Pixel[][] newImage2 = testLoadHelper4(
        "load res/different_format/stop_saved_from_model_test.bmp stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes png input file and saves it in another format.
   */
  @Test
  public void testDiffFormat7() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper3("load res/stop.png stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.bmp", "stop");

    testSaveHelper4(testFilePath2 + "stop_saved_from_model_test.bmp", saveImage);
    Pixel[][] newImage2 = testLoadHelper4(
        "load res/different_format/stop_saved_from_model_test.bmp stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes png input file and saves it in another format.
   */
  @Test
  public void testDiffFormat8() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper3("load res/stop.png stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.ppm", "stop");

    testSaveHelper(testFilePath2 + "stop_saved_from_model_test.ppm", saveImage);
    Pixel[][] newImage2 = testLoadHelper(
        "load res/different_format/stop_saved_from_model_test.ppm stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes png input file and saves it in another format.
   */
  @Test
  public void testDiffFormat9() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper3("load res/stop.png stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.jpeg", "stop");

    testSaveHelper2(testFilePath2 + "stop_saved_from_model_test.jpeg", saveImage);
    Pixel[][] newImage2 = testLoadHelper2(
        "load res/different_format/stop_saved_from_model_test.jpeg stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes bmp input file and saves it in another format.
   */
  @Test
  public void testDiffFormat10() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper4("load res/stop.bmp stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.ppm", "stop");

    testSaveHelper(testFilePath2 + "stop_saved_from_model_test.ppm", saveImage);
    Pixel[][] newImage2 = testLoadHelper(
        "load res/different_format/stop_saved_from_model_test.ppm stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes bmp input file and saves it in another format.
   */
  @Test
  public void testDiffFormat11() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper4("load res/stop.bmp stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.png", "stop");

    testSaveHelper3(testFilePath2 + "stop_saved_from_model_test.png", saveImage);
    Pixel[][] newImage2 = testLoadHelper3(
        "load res/different_format/stop_saved_from_model_test.png stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes bmp input file and saves it in another format.
   */
  @Test
  public void testDiffFormat12() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper4("load res/stop.bmp stop");
    test.loadImage(newImage, "stop");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_saved_from_model_test.jpeg", "stop");

    testSaveHelper2(testFilePath2 + "stop_saved_from_model_test.jpeg", saveImage);
    Pixel[][] newImage2 = testLoadHelper2(
        "load res/different_format/stop_saved_from_model_test.jpeg stop_actual");

    test.loadImage(newImage2, "stop_actual");
    Pixel[][] actual = test.getImage("stop_actual");
    assertTrue(actual.length > 0);
  }

  /**
   * This is the test if the program takes ppm input file, performs blur opertaion and saves it in
   * another format.
   */
  @Test
  public void testDiffFormat13() throws FileNotFoundException {
    EnhancedImageProcessingModel test = new ImageProcessingModelImpl();
    Pixel[][] newImage = testLoadHelper4("load res/stop.bmp stop");
    test.loadImage(newImage, "stop");
    test.createBlur("stop", "stop_blur");
    Pixel[][] saveImage = test.saveFile(
        testFilePath2 + "stop_blur_saved_from_model_test.jpeg", "stop_blur");

    testSaveHelper2(testFilePath2 + "stop_blur_saved_from_model_test.jpeg", saveImage);
    Pixel[][] newImage2 = testLoadHelper2(
        "load res/different_format/stop_blur_saved_from_model_test.jpeg stop_blur_actual");

    test.loadImage(newImage2, "stop_blur_actual");
    Pixel[][] actual = test.getImage("stop_blur_actual");
    assertTrue(actual.length > 0);
  }


}