import java.io.FileNotFoundException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Features;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingSimpleControllerImpl;
import controller.commands.RunScriptFile;
import model.EnhancedImageProcessingModel;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;
import view.View;
import view.ViewImpl;

/**
 * This is our main method.
 * param args are command line arguments.
 */
public class Main {
  /**
   * Returns void.
   *
   * @param args takes arguments.
   */
  public static void main(String[] args) throws FileNotFoundException,
      UnsupportedLookAndFeelException, ClassNotFoundException,
      InstantiationException, IllegalAccessException {
    System.out.println("Hola! Load the file and start manipulating!");
    System.out.println("Please refer the command palette in README.md file");
    if (args.length > 0 && args[0].equals("-file")) {
      ImageProcessingModel model = new ImageProcessingModelImpl();
      if (args.length != 2) {
        System.out.println("Usage: java Main -file name-of-script.txt");
        return;
      }
      String scriptFileName = args[1];
      RunScriptFile scriptFile = new RunScriptFile("-file " + scriptFileName);
      scriptFile.executeCommand(model);
      return;
    }
    if (args.length > 0 && args[0].equals("-text")) {
      ImageProcessingModel model = new ImageProcessingModelImpl();
      View view = new ViewImpl();
      if (args.length != 1) {
        System.out.println("Usage: java Main -file name-of-script.txt");
        return;
      }
      ImageProcessingController controller = new ImageProcessingSimpleControllerImpl(System.in,
          view, model);
      controller.inputSelection();
      return;
    }
    EnhancedImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingViewImpl("Image", model);
    Features controller = new ImageProcessingControllerImpl(System.in,
        view, model);
    controller.setView(view);
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

  }

}