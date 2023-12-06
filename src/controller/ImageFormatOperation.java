package controller;

import java.io.FileNotFoundException;

import model.Pixel;

/**
 * This is ImageFormatOperation interface.
 * It performs load and save image for all commands.
 */
public interface ImageFormatOperation {
  /**
   * A method to load an image.
   *
   * @param filePath the path where the file is present.
   * @param fileName the key that will be used for pointing to the image in Hashmap.
   * @throws FileNotFoundException if the file is not present in the file path.
   */
  Pixel[][] loadImage(String filePath, String fileName) throws FileNotFoundException;

  /**
   * A method to save an image into PPM format.
   *
   * @param filePath the path where the file should be saved.
   */
  void saveFile(String filePath, Pixel[][] image);

}
