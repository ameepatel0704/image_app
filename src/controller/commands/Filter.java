package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;


/**
 * This is Filter class.
 * This class has blur filter and sharpen image filter.
 */
public class Filter implements CommandController {
  private final String[] commandList;

  /**
   * This is Filter constructor.
   * This class has blur filter and sharpen image filter.
   *
   * @param input takes the command input string.
   */
  public Filter(String input) {
    this.commandList = input.split(" ");
  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    filterOptions(commandList, model);
  }

  /**
   * This is Filter options.
   * This class has blur filter and sharpen image filter.
   *
   * @param command takes the string array.
   * @param model   takes model as an input to perform operation.
   */
  private void filterOptions(String[] command, ImageProcessingModel model) {
    switch (command[1]) {
      case "blur":
        blurImage(commandList, model);
        break;
      case "sharpen":
      default:
        sharpenImage(commandList, model);
        break;
    }
  }

  /**
   * This applies the blur filter on an image.
   *
   * @param command takes the string array.
   * @param model   takes model as an input to call the operations.
   */
  private void blurImage(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.createBlur(command[2], command[3]);
      System.out.println(command[2] + " has been blurred!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * This applies to sharpen filter on an image.
   *
   * @param command takes the string array.
   * @param model   takes model as an input to call the operations.
   */
  private void sharpenImage(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.sharpenImage(command[2], command[3]);
      System.out.println(command[2] + " has been sharpened!");
      System.out.println("You can proceed to next commands, save or quit!");
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
