package model;

import java.io.IOException;

/**
 * A Class add some extra methods on image processing model.
 */
public interface EnhancedImageProcessingModel extends ImageProcessingModel {
  /**
   * A method to return the image from Hashmap provided it's key.
   *
   * @param searchImageKey the key of the image to be retrieved from the Hashmap.
   */
  Pixel[][] getImage(String searchImageKey);

  /**
   * A method to store image object inside map.
   *
   * @param searchImageKey the key of the image to be retrieved from the Hashmap.
   * @param image          the image to be stored in hashmap.
   */
  void putImage(String searchImageKey, Pixel[][] image);

  /**
   * A method to fetch the histogram of the image.
   *
   * @param searchImageKey the key of the image to be retrieved from the Hashmap.
   */
  int[][] fetchHistogram(String searchImageKey) throws IOException;
}
