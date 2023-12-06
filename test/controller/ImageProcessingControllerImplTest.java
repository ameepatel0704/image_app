package controller;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import model.EnhancedImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;
import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertTrue;


/**
 * This is the controller test class. This will contain all test cases of controller object.
 */
public class ImageProcessingControllerImplTest {

  /**
   * This is the test class check if image is loaded and stored or not.
   */
  @Test
  public void loadImage() throws FileNotFoundException {
    String input = "load res/stop.ppm stop";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class will give false as image is loaded but wrong object has been called.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageWrongObject() throws FileNotFoundException {
    String input = "load res/stop.ppm stop";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    model.getImage("stopm");
  }


  /**
   * This is the test class check and run commands from txt file.
   */
  @Test
  public void testRunCommands() throws FileNotFoundException {
    String input = "run res/commands.txt";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is brighten or not.
   */
  @Test
  public void testBrightenImage() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nbrighten 20 stop stop-brighter";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-brighter");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is darkened or not.
   */
  @Test
  public void testDarkenImage() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nbrighten -20 stop stop-dark";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-dark");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is flipped horizontally using controller or not.
   */
  @Test
  public void testFlipHorizontal() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nhorizontal-flip stop stop-horizontal";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-horizontal");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is flipped horizontal vertical using controller or not.
   */
  @Test
  public void testFlipHorizontalVertical() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nhorizontal-flip stop stop-horizontal" +
        "\nvertical-flip stop-horizontal stop-horizontal-vertical";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-horizontal-vertical");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is flipped vertically using controller or not.
   */
  @Test
  public void testFlipVertical() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nvertical-flip stop stop-vertical";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-vertical");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is flipped vertical horizontal using controller or not.
   */
  @Test
  public void testFlipVerticalHorizontal() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nvertical-flip stop stop-vertical" +
        "\nhorizontal-flip stop-vertical stop-vertical-horizontal";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-vertical-horizontal");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is red grey component is generated or not.
   */
  @Test
  public void testRedGreyScale() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ngreyscale red-component stop stop-red-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-red-grey");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is green grey component is generated or not.
   */
  @Test
  public void testGreenGreyScale() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ngreyscale green-component stop stop-green-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-green-grey");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is blue grey component is generated or not.
   */
  @Test
  public void testBlueGreyScale() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ngreyscale blue-component stop stop-blue-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-blue-grey");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is intensity grey component is generated or not.
   */
  @Test
  public void testIntensityGreyScale() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ngreyscale intensity-component stop stop-intensity-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-intensity-grey");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is luma grey component is generated or not.
   */
  @Test
  public void testLumaGreyScale() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ngreyscale intensity-component stop stop-luma-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-luma-grey");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is value grey component is generated or not.
   */
  @Test
  public void testValueGreyScale() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ngreyscale intensity-component stop stop-value-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-value-grey");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is splitted or not.
   */
  @Test
  public void testRgbSplit() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nrgb-split stop " +
        "stop-red-grey stop-green-grey stop-blue-grey";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-red-grey");
    Pixel[][] image2 = model.getImage("stop-green-grey");
    Pixel[][] image3 = model.getImage("stop-blue-grey");
    assertTrue(image.length > 0);
    assertTrue(image2.length > 0);
    assertTrue(image3.length > 0);

  }

  /**
   * This is the test class check if image is combined or not.
   */
  @Test
  public void testRgbCombine() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nrgb-split stop "
        + "stop-red-grey stop-green-grey stop-blue-grey\n"
        + "rgb-combine stop-red-grey stop-green-grey stop-blue-grey rgb-combine-image";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("rgb-combine-image");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if sepia tone of image is created or not.
   */
  @Test
  public void testSepiaTone() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ncolor-transform sepia stop stop-sepia";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-sepia");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if dithering of image is created or not.
   */
  @Test
  public void testDithering() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\ndither stop stop-dither";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-dither");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if sharpen filter of image is created or not.
   */
  @Test
  public void testFilterSharpen() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nfilter sharpen stop stop-sharpen";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-sharpen");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if filter blur of image is created or not.
   */
  @Test
  public void testFilterBlur() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nfilter blur stop stop-blur";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-blur");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is saved or not.
   */
  @Test
  public void testSaveImage() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nbrighten 20 stop stop-brighter-test\n"
        + "save res/stop_brighter_controller_test.ppm stop-brighter-test\n" +
        "load res/stop_brighter_controller_test.ppm stop-br";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-br");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as png and saved as ppm.
   */
  @Test
  public void testLoadPNGSavePPM() throws FileNotFoundException {
    String input = "load res/stop.png stop\nsave res/stop.ppm stop\n" +
        "load res/stop.ppm stop-ppm";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-ppm");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as png and saved as bmp.
   */
  @Test
  public void testLoadPNGSaveBMP() throws FileNotFoundException {
    String input = "load res/stop.png stop\nsave res/stop.bmp stop\n" +
        "load res/stop.bmp stop-bmp";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-bmp");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as png and saved as jpeg.
   */
  @Test
  public void testLoadPNGSaveJPEG() throws FileNotFoundException {
    String input = "load res/stop.png stop\nsave res/stop.jpeg stop\n" +
        "load res/stop.jpeg stop-jpeg";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-jpeg");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as ppm and saved as png.
   */
  @Test
  public void testLoadPPMSavePNG() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nsave res/stop.png stop\n" +
        "load res/stop.ppm stop-png";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-png");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as ppm and saved as bmp.
   */
  @Test
  public void testLoadPPMSaveBMP() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nsave res/stop.bmp stop\n" +
        "load res/stop.bmp stop-bmp";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-bmp");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as ppm and saved as png.
   */
  @Test
  public void testLoadPPMSaveJPEG() throws FileNotFoundException {
    String input = "load res/stop.ppm stop\nsave res/stop.jpeg stop\n" +
        "load res/stop.jpeg stop-jpeg";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-jpeg");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as bmp and saved as jpeg.
   */
  @Test
  public void testLoadBMPSaveJPEG() throws FileNotFoundException {
    String input = "load res/stop.bmp stop\nsave res/stop.jpeg stop\n" +
        "load res/stop.jpeg stop-jpeg";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-jpeg");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as bmp and saved as png.
   */
  @Test
  public void testLoadBMPSavePNG() throws FileNotFoundException {
    String input = "load res/stop.bmp stop\nsave res/stop.png stop\n" +
        "load res/stop.png stop-png";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-png");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as bmp and saved as ppm.
   */
  @Test
  public void testLoadBMPSavePPM() throws FileNotFoundException {
    String input = "load res/stop.bmp stop\nsave res/stop.ppm stop\n" +
        "load res/stop.ppm stop-ppm";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-ppm");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as jpeg and saved as png.
   */
  @Test
  public void testLoadJPEGSavePNG() throws FileNotFoundException {
    String input = "load res/stop.jpeg stop\nsave res/stop.png stop\n" +
        "load res/stop.png stop-png";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-png");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as jpeg and saved as ppm.
   */
  @Test
  public void testLoadJPEGSavePPM() throws FileNotFoundException {
    String input = "load res/stop.jpeg stop\nsave res/stop.ppm stop\n" +
        "load res/stop.ppm stop-ppm";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-ppm");
    assertTrue(image.length > 0);
  }

  /**
   * This is the test class check if image is loaded as jpeg and saved as bmp.
   */
  @Test
  public void testLoadJPEGSaveBMP() throws FileNotFoundException {
    String input = "load res/stop.jpeg stop\nsave res/stop.bmp stop\n" +
        "load res/stop.bmp stop-bmp";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    View view = new ViewImpl();
    ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(in, view, model);
    controller.inputSelection();
    Pixel[][] image = model.getImage("stop-bmp");
    assertTrue(image.length > 0);
  }

}