package controller.imageformatoptions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageFormatOperation;
import model.Pixel;

/**
 * This is JPEG class.
 */
public class JPEG implements ImageFormatOperation {

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

    Pixel[][] image;
    if ((filePath.toLowerCase().endsWith(".jpeg")) || (filePath.toLowerCase().endsWith(".jpg"))) {
      try {
        BufferedImage img = ImageIO.read(new File(filePath));
        int width = img.getWidth();
        int height = img.getHeight();
        //int maxPixelValue = (1 << img.getColorModel().getPixelSize()) - 1;
        image = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int rgb = img.getRGB(j, i);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            image[i][j] = new Pixel(r, g, b);
          }
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new IllegalArgumentException("Not a valid file. Only JPEG or JPG files are supported");
    }

    return image;
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
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image[i][j].get(0);
        int green = image[i][j].get(1);
        int blue = image[i][j].get(2);
        int rgb = (red << 16) | (green << 8) | blue;
        newImage.setRGB(j, i, rgb);
      }
    }
    File output = new File(filePath);
    try {
      ImageIO.write(newImage, "jpg", output); //JPG or JPEG both are the same format.
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
