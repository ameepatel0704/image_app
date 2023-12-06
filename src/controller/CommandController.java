package controller;

import java.io.FileNotFoundException;

import model.ImageProcessingModel;

/**
 * This is command controller interface.
 */
public interface CommandController {

  /**
   * This method takes model as an argument and uses the model to perform the commands.
   *
   * @param model takes ImageProcessingModel type which has all the commands.
   */
  void executeCommand(ImageProcessingModel model) throws FileNotFoundException;

  /**
   * This method takes actual commandLength from user input and accepted command length to verify
   * if a proper command is passed or not.
   *
   * @param commandLength  takes the length of the command which user inputted.
   * @param acceptedLength it checks with commandLength to verify if proper command has been passed
   *                       or not.
   */
  void checkLegalArgument(int commandLength, int acceptedLength) throws IllegalArgumentException;
}
