package controller.imageformatoptions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageFormatOperation;
import model.Pixel;

/**
 * This is BMP class.
 */
public class BMP implements ImageFormatOperation {

  /**
   * A method to load an image from the given file that represents the image.
   *
   * @param filePath      the path where the file is present.
   * @param storeFileName the key name which refers to the image in HashMap.
   * @throws FileNotFoundException if the file to read is not found.
   */
  @Override
  public Pixel[][] loadImage(String filePath, String storeFileName) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found!");
    }
    if (!filePath.toLowerCase().endsWith(".bmp")) {
      throw new IllegalArgumentException("Not a valid file. Only BMP files are supported");
    }
    Pixel[][] newImage;
    try {
      BufferedImage image = ImageIO.read(new File(filePath));
      int width = image.getWidth();
      int height = image.getHeight();
      newImage = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          // Get the RGB values of the current pixel
          int rgb = image.getRGB(j, i);

          // Extract the red, green, and blue values
          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;

          newImage[i][j] = new Pixel(red, green, blue);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return newImage;
  }

  /**
   * A method to save an image into a particular format.
   *
   * @param filePath the path where the file should be saved.
   * @param image    the key of the image in Hashmap to save.
   */
  @Override
  public void saveFile(String filePath, Pixel[][] image) {
    int width = image[0].length;
    int height = image.length;
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = image[y][x].get(0);
        int green = image[y][x].get(1);
        int blue = image[y][x].get(2);
        int rgb = (red << 16) | (green << 8) | blue;
        img.setRGB(x, y, rgb);
      }
    }
    File output = new File(filePath);
    try {
      ImageIO.write(img, "bmp", output);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
