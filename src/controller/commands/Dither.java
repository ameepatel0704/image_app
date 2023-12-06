package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;


/**
 * This is Dither class.
 * This implements the dithering effect on an image.
 */
public class Dither implements CommandController {
  private final String[] commandList;

  /**
   * This is dither constructor.
   *
   * @param input takes input of command string.
   */
  public Dither(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    try {
      checkLegalArgument(commandList.length, 3);
      model.createDithering(commandList[1], commandList[2]);
      System.out.println(commandList[1] + " has been dithered");
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
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
