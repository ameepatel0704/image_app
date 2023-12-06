package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is our Model class. This class holds the methods for performing various operations on the
 * image.
 */
public class ImageProcessingModelImpl implements EnhancedImageProcessingModel {

  private final HashStorage storage;
  private int width;
  private int height;

  /**
   * The constructor for our ImageprocessingModel. A hashstorage is created everytime a object of
   * the image processing model is created.
   */
  public ImageProcessingModelImpl() {
    this.storage = new HashStorageImpl();
  }


  /**
   * A method to load the image in the model Hashmap. The controller will call this function when it
   * receives the input file.
   *
   * @param image         the image to add in Hashmap.
   * @param storeFileName the key that will be used for pointing to the image in Hashmap.
   */
  @Override
  public void loadImage(Pixel[][] image, String storeFileName) throws FileNotFoundException {
    this.storage.addImage(storeFileName, image);
  }

  /**
   * A method to visualize red component of the image.
   *
   * @param image the image to perform the operation over.
   * @return a red-tinted image.
   */
  private Pixel[][] createRed(Pixel[][] image) {
    width = image[0].length;
    height = image.length;
    Pixel[][] redImage = new Pixel[height][width];
    int red = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        red = image[i][j].get(0);
        redImage[i][j] = new Pixel(red, 0, 0);
      }
    }
    return redImage;
  }

  /**
   * A method to visualize green component of the image.
   *
   * @param image the image to perform the operation over.
   * @return a green-tinted image.
   */

  private Pixel[][] createGreen(Pixel[][] image) {
    width = image[0].length;
    height = image.length;
    Pixel[][] greenImage = new Pixel[height][width];
    int green = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        green = image[i][j].get(1);
        greenImage[i][j] = new Pixel(0, green, 0);
      }
    }
    return greenImage;
  }

  /**
   * A method to visualize blue component of the image.
   *
   * @param image the image to perform the operation over.
   * @return a blue-tinted image.
   */

  private Pixel[][] createBlue(Pixel[][] image) {
    width = image[0].length;
    height = image.length;
    Pixel[][] blueImage = new Pixel[height][width];
    int blue = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blue = image[i][j].get(2);
        blueImage[i][j] = new Pixel(0, 0, blue);
      }
    }
    return blueImage;
  }

  /**
   * A method to visualize red gray component of the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void createRedGray(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    Pixel[][] redGrayImage = redGrayHelper(image);
    this.storage.addImage(storeKey, redGrayImage);
  }

  /**
   * A helper method to create a red grayscale image using the value component.
   *
   * @param image the image to perform the operation over.
   * @return the grayscale image.
   */
  private Pixel[][] redGrayHelper(Pixel[][] image) {
    width = image[0].length;
    height = image.length;
    Pixel[][] red = createRed(image);
    Pixel[][] redGrayImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redGrayImage[i][j] = new Pixel(getValue(red[i][j]), getValue(red[i][j]),
            getValue(red[i][j]));
      }
    }
    return redGrayImage;
  }

  /**
   * A method to visualize green gray component of the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void createGreenGray(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    Pixel[][] greenGrayImage = greenGrayHelper(image);
    this.storage.addImage(storeKey, greenGrayImage);
  }

  /**
   * A helper method to create a green grayscale image using the value component.
   *
   * @param image the image to perform the operation over.
   * @return the grayscale image.
   */
  private Pixel[][] greenGrayHelper(Pixel[][] image) {
    width = image[0].length;
    height = image.length;
    Pixel[][] green = createGreen(image);
    Pixel[][] greenGrayImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        greenGrayImage[i][j] = new Pixel(getValue(green[i][j]), getValue(green[i][j]),
            getValue(green[i][j]));
      }
    }
    return greenGrayImage;
  }

  /**
   * A method to visualize blue gray component of the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void createBlueGray(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    Pixel[][] blueGrayImage = blueGrayHelper(image);
    this.storage.addImage(storeKey, blueGrayImage);
  }

  /**
   * A helper method to create a blue grayscale image using the value component.
   *
   * @param image the image to perform the operation over.
   * @return the grayscale image.
   */
  private Pixel[][] blueGrayHelper(Pixel[][] image) {
    width = image[0].length;
    height = image.length;
    Pixel[][] blue = createBlue(image);
    Pixel[][] blueGrayImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blueGrayImage[i][j] = new Pixel(getValue(blue[i][j]), getValue(blue[i][j]),
            getValue(blue[i][j]));
      }
    }
    return blueGrayImage;
  }

  /**
   * A helper method to return greatest channel value in a pixel.
   *
   * @param pixel a pixel that has r,g,b value
   * @return the channel with the highest value.
   */
  private int getValue(Pixel pixel) {
    if (pixel.get(0) >= pixel.get(1) && pixel.get(0) >= pixel.get(2)) {
      return pixel.get(0);
    } else if (pixel.get(1) >= pixel.get(0) && pixel.get(1) >= pixel.get(2)) {
      return pixel.get(1);
    } else {
      return pixel.get(2);
    }
  }

  /**
   * A method to get a grey scale version of the image using the value component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void valueGrayScale(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    width = image[0].length;
    height = image.length;
    Pixel[][] value = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        value[i][j] = new Pixel(getValue(image[i][j]), getValue(image[i][j]),
            getValue(image[i][j]));
      }
    }
    this.storage.addImage(storeKey, value);
  }

  /**
   * A helper method to return Weighted sum of channel values in the pixel.
   *
   * @param pixel the input pixel.
   * @return an integer after performing the operation.
   */
  private int getWeightedSum(Pixel pixel) {
    return (int) (0.2126 * pixel.get(0) + 0.7152 * pixel.get(1) + 0.0722 * pixel.get(2));
  }

  /**
   * A method to get a grey scale version of the image using the luma component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void lumaGrayScale(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] lumaGray = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        lumaGray[i][j] = new Pixel(getWeightedSum(image[i][j]), getWeightedSum(image[i][j]),
            getWeightedSum(image[i][j]));
      }
    }
    this.storage.addImage(storeKey, lumaGray);
  }

  /**
   * A helper method to return the average of all channel values in the pixel.
   *
   * @param pixel the input pixel with r,g,b values.
   * @return Average of all three channel values.
   */
  private int getAverage(Pixel pixel) {
    return (pixel.get(0) + pixel.get(1) + pixel.get(2)) / 3;
  }

  /**
   * A method to get a grey scale version of the image using the intensity component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  @Override
  public void intensityGrayScale(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    width = image[0].length;
    height = image.length;
    Pixel[][] intensity = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        intensity[i][j] = new Pixel(getAverage(image[i][j]), getAverage(image[i][j]),
            getAverage(image[i][j]));
      }
    }
    this.storage.addImage(storeKey, intensity);
  }

  /**
   * A method to flip the image vertically.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the newly flipped image in the Hashmap
   */
  @Override
  public void flipVertically(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] flippedImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      System.arraycopy(image[height - i - 1], 0, flippedImage[i], 0, width);
    }
    this.storage.addImage(storeKey, flippedImage);
  }

  /**
   * A method to flip the image horizontally.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the newly flipped image in the Hashmap
   */
  @Override
  public void flipHorizontally(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] flippedImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        flippedImage[i][j] = image[i][width - j - 1];
      }
    }
    this.storage.addImage(storeKey, flippedImage);
  }


  /**
   * A method to darken/brighten the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new brightened/darkened image in the Hashmap.
   * @param value          the value for increasing or decreasing the brightness.
   */
  @Override
  public void imageBrightenDarken(String searchImageKey, String storeKey, int value) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] brightenImageDarken = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        brightenImageDarken[i][j] = image[i][j];
        int red = value < 0 ? Math.max((image[i][j].get(0) + value), 0)
            : Math.min(image[i][j].get(0) + value, 255);
        int green = value < 0 ? Math.max((image[i][j].get(1) + value), 0)
            : Math.min(image[i][j].get(1) + value, 255);
        int blue = value < 0 ? Math.max((image[i][j].get(2) + value), 0)
            : Math.min(image[i][j].get(2) + value, 255);

        brightenImageDarken[i][j].set(0, red);
        brightenImageDarken[i][j].set(1, green);
        brightenImageDarken[i][j].set(2, blue);
      }
    }
    this.storage.addImage(storeKey, brightenImageDarken);
  }

  /**
   * A helper method to calculate the sepia value for a pixel.
   *
   * @param pixel The input pixel over which sepia tone is applied.
   * @return the new pixel with sepia tone.
   */
  private Pixel createSepiaValue(Pixel pixel) {
    int red = (int) ((0.393 * pixel.get(0)) + (0.769 * pixel.get(1)) + (0.189 * pixel.get(2)));
    int green = (int) ((0.349 * pixel.get(0)) + (0.686 * pixel.get(1)) + (0.168 * pixel.get(2)));
    int blue = (int) ((0.272 * pixel.get(0)) + (0.534 * pixel.get(1)) + (0.131 * pixel.get(2)));
    red = Math.min(red, 255);
    green = Math.min(green, 255);
    blue = Math.min(blue, 255);
    Pixel sepia = new Pixel(red, green, blue);
    return sepia;
  }

  /**
   * A method to apply sepia tone filter over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new sepia toned image in the Hashmap
   */
  @Override
  public void createSepiaTone(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] sepiaTone = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sepiaTone[i][j] = createSepiaValue(image[i][j]);
      }
    }
    this.storage.addImage(storeKey, sepiaTone);
  }

  /**
   * A method to apply Dithering over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new dithered image in the Hashmap
   */
  @Override
  public void createDithering(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] ditheredImage = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel oldPixel = image[i][j];
        int oldColor = oldPixel.get(0);
        int newColor = (oldColor < 128) ? 0 : 255;
        int error = oldColor - newColor;
        Pixel newPixel = new Pixel(newColor, newColor, newColor);
        ditheredImage[i][j] = newPixel;

        if (j + 1 < width) {
          Pixel rightPixel = image[i][j + 1];
          int rightColor = rightPixel.get(0);
          int newRightColor = overlap(rightColor + (7 * error / 16));
          rightPixel.set(0, newRightColor);
        }

        if (i + 1 < height) {
          if (j - 1 >= 0) {
            Pixel nextRowLeftPixel = image[i + 1][j - 1];
            int nextRowLeftColor = nextRowLeftPixel.get(0);
            int newNextRowLeftColor = overlap(nextRowLeftColor + (3 * error / 16));
            nextRowLeftPixel.set(0, newNextRowLeftColor);
          }

          Pixel nextRowPixel = image[i + 1][j];
          int nextRowColor = nextRowPixel.get(0);
          int newNextRowColor = overlap(nextRowColor + (5 * error / 16));
          nextRowPixel.set(0, newNextRowColor);

          if (j + 1 < width) {
            Pixel nextRowRightPixel = image[i + 1][j + 1];
            int nextRowRightColor = nextRowRightPixel.get(0);
            int newNextRowRightColor = overlap(nextRowRightColor + (1 * error / 16));
            nextRowRightPixel.set(0, newNextRowRightColor);
          }
        }
      }
    }
    this.storage.addImage(storeKey, ditheredImage);
  }

  /**
   * A helper method to clamp the pixel value between 0 and 255.
   *
   * @param value the input value.
   * @return a integer that is between 0 to 255 or 0 or 255.
   */
  private int overlap(int value) {
    return Math.max(0, Math.min(255, value));
  }

  /**
   * A method to apply blurring over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new blurred image in the Hashmap
   */
  @Override
  public void createBlur(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] blurredImage = new Pixel[height][width];

    double[] gaussianFilter = {
        1.0 / 16, 1.0 / 8, 1.0 / 16,
        1.0 / 8, 1.0 / 4, 1.0 / 8,
        1.0 / 16, 1.0 / 8, 1.0 / 16
    };

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        double sumR = 0;
        double sumG = 0;
        double sumB = 0;
        for (int dx = -1; dx <= 1; dx++) {
          for (int dy = -1; dy <= 1; dy++) {
            int ny = y + dy;
            int nx = x + dx;
            if (ny >= 0 && ny < width && nx >= 0 && nx < height) {
              double weight = gaussianFilter[(dx + 1) * 3 + (dy + 1)];

              sumR += weight * image[nx][ny].get(0);
              sumG += weight * image[nx][ny].get(1);
              sumB += weight * image[nx][ny].get(2);
            }
          }
        }

        blurredImage[x][y] = new Pixel((int) sumR, (int) sumG, (int) sumB);
      }
    }
    this.storage.addImage(storeKey, blurredImage);
  }

  /**
   * A method to apply sharpening over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the sharpened image in the Hashmap
   */
  @Override
  public void sharpenImage(String searchImageKey, String storeKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    int height = image.length;
    int width = image[0].length;
    Pixel[][] result = new Pixel[height][width];

    double[][] kernel = {
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double red = 0.0;
        double green = 0.0;
        double blue = 0.0;
        for (int k = -2; k <= 2; k++) {
          for (int l = -2; l <= 2; l++) {
            int row = i + k;
            int col = j + l;
            if (row >= 0 && row < height && col >= 0 && col < width) {
              red += image[row][col].get(0) * kernel[k + 2][l + 2];
              green += image[row][col].get(1) * kernel[k + 2][l + 2];
              blue += image[row][col].get(2) * kernel[k + 2][l + 2];
            }
          }
        }
        int newRed = Math.min(Math.max((int) red, 0), 255);
        int newGreen = Math.min(Math.max((int) green, 0), 255);
        int newBlue = Math.min(Math.max((int) blue, 0), 255);
        result[i][j] = new Pixel(newRed, newGreen, newBlue);
      }
    }

    this.storage.addImage(storeKey, result);
  }

  /**
   * A method to return red, green and blue grayscale images of the original images.
   *
   * @param searchImageKey the key of the input image  to perform the operation over in the
   *                       Hashmap.
   * @param storeKeys      the keys that will be used to refer the three grey scale images that will
   *                       be added to Hashmap.
   */
  @Override
  public void splitImage(String searchImageKey, String[] storeKeys) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    Pixel[][] redGrayImage = redGrayHelper(image);
    Pixel[][] greenGrayImage = greenGrayHelper(image);
    Pixel[][] blueGrayImage = blueGrayHelper(image);
    this.storage.addImage(storeKeys[2], redGrayImage);
    this.storage.addImage(storeKeys[3], greenGrayImage);
    this.storage.addImage(storeKeys[4], blueGrayImage);
  }

  /**
   * A method to return an RGB image provided each channel's greyscale image.
   *
   * @param searchImageKeys the keys of the greyscale images that refer to the corresponding grey
   *                        scale images in Hashmap.
   * @param storeKey        the key for adding the resultant image in the Hashmap.
   */
  @Override
  public void combineImage(String[] searchImageKeys, String storeKey) {
    searchHashStorage(searchImageKeys[1]);
    searchHashStorage(searchImageKeys[2]);
    searchHashStorage(searchImageKeys[3]);
    Pixel[][] redGrayScale = storage.fetchImage(searchImageKeys[1]);
    Pixel[][] greenGrayScale = storage.fetchImage(searchImageKeys[2]);
    Pixel[][] blueGrayScale = storage.fetchImage(searchImageKeys[3]);
    if ((redGrayScale.length == greenGrayScale.length)
        && (greenGrayScale.length == blueGrayScale.length)
        && (redGrayScale.length == blueGrayScale.length)
        && (redGrayScale[0].length == greenGrayScale[0].length)
        && (greenGrayScale[0].length == blueGrayScale[0].length)
        && (redGrayScale[0].length == blueGrayScale[0].length)) {
      Pixel[][] combineImage = new Pixel[redGrayScale.length][redGrayScale[0].length];
      for (int i = 0; i < redGrayScale.length; i++) {
        for (int j = 0; j < redGrayScale[0].length; j++) {
          combineImage[i][j] = new Pixel(redGrayScale[i][j].get(0), greenGrayScale[i][j].get(1),
              blueGrayScale[i][j].get(2));
        }
      }
      this.storage.addImage(storeKey, combineImage);
    } else {
      System.out.println("All 3 grayscales are not the same image");
    }
  }


  /**
   * A method to save an image into PPM format.
   *
   * @param filePath       the path where the file should be saved.
   * @param searchImageKey the key of the image in the Hashmap that should be saved.
   */
  @Override
  public Pixel[][] saveFile(String filePath, String searchImageKey) {
    return this.storage.fetchImage(searchImageKey);
  }

  /**
   * A method to return the image from Hashmap provided it's key.
   *
   * @param searchImageKey the key of the image to be retrieved from the Hashmap.
   */
  @Override
  public Pixel[][] getImage(String searchImageKey) {
    searchHashStorage(searchImageKey);
    Pixel[][] image = storage.fetchImage(searchImageKey);
    return image;
  }

  @Override
  public void putImage(String putImageKey, Pixel[][] image) {
    this.storage.addImage(putImageKey, image);
  }

  @Override
  public int[][] fetchHistogram(String imageName) throws IOException {
    int[][] histogram = new int[4][256];
    Pixel[][] imagePixel = storage.fetchImage(imageName);
    int height = imagePixel.length;
    int width = imagePixel[0].length;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = imagePixel[i][j];
        int red = pixel.get(0);
        int green = pixel.get(1);
        int blue = pixel.get(2);
        int intensity = (red + green + blue) / 3;

        histogram[0][red]++;
        histogram[1][green]++;
        histogram[2][blue]++;
        histogram[3][intensity]++;
      }
    }
    return histogram;
  }

  /**
   * A helper method to check if the key refers to a valid image in the Hashmap.
   *
   * @param searchCommand the key to search in the Hashmap.
   */
  private void searchHashStorage(String searchCommand) {
    if (!this.storage.search(searchCommand)) {
      throw new IllegalArgumentException("There is no image object called "
          + searchCommand + " present in memory to perform operation.");
    }
  }
}
