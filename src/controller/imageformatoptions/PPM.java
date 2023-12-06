package controller.imageformatoptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import controller.ImageFormatOperation;
import model.ImageUtil;
import model.Pixel;

/**
 * This is our Model class.
 * This class holds the methods for performing various operations on the image.
 */
public class PPM implements ImageFormatOperation {

  /**
   * A method to load an image given the ppm file that represents the image.
   *
   * @param filePath the path where the PPM file is present.
   */
  @Override
  public Pixel[][] loadImage(String filePath, String storeFileName) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found!");
    }
    if (!filePath.toLowerCase().endsWith(".ppm")) {
      throw new IllegalArgumentException("Not a valid file. Only PNG, PPM, BMP, " +
          "JPEG files are supported");
    }
    Pixel[][] pixelData = ImageUtil.readPPM(filePath);
    return pixelData;
  }

  /**
   * A method to save an image into PPM format.
   *
   * @param filePath the path where the file should be saved.
   */
  @Override
  public void saveFile(String filePath, Pixel[][] image) {
    try {
      //      Pixel[][] image = storage.fetchImage(searchImageKey);
      PrintWriter writer = new PrintWriter(new FileOutputStream(filePath));
      // Write ppm header information
      writer.println("P3");
      writer.println(image[0].length + " " + image.length);
      writer.println("255");

      // Write pixel data
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          writer.println(image[i][j].get(0));
          writer.println(image[i][j].get(1));
          writer.println(image[i][j].get(2));
        }
        writer.println();
      }

      writer.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }


}
