package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is Image darken or brighten class.
 * If negative value passed it will darken the image and positive value
 * passed image will be brightened.
 */
public class ImageBrightenDarken implements CommandController {
  private final String[] commandList;

  /**
   * This is Image brighten darken constructor.
   *
   * @param input takes the command input string.
   */
  public ImageBrightenDarken(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    try {
      checkLegalArgument(commandList.length, 4);
      model.imageBrightenDarken(commandList[2], commandList[3], Integer.parseInt(commandList[1]));
      String printResult = "Brightness of " + commandList[2] + " " +
          "has been " + (Integer.parseInt(commandList[1]) < 0 ? "decreased" : "increased")
          + " by " + Math.abs(Integer.parseInt(commandList[1])) + " points!";
      System.out.println(printResult);
      System.out.println("You can proceed to next commands, save, or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public void checkLegalArgument(int commandLength, int acceptedLength) {
    if (commandLength != acceptedLength) {
      throw new IllegalArgumentException("Incorrect command!! Please enter valid command");
    }
  }
}
