package controller.commands;

import java.util.Objects;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is ColorTransformation class.
 * This implements the sepia tone of color transformation.
 */
public class ColorTransformation implements CommandController {
  private final String[] commandList;

  /**
   * This is ColorTransformation constructor.
   *
   * @param input takes input of command string.
   */
  public ColorTransformation(String input) {
    this.commandList = input.split(" ");

  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    try {
      if (Objects.equals(commandList[1], "sepia")) {
        checkLegalArgument(commandList.length, 4);
        model.createSepiaTone(commandList[2], commandList[3]);
        System.out.println("Sepia tone for " + commandList[2] + " has been generated!");
        System.out.println("You can proceed to next commands, save, or quit!");
      } else {
        throw new IllegalArgumentException("Invalid command");
      }

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
