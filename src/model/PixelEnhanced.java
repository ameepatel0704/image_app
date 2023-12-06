package model;

/**
 * This is pixel enhanced java class having alpha.
 */
public final class PixelEnhanced extends Pixel {

  public int alpha;


  /**
   * Create a pixel given its red, green and blue values.
   *
   * @param red   the red channel value of the pixel.
   * @param green the green channel value of the pixel.
   * @param blue  the blue channel value of the pixel.
   */
  public PixelEnhanced(int red, int green, int blue, int alpha) {
    super(red, green, blue);
    this.alpha = alpha;
  }

  @Override
  public int get(int channel) throws IllegalArgumentException {
    if (channel == 0) {
      return this.red;
    } else if (channel == 1) {
      return this.green;
    } else if (channel == 2) {
      return this.blue;
    } else if (channel == 3) {
      return this.alpha;
    }
    throw new IllegalArgumentException("Invalid channel for pixel!");
  }

  @Override
  public void set(int channel, int value) throws IllegalArgumentException {
    if (channel == 0) {
      this.red = value;
    } else if (channel == 1) {
      this.green = value;
    } else if (channel == 2) {
      this.blue = value;
    } else if (channel == 3) {
      this.alpha = value;
    } else {
      throw new IllegalArgumentException("Invalid channel for pixel!");
    }
  }

}
