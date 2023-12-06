package model;

import java.io.FileNotFoundException;

/**
 * This interface represents the list of operations to be performed on a PPM file that represents an
 * RGB image.
 */
public interface ImageProcessingModel {

  /**
   * A method to load the image in the model Hashmap. The controller will call this function when
   * it receives the input file.
   *
   * @param image         the image to add in Hashmap.
   * @param storeFileName the key that will be used for pointing to the image in Hashmap.
   */
  void loadImage(Pixel[][] image, String storeFileName) throws FileNotFoundException;

  /**
   * A method to visualize red gray component of the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  void createRedGray(String searchImageKey, String storeKey);

  /**
   * A method to visualize green gray component of the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  void createGreenGray(String searchImageKey, String storeKey);

  /**
   * A method to visualize blue gray component of the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  void createBlueGray(String searchImageKey, String storeKey);

  /**
   * A method to get a grey scale version of the image using the value component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  void valueGrayScale(String searchImageKey, String storeKey);

  /**
   * A method to get a grey scale version of the image using the luma component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  void lumaGrayScale(String searchImageKey, String storeKey);

  /**
   * A method to get a grey scale version of the image using the intensity component.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new greyscale image in the Hashmap
   */
  void intensityGrayScale(String searchImageKey, String storeKey);

  /**
   * A method to flip the image vertically.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the newly flipped image in the Hashmap
   */
  void flipVertically(String searchImageKey, String storeKey);

  /**
   * A method to flip the image horizontally.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the newly flipped image in the Hashmap
   */
  void flipHorizontally(String searchImageKey, String storeKey);

  /**
   * A method to darken/brighten the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new brightened/darkened image in the Hashmap.
   * @param value          the value for increasing or decreasing the brightness.
   */
  void imageBrightenDarken(String searchImageKey, String storeKey, int value);

  /**
   * A method to apply sepia tone filter over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new sepia toned image in the Hashmap
   */
  void createSepiaTone(String searchImageKey, String storeKey);

  /**
   * A method to apply Dithering over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new dithered image in the Hashmap
   */
  void createDithering(String searchImageKey, String storeKey);

  /**
   * A method to apply blurring over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the new blurred image in the Hashmap
   */
  void createBlur(String searchImageKey, String storeKey);

  /**
   * A method to apply sharpening over the image.
   *
   * @param searchImageKey the key of the image over which the operation has to be performed.
   * @param storeKey       the key to refer to the sharpened image in the Hashmap
   */
  void sharpenImage(String searchImageKey, String storeKey);

  /**
   * A method to return red, green and blue grayscale images of the original images.
   *
   * @param searchImageKey the key of the input image  to perform the operation over in the Hashmap.
   * @param storeKeys      the keys that will be used to refer the three grey scale images that will
   *                       be added to Hashmap.
   */
  void splitImage(String searchImageKey, String[] storeKeys);

  /**
   * A method to return an RGB image provided each channel's greyscale image.
   *
   * @param searchImageKeys the keys of the greyscale images that refer to the corresponding
   *                        grey scale images in Hashmap.
   * @param storeKey        the key for adding the resultant image in the Hashmap.
   */
  void combineImage(String[] searchImageKeys, String storeKey);


  /**
   * A method to save an image into PPM format.
   *
   * @param filePath       the path where the file should be saved.
   * @param searchImageKey the key of the image in the Hashmap that should be saved.
   */
  Pixel[][] saveFile(String filePath, String searchImageKey);

}
