package controller.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.CommandController;
import controller.ImageProcessingController;
import controller.ImageProcessingSimpleControllerImpl;
import model.ImageProcessingModel;
import view.View;
import view.ViewImpl;

/**
 * This is run script class which runs the text file.
 */
public class RunScriptFile implements CommandController {
  private final String[] commandList;
  private final List<String> runCommands;

  /**
   * This is run script class constructor.
   *
   * @param input takes the command input string.
   */
  public RunScriptFile(String input) {
    this.commandList = input.split(" ");
    this.runCommands = new ArrayList<>();
    processScript();
  }

  @Override
  public void executeCommand(ImageProcessingModel model) throws FileNotFoundException {
    for (String runCommand : this.runCommands) {
      View view = new ViewImpl();
      ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(null,
          view, model);
      Scanner sc = new Scanner(runCommand);
      controller.commandExecution(sc);
    }

  }

  /**
   * This is processes all the inputs received from text file.
   */
  private void processScript() {
    try {
      File file = new File(commandList[1]);
      if (!file.exists()) {
        throw new FileNotFoundException("File Not Found!");
      }
      checkLegalArgument(commandList.length, 2);
      if (!commandList[1].endsWith(".txt")) {
        throw new IllegalArgumentException("Only .txt scripts are accepted!");
      }
      BufferedReader br = new BufferedReader(new FileReader(file));

      String line = br.readLine();
      while (line != null) {
        if (!line.isBlank()) {
          if (line.charAt(0) != '#' && !line.trim().isEmpty()) {
            this.runCommands.add(line);
          }
        }
        line = br.readLine();
      }
      br.close();
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
