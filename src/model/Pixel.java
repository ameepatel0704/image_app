package model;//import java.util.Objects;

/**
 * This class represents a single, immutable pixel in a rgb image represented using the channel
 * values. The channel values are in the integral part of the pixel.
 */
public class Pixel {

  public int red;
  public int green;
  public int blue;

  /**
   * Create a pixel given its red, green and blue values.
   *
   * @param red   the red channel value of the pixel.
   * @param green the green channel value of the pixel.
   * @param blue  the blue channel value of the pixel.
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  /**
   * Return a specific channel value of the pixel.
   *
   * @param channel the channel number (0:red, 1:green, 2:blue).
   * @return the value of the channel, if it is valid.
   * @throws IllegalArgumentException if the channel number is not valid ( anything other than 0, 1
   *                                  or 2).
   */
  public int get(int channel) throws IllegalArgumentException {
    if (channel == 0) {
      return this.red;
    } else if (channel == 1) {
      return this.green;
    } else if (channel == 2) {
      return this.blue;
    }
    throw new IllegalArgumentException("Invalid channel for pixel!");
  }

  /**
   * A method to change the channel values individually.
   *
   * @param channel the channel.
   * @param value   the new value of channel.
   * @throws IllegalArgumentException if channel value is not valid.
   */
  public void set(int channel, int value) throws IllegalArgumentException {
    if (channel == 0) {
      this.red = value;
    } else if (channel == 1) {
      this.green = value;
    } else if (channel == 2) {
      this.blue = value;
    } else {
      throw new IllegalArgumentException("Invalid channel for pixel!");
    }
  }
}


