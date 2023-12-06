package controller.commands;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.CommandController;
import controller.imageformatoptions.BMP;
import controller.imageformatoptions.JPEG;
import controller.imageformatoptions.PNG;
import controller.imageformatoptions.PPM;
import controller.ImageFormatOperation;
import model.ImageProcessingModel;
import model.Pixel;

/**
 * This is Load image class which performs load image operation.
 */
public class LoadImage implements CommandController {
  private final String[] commandList;
  private final Map<String, Function<String, ImageFormatOperation>> imageOptions = new HashMap<>();


  /**
   * This is load image constructor which loads the image.
   *
   * @param input takes the command input string.
   */
  public LoadImage(String input) {
    this.commandList = input.split(" ");
    storeImageOptions();
  }

  /**
   * This is Storage image options method which takes the image option objects an put in hashmap.
   */
  private void storeImageOptions() {
    this.imageOptions.put("ppm", s -> new PPM());
    this.imageOptions.put("png", s -> new PNG());
    this.imageOptions.put("bmp", s -> new BMP());
    this.imageOptions.put("jpeg", s -> new JPEG());
    this.imageOptions.put("jpg", s -> new JPEG());
  }

  @Override
  public void executeCommand(ImageProcessingModel model) throws FileNotFoundException {
    checkLegalArgument(commandList.length, 3);
    ImageFormatOperation formatObject;
    String imagePath = new String(this.commandList[1]);
    Function<String, ImageFormatOperation> cmd =
        imageOptions.getOrDefault(imagePath.split(
            "[.]")[1], null);
    if (cmd == null) {
      throw new IllegalArgumentException("file not supported");
    } else {
      formatObject = cmd.apply(imagePath);
      Pixel[][] inputImage = formatObject.loadImage(commandList[1], commandList[2]);
      model.loadImage(inputImage, commandList[2]);
      System.out.println("Image loaded! Start Manipulating using the provided commands "
          + "format!");
      //      Pixel[][] inputImage = formatObject.loadImage(imagePath, storeFileName);
      //      this.storage.addImage(storeFileName, inputImage);
    }
  }

  @Override
  public void checkLegalArgument(int commandLength, int acceptedLength) throws
      IllegalArgumentException {
    if (commandLength != acceptedLength) {
      throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
    }
  }
}
