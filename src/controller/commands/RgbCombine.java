package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is rgb combine class which performs combines three greyscale images.
 */
public class RgbCombine implements CommandController {
  private final String[] commandList;

  /**
   * This is rgb combine constructor.
   *
   * @param input takes the command input string.
   */
  public RgbCombine(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    try {
      checkLegalArgument(commandList.length, 5);
      model.combineImage(commandList, commandList[4]);
      System.out.println(commandList[1] + " " + commandList[2] + " " + commandList[2] +
          " has been combined!");
      System.out.println("You can proceed to next commands, save another or quit!");
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

