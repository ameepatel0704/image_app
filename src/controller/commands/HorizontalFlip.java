package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is Horizontal flip class.
 * This class flips an image horizontally.
 */
public class HorizontalFlip implements CommandController {
  private final String[] commandList;

  /**
   * This is Horizontal flip constructor.
   *
   * @param input takes the command input string.
   */
  public HorizontalFlip(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    try {
      checkLegalArgument(commandList.length, 3);
      model.flipHorizontally(commandList[1], commandList[2]);
      System.out.println(commandList[1] + " has been flipped horizontally!");
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
