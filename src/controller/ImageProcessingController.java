package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This interface represents the operations of controller that will be handled by controller.
 */
public interface ImageProcessingController {

  /**
   * This is commandExecution method.
   * This method holds all the switch cases of the valid commands.
   *
   * @param input It takes scanner as input which is user input or file input.
   */
  void commandExecution(Scanner input) throws FileNotFoundException;

  /**
   * This is inputSelection method.
   * This method checks for the input whether it's coming from file or from user
   * and set it accordingly.
   */
  void inputSelection() throws FileNotFoundException;
}
