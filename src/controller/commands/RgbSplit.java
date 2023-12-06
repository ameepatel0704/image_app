package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is rgb split class which splits into red, green, blue greyscale images.
 */
public class RgbSplit implements CommandController {
  private final String[] commandList;

  /**
   * This is rgb split constructor.
   *
   * @param input takes the command input string.
   */
  public RgbSplit(String input) {
    this.commandList = input.split(" ");

  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    try {
      checkLegalArgument(commandList.length, 5);
      model.splitImage(commandList[1], commandList);
      System.out.println(commandList[1] + " has been split to three red, green, blue " +
          "greyscale components!");
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
