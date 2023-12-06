package model;

/**
 * This interface represents the operations of adding, fetching key-value pairs and searching for
 * keys in a Hashmap.
 */
public interface HashStorage {

  /**
   * A method to add the pixel[][] object to Hashmap.
   *
   * @param imageName  the key for the image.
   * @param inputImage the image in the form of 2D pixel array.
   */
  void addImage(String imageName, Pixel[][] inputImage);

  /**
   * A method to retrieve an image from the Hashmap.
   *
   * @param imageName the key of the image to be retrieved from the Hashmap.
   */
  Pixel[][] fetchImage(String imageName);

  /**
   * A method to search if an image is present in the Hashmap using it's key as input.
   *
   * @param imageName the key for the image to be searched in the Hashmap.
   * @return boolean true if the key is present and false if not.
   */
  boolean search(String imageName);

}
