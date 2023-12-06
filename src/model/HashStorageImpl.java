package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This is our HashstorageImpl class.
 * This class holds the methods for adding, fetching pixel array objects and string as key-value
 * pairs.
 */
public class HashStorageImpl implements HashStorage {

  private final Map<String, Pixel[][]> computedCommands;

  public HashStorageImpl() {
    this.computedCommands = new HashMap<>();
  }

  @Override
  public void addImage(String imageName, Pixel[][] inputImage) {
    this.computedCommands.put(imageName, inputImage);
  }

  @Override
  public Pixel[][] fetchImage(String imageName) {
    return computedCommands.get(imageName);
  }

  @Override
  public boolean search(String imageName) {
    return computedCommands.containsKey(imageName);
  }
}
