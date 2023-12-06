package controller.commands;

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
 * This is saveImage class which saves the image in required format.
 */
public class SaveImage implements CommandController {
  private final String[] commandList;
  private final Map<String, Function<String, ImageFormatOperation>> imageOptions = new HashMap<>();

  /**
   * This is save image class constructor.
   *
   * @param input takes the command input string.
   */
  public SaveImage(String input) {
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
  public void executeCommand(ImageProcessingModel model) {
    ImageFormatOperation formatObject;
    String imagePath = new String(this.commandList[1]);
    Function<String, ImageFormatOperation> cmd =
        imageOptions.getOrDefault(imagePath.split(
            "[.]")[1], null);
    if (cmd == null) {
      throw new IllegalArgumentException("file not supported");
    } else {
      formatObject = cmd.apply(imagePath);
      Pixel[][] imageFile = model.saveFile(this.commandList[1], this.commandList[2]);
      formatObject.saveFile(commandList[1], imageFile);
      System.out.println("Image has been save at " + commandList[1]);
      System.out.println("You can proceed to next commands, save another or quit!");
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
