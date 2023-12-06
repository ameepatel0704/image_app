package controller.commands;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is Greyscale class.
 * This class has all greyscale filter options.
 */
public class Greyscale implements CommandController {
  private final String[] commandList;

  /**
   * This is Greyscale constructor.
   * This class has methods like red-greyscale, green-greyscale, blue-greyscale, luma-greyscale
   * value-greyscale, intensity-greyscale.
   *
   * @param input takes the command input string.
   */
  public Greyscale(String input) {
    this.commandList = input.split(" ");

  }

  @Override
  public void executeCommand(ImageProcessingModel model) {
    grayScaleOperations(commandList, model);

  }

  /**
   * This is greyscale operation's method.
   * This method takes command arguments and call the specific command method.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */

  private void grayScaleOperations(String[] command, ImageProcessingModel model) {
    switch (command[1]) {
      case "red-component":
        //red-component koala koala-value-grayscale
        redGrayScale(command, model);
        break;
      case "green-component":
        //green-component koala koala-value-grayscale
        greenGrayScale(command, model);
        break;
      case "blue-component":
        //blue-component koala koala-value-grayscale
        blueGrayScale(command, model);
        break;
      case "value-component":
        //value-component koala koala-value-grayscale
        valueGrayScale(command, model);
        break;
      case "luma-component":
        //luma-grayscale koala koala-value-grayscale
        lumaGrayScale(command, model);
        break;
      case "intensity-component":
        //intensity-grayscale koala koala-value-grayscale
        intensityGrayScale(command, model);
        break;
      default:
        System.out.println("Not a supported greyscale command!");
        break;
    }
  }

  /**
   * This is red greyscale operation method.
   * This method takes red greyscale argument and perform red greyscale operation.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */
  private void redGrayScale(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.createRedGray(command[2], command[3]);
      System.out.println("red grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * This is green greyscale operation method.
   * This method takes green greyscale argument and perform green greyscale operation.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */
  private void greenGrayScale(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.createGreenGray(command[2], command[3]);
      System.out.println("green grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * This is blue greyscale operation method.
   * This method takes blue greyscale argument and perform blue greyscale operation.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */
  private void blueGrayScale(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.createBlueGray(command[2], command[3]);
      System.out.println("blue grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * This is value greyscale operation method.
   * This method takes value greyscale argument and perform value greyscale operation.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */
  private void valueGrayScale(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.valueGrayScale(command[2], command[3]);
      System.out.println("Value grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * This is luma greyscale operation method.
   * This method takes luma greyscale argument and perform luma greyscale operation.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */
  private void lumaGrayScale(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.lumaGrayScale(command[2], command[3]);
      System.out.println("Value grayscale of " + command[2] + " is generated!");
      System.out.println("You can proceed to next commands, save or quit!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * This is intensity greyscale operation method.
   * This method takes intensity greyscale argument and perform intensity greyscale operation.
   *
   * @param command takes the command array.
   * @param model   takes model as an input to perform operation.
   */
  private void intensityGrayScale(String[] command, ImageProcessingModel model) {
    try {
      checkLegalArgument(command.length, 4);
      model.intensityGrayScale(command[2], command[3]);
      System.out.println("Value grayscale of " + command[2] + " is generated!");
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
