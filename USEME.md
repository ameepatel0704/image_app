# **USEME**
## **Image processing Application**
### **Description**
The application will receive the input file in ppm format and perform certain operations over the image and save it when the users wants. The input commands are provided by user either using text-based scripting or using a text script that contains. If the user provides an invalid command, the program will throw an exception and continue to wait for the correct input command. 

### **How to run the application**
The application can run in three version. It can run as an interactive text based mode or the user can provide a text file with scripts or the user can run the applciation in GUI mode. Open the command terminal from the res folder of the assignment and the type the following command. 

#### To run in text mode:
java -jar Assignment6.jar -text

#### To run a command text file
java -jar Assignment6.jar -file commands_for_jar.txt 
This text files contains set of commands that will perform the operation and save the images in res/test_ppm folder. 

#### To run in GUI  mode
java -jar Assignment6.jar
or Simply double click on Assignment6.jar file. 

### **GUI mode**:
When the GUI is opened, to load a new image:
1. Click on the Open image button and navigate to the path of the image and click on the image to load it.
2. Once the image is loaded, a histogram (line chart) of the image will be generated below. 
3. The user can perform any operation using the buttons provided. 
4. To save the image, click on the save button, choose the destination path and name and save the image

Caution: Only one type of grayscale operation can be performed at a time on an image. To perform another grayscale operation, the original image has to be loaded again.

### **Test Image**:
The test image for testing our application is provided in res folder. Kindly use stop.png,stop.ppm,stop.bmp,stop.jpg for testing our application. 


### **Commands for text based mode and command scripe file**
### **Commands and Explanation:**
When the main file is executed, the program will wait for the user to enter a command in the terminal. Below are the following commands that the application accepts and it's operation is described briefly.

- To load the ppm file into the program:
- ##### File has to be loaded before performing any steps. 
##### load file_path image
This command will load the ppm file in the file path and store the value from the file as a 2D Pixel array and add it a hashmap with the last argument as the key to reference the image.


Ex: load res/husky.ppm husky

The program will search for the husky.PPM file in that mentioned folder and store the pixel values from it as a 2D array of Pixel object and add it in a hashmap with the string husky being it's key called husky. If the file is not present or if the file is not in P3 PPM format an exception will be thrown.


- To brighten or darken the image:
##### brighten value image image_brighter_darker
The command will fetch the pixel array that the string image references to in the hashmap and will brighten it if provided the positive value and will darken if provided the negative value. And then again add the brightened or darkened image in the hashmap with image_brighten_darken string as the key to reference it. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: brighten 10 husky husky-brighter.

The program will fetch the pixel array that the husky string points to in the hashmap and will brighten the image by adding the value 10 to all pixels. After adding, the program will  add the brightened image in the hashmap with husky-brighter as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.

Ex: brighten -10 husky husky-darker.

The program will fetch the pixel array that the husky string points to in the hashmap and will darken the image by subtracting the value 10 to all pixels. After subtracting, the program will  add the darkened image in the hashmap with husky-darker as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.


- To flip an image horizontally:
#####  horizontal-flip image image-horizontal
This command will fetch the pixel array object that the image string points to from that hashmap and will flip the object horizontally and add it to the hashmap with image-horizontal as it's key. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: horizontal-flip husky husky-horizontal

The program will fetch the pixel array that the husky string points to in the hashmap and will perform a horizontal flip on it and again add the flipped image in the hashmap with husky-horizontal as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.


- To flip an image vertically:
##### vertical-flip image iamge-vertical
This command will fetch the pixel array object that the image string points to from that hashmap and will flip the object vertically and add it to the hashmap with image-vertical as it's key. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: vertical-flip husky husky-vertical

The program will fetch the pixel array that the husky string points to in the hashmap and will perform a vertical flip on it and again add the flipped image in the hashmap with husky-vertical as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.


- To create a grayscale based on value component
##### greyscale value-component image image-greyscale-value
This command will fetch the pixel array object that the image string points to from that hashmap and will generate a gray scale version of the image based on the value component and add it to the hashmap with image-greyscale-value as it's key. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: greyscale value-component husky husky-greyscale-value

The program will fetch the pixel array that the husky string points to in the hashmap and will generate a gray scale version of the image based on the value component and again add the new image in the hashmap with husky-greyscale-value as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.

- To create a grayscale based on luma component
##### greyscale luma-component image image-greyscale-luma
This command will fetch the pixel array object that the image string points to from that hashmap and will generate a gray scale version of the image based on the luma component and add it to the hashmap with image-greyscale-luma as it's key. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: greyscale luma-component husky husky-greyscale-luma

The program will fetch the pixel array that the husky string points to in the hashmap and will generate a gray scale version of the image based on the luma component and again add the new image in the hashmap with husky-greyscale-luma as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.


- To create a grayscale based on intensity component
##### greyscale intensity-component husky husky-greyscale-intensity
This command will fetch the pixel array object that the image string points to from that hashmap and will generate a gray scale version of the image based on the intensity component and add it to the hashmap with image-greyscale-intensity as it's key. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: greyscale intensity-component husky husky-greyscale-intensity

The program will fetch the pixel array that the husky string points to in the hashmap and will generate a gray scale version of the image based on the intensity component and again add the new image in the hashmap with husky-greyscale-intensity as it's key. If the husky string is not a key in the hashmap, an exception will be thrown.


- To create a value grayscale based on red channel.
##### greyscale red-component image image-red-value-grayscale
This command will fetch the pixel array object that the image key points to in the hashmap and will create a red-tint of that image. And then will generate a value grayscale of the red-tinted image and and add it to the hashmap with image-red-value-grayscale as key. If the image key is missing in hashmap, an exception will be thrown.

Ex: greyscale red-component husky husky-red-value-grayscale

The program will fetch the pixel array object of the husky key in the hashmap and will create a red-tinted verion of the image. Later it will generate a value grayscale using the red-tinted image and add this new image to the hashmap with husky-red-value-grayscale as it's key. If the husky key is not present in the hashmap then an exception will be thrown.


- To create a value grayscale based on green channel.
##### greyscale green-component image image-green-value-grayscale
This command will fetch the pixel array object that the image key points to in the hashmap and will create a green-tint of that image. And then will generate a value grayscale of the green-tinted image and and add it to the hashmap with image-green-value-grayscale as key. If the image key is missing in hashmap, an exception will be thrown.

Ex: greyscale green-component husky husky-green-value-grayscale

The program will fetch the pixel array object of the husky key in the hashmap and will create a green-tinted verion of the image. Later it will generate a value grayscale using the green-tinted image and add this new image to the hashmap with husky-green-value-grayscale as it's key. If the husky key is not present in the hashmap then an exception will be thrown.


- To create a value grayscale based on blue channel.
##### greyscale blue-component image image-blue-value-grayscale
This command will fetch the pixel array object that the image key points to in the hashmap and will create a blue-tint of that image. And then will generate a value grayscale of the blue-tinted image and and add it to the hashmap with image-blue-value-grayscale as key. If the image key is missing in hashmap, an exception will be thrown.

Ex: greyscale blue-component husky husky-blue-value-grayscale

The program will fetch the pixel array object of the husky key in the hashmap and will create a blue-tinted verion of the image. Later it will generate a value grayscale using the blue-tinted image and add this new image to the hashmap with husky-blue-value-grayscale as it's key. If the husky key is not present in the hashmap then an exception will be thrown.


- To split an image into red, green and blue grayscale
##### rgb-split image image-red image-green image-blue
This command will fetch the pixel array object that the image string points to from that hashmap and will generate three value grayscales based on red, green and blue channel and add all 3 to the hashmap with image-red, image-green, image-blue as keys respectively. If the image key doesn't exist in the hashmap, an exception will be thrown.

Ex: rgb-split husky husky-red husky-green husky-blue

The program will fetch the pixel array that the husky string points to in the hashmapand will generate three value grayscales based on red, green and blue channel and add all 3 to the hashmap with husky-red,husky-green,husky-blue as their keys respectively. If the husky string is not a key in the hashmap, an exception will be thrown.


- To combine red,green and blue grayscale into a RGB image
- ##### To combine image first greyscale images of red, green and blue should be present. For this run split command before combine or generate greyscale images by running red, green, greyscale image generation commands.
##### rgb-combine image-red image-green image-blue image
This command will fetch the grayscales that image-red,image-green,image-blue references to in the hashmap and combine the grayscale to generate a RGB image and add it in the hashmap with image as it's key. If any of the grayscale keys are missing in the hashmap, an exception will be thrown.

Ex: rgb-combine husky husky-red husky-green husky-blue

The program will fetch the value of husky-red,husky-green,husky-blue keys from the hashmap and combine the grayscales to generate a RGB image and add it in the hashmap with husky as it's key. If any of the grayscale keys are missing in the hashmap, an exception will be thrown.

- To generate sepia tone of an image
##### color-transform sepia image image_sepia
This command will generate the sepia tone of an image. This image takes image object and generates sepia under name of image_sepia.

Ex: color-transform sepia husky husky_sepia

The program will generate sepia image of husky. First it will look for image object named husky in map and if present then it will generate sepia of this husky and store it under name of husky_sepia.

- To apply dithering on an image.
##### dither image image_dither
This command will produce dithered image. This image takes image object and generates dithered image under name of image_dither.

Ex: dither husky husky_dither

The program will generate dithered image of husky. First it will look for image object named husky in map and if present then it will generate dithered image of this husky and store it under name of husky_dither.

- To apply filter blur on an image.
##### filter blur image image_blur
This command will apply filter blur on image. This image takes image object and generates blurred image under name of image_blur.

Ex: filter blur husky husky_blur

The program will generate blurred image of husky. First it will look for image object named husky in map and if present then it will generate blurred image of this husky and store it under name of husky_blur.

- To apply filter sharpen on an image.
##### filter sharpen image image_sharpen
This command will apply filter sharpen on image. This image takes image object and generates sharpened image under name of image_sharpen.

Ex: filter sharpen husky husky_sharpen

The program will generate sharpened image of husky. First it will look for image object named husky in map and if present then it will generate sharpened image of this husky and store it under name of husky_sharpen.

- To save a image as PPM
##### save destination image
This command will fetch the value of the image key in hashmap and save it as ppm file in the destination path. if the image key is missing in the hashmap an exception will be thrown.

Ex: save res/husky.ppm husky

The program will fetch the pixel array that the husky key points to in the hashmap and write a ppm file with the values in the array and save the PPM file in the res folder with husky as the PPM file name.


- To run a command script
##### run res/commands.txt
This command will search the res folder for a text file called commands.txt, if the file is present, the program will extract each command and add it as a string to string array and return the array. This array of commands in string data type will be now used as input for running other image operations.
 

- To exit the application
##### exit
This command will exit the application.
