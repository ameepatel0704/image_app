package controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ColorTransformation;
import controller.commands.Dither;
import controller.commands.Filter;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageBrightenDarken;
import controller.commands.LoadImage;
import controller.commands.RgbCombine;
import controller.commands.RgbSplit;
import controller.commands.RunScriptFile;
import controller.commands.SaveImage;
import controller.commands.VerticalFlip;
import model.ImageProcessingModel;
import view.View;

/**
 * This is our controller class.
 * This class holds the running of commands.
 */
public class ImageProcessingSimpleControllerImpl implements ImageProcessingController {
  private final InputStream input;
  private final ImageProcessingModel model;
  private final Map<String, Function<Scanner, CommandController>> knownCommands = new HashMap<>();
  private View view;
  private List<String> runCommands;

  /**
   * This is ImageProcessingControllerImpl class constructor.
   *
   * @param input takes the system input parameter.
   * @param view  will show the output from Scanner class.
   * @param model takes the model object as input.
   */
  public ImageProcessingSimpleControllerImpl(InputStream input, View view,
                                             ImageProcessingModel model) {
    this.input = input;
    this.view = view;
    this.model = model;
    this.runCommands = new ArrayList<>();
    storeCommands();
  }

  /**
   * A method to add all the operations to a Hashmap.
   */
  private void storeCommands() {
    this.knownCommands.put("load", s -> new LoadImage(s.nextLine()));
    this.knownCommands.put("brighten", s -> new ImageBrightenDarken(s.nextLine()));
    this.knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.nextLine()));
    this.knownCommands.put("vertical-flip", s -> new VerticalFlip(s.nextLine()));
    this.knownCommands.put("rgb-combine", s -> new RgbCombine(s.nextLine()));
    this.knownCommands.put("rgb-split", s -> new RgbSplit(s.nextLine()));
    this.knownCommands.put("greyscale", s -> new Greyscale(s.nextLine()));
    this.knownCommands.put("run", s -> new RunScriptFile(s.nextLine()));
    this.knownCommands.put("save", s -> new SaveImage(s.nextLine()));
    this.knownCommands.put("color-transform", s -> new ColorTransformation(s.nextLine()));
    this.knownCommands.put("dither", s -> new Dither(s.nextLine()));
    this.knownCommands.put("filter", s -> new Filter(s.nextLine()));
  }

  @Override
  public void commandExecution(Scanner input) throws FileNotFoundException {
    while (input.hasNext()) {
      CommandController control;
      String inputCommand = input.next();
      String command = inputCommand.split(" ")[0];
      if (command.equalsIgnoreCase("quit")
          || command.equalsIgnoreCase("exit")) {
        return;
      }
      Function<Scanner, CommandController> cmd =
          this.knownCommands.getOrDefault(command, null);
      if (cmd == null) {
        throw new IllegalArgumentException("Invalid");
      } else {
        control = cmd.apply(input);
        control.executeCommand(this.model);
      }
    }

  }

  @Override
  public void inputSelection() throws FileNotFoundException {
    Scanner input = new Scanner(this.input);
    if (this.runCommands.size() > 0) {
      for (String commands : this.runCommands) {
        input = new Scanner(commands);
        commandExecution(input);
      }
      this.runCommands = new ArrayList<>();
      view.display("commands");
    } else {
      commandExecution(input);
    }
  }
}
