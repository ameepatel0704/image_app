package view;

import controller.Features;

/**
 * This interface represents the operations of view that will be handled by controller.
 */
public interface ImageProcessingView {

  /**
   * This method adds all the features used to present image on GUI.
   *
   * @param features takes the parameter of type Features that is used in controller.
   */
  void addFeatures(Features features);

  /**
   * This method loads the image from the system.
   *
   * @return the imagePath of the image.
   */
  String load();

  /**
   * This method saves the image on the selected path.
   *
   * @return the imagePath of the image.
   */
  String save();

  /**
   * This forms the actual command.
   *
   * @return the actual formed command that has to be run.
   */
  String executeCommand();

  /**
   * This displays the image on GUI.
   *
   * @param imageName takes the imageName
   */
  void displayImage(String imageName);

  /**
   * This displays the error encountered while working on image.
   */
  void displayError(String error);

}
