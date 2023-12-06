package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
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
import model.EnhancedImageProcessingModel;
import model.Pixel;
import view.ImageProcessingView;

/**
 * This is our controller class.
 * This class holds the running of the other commands.
 */
public class ImageProcessingControllerImpl implements Features {
  private final InputStream input;
  private final EnhancedImageProcessingModel model;
  private final Map<String, Function<Scanner, CommandController>> knownCommands = new HashMap<>();
  private final Map<String, Pixel[][]> combineGreyScaleImages = new HashMap<>();
  int i = 1;
  private String objectName = "image";
  private ImageProcessingView view;
  private List<String> runCommands;
  private Stack<String> currentImage;

  /**
   * This is ImageProcessingControllerImpl class constructor.
   *
   * @param input takes the system input parameter.
   * @param view  will show the output from Scanner class.
   * @param model takes the model object as input.
   */
  public ImageProcessingControllerImpl(InputStream input, ImageProcessingView view,
                                       EnhancedImageProcessingModel model) {
    this.input = input;
    this.view = view;
    this.model = model;
    this.runCommands = new ArrayList<>();
    storeCommands();
    this.currentImage = new Stack<>();
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
  public void setView(ImageProcessingView v) {
    this.view = v;
    this.view.addFeatures(this);
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
    } else {
      commandExecution(input);
    }
  }


  @Override
  public void loadImage(String imageName) {
    try {
      String imagePath = view.load();
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("load", null);
      cc = cmd.apply(new Scanner("load" + " "
          + imagePath + " " + imageName));
      cc.executeCommand(this.model);
      this.currentImage.push(imageName);
    } catch (IOException e) {
      view.displayError("Error while loading image!");
    }
    view.displayImage(imageName);
  }


  @Override
  public void saveImage() {
    try {
      String imagePath = view.save();
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("save", null);
      cc = cmd.apply(new Scanner("save" + " "
          + imagePath + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error while saving image!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void verticalFlip() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("vertical-flip", null);
      cc = cmd.apply(new Scanner("vertical-flip" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
      this.currentImage.push(objectName);
    } catch (IOException e) {
      view.displayError("Error performing vertical flip operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void horizontalFlip() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("horizontal-flip", null);
      cc = cmd.apply(new Scanner("horizontal-flip" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (Exception e) {
      view.displayError("Error performing horizontal flip operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void valueGrayscale() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "value-component" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing value greyscale operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void lumaGrayscale() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "luma-component" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing luma greyscale operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void intensityGrayscale() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "intensity-component" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing intensity greyscale operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void redGrayscale() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "red-component" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing red greyscale operation!");
    }
    Pixel[][] image = model.getImage(currentImage.peek());
    model.putImage("red", image);
    combineGreyScaleImages.put("red", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void blueGrayscale() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "blue-component" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing blue greyscale operation!");
    }
    Pixel[][] image = model.getImage(currentImage.peek());
    model.putImage("blue", image);
    combineGreyScaleImages.put("blue", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void greenGrayscale() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("greyscale", null);
      cc = cmd.apply(new Scanner("greyscale" + " " + "green-component" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing green greyscale operation!");
    }
    Pixel[][] image = model.getImage(currentImage.peek());
    model.putImage("green", image);
    combineGreyScaleImages.put("green", image);
    view.displayImage(currentImage.peek());
  }

  @Override
  public void dither() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("dither", null);
      cc = cmd.apply(new Scanner("dither" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing dither operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void blur() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("filter", null);
      cc = cmd.apply(new Scanner("filter" + " " + "blur" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing blur operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void sharpen() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("filter", null);
      cc = cmd.apply(new Scanner("filter" + " " + "sharpen" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing blur operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void sepiaTone() {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("color-transform", null);
      cc = cmd.apply(new Scanner("color-transform" + " " + "sepia" + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing sepia operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void brightness(int value) {
    try {
      CommandController cc;
      Function<Scanner, CommandController> cmd =
          knownCommands.getOrDefault("brighten", null);
      cc = cmd.apply(new Scanner("brighten" + " " + value + " "
          + objectName + " " + currentImage.peek()));
      cc.executeCommand(this.model);
    } catch (IOException e) {
      view.displayError("Error performing brightness operation!");
    }
    view.displayImage(currentImage.peek());
  }

  @Override
  public void combineImage() {
    if (model.getImage("red").length > 0 && model.getImage("green").length > 0
        && model.getImage("blue").length > 0) {
      try {
        CommandController cc;
        Function<Scanner, CommandController> cmd =
            knownCommands.getOrDefault("rgb-combine", null);
        cc = cmd.apply(new Scanner("rgb-combine" + " " + "red" + " "
            + "green" + " " + "blue" + " " + currentImage.peek()));
        cc.executeCommand(this.model);
      } catch (IOException e) {
        view.displayError("Error performing combine operation!");
      }
      view.displayImage(currentImage.peek());
    }
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
}
