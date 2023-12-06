# **Assigment6**
## **Image processing Application**
### **Description**
The application will receive the input file in ppm format and perform certain operations over the image and save it when the users wants. The input commands are provided by user either using text-based scripting or using a text script that contains. If the user provides an invalid command, the program will throw an exception and continue to wait for the correct input command. 

### **Design Overview**
The application has adopted the MVC design pattern. The model consists of all the methods that deal with load the PPM file , saving as PPM file and performing operations over the image, the controller connects the model with view and decided which function to execute based on the input command. 

### **Summary of Design Changes**
- A new interface EnhancedImageProcessingModel has been created that extends the old interface ImageProcessingModel. 
- This new interface has new methods for adding and fetching images from HashMap. Features is a new class that holds commands that links GUI with controller is created and extended by the controller. 
- For view a new interface and implementation is created. This imlpmentation is resposible for the GUI working. 
- Another class called Histogram is used for displaying the histogram(bar graph) in the GUI. This class extends the JPanel
- All parts of the program is completed.

### **Justifications**
- To follow the solid principle of not changing the interface we created two interfaces one in model and other in controller which is extending the old interfaces.
- In view we created a new interface for GUI and kept old interface for performing Command line things.


### **Interfaces and Classes**

#### **Model Classes:**

##### Pixel:
This pixel class is made for storing the rgb value of a pixel in an image. Each object of this pixel class is made up of three integers, each one representing a channel's value. This class has two methods.

1. int get(int channel): This method takes the channel number (0-red,1-green,2-blue) as an argument and returns the value of that channel.
2. void set(int channel,int value) : This methods takes the channel number and the an integer value as arguments and change the corrensponding channel's value with the new value. 

Both the methods will throw an illegal argument exception if an invalid channel number ( >2) is provided. 

##### Pixel Enhanced:
This class extends the Pixel class, apart from the three channels from the pixel class, the pixel enhanced not also has another channel for alpha value. 


##### ImageUtil:
This class is used for reading the ppm file and storing it. We create a 2D array of pixel objects to store the pixel values from the PPM file. This class has the following methods. 

1. Pixel[][] readPPM(String filename): This method the path of the ppm file as an argument. It will read the PPM file and create a 2D array of pixel objects to store the pixel values from the PPM file. The size of the array will be equal to the dimensions of the image. The first triplet of rgb values will be the 1st element in the array and so on. 


##### ImageProcessingModelImpl:
This class consits of all the operations that can be performed over the image. These operations are defined as methods and they are described as below.

1. void loadImage(String filePath, String fileName): This method takes the path of the ppm file and the name under which image object has to be stored and calls the readPPM method from ImageUtil class. After reading the PPM document the method will return  all the pixel values in the ppm file as a 2D array of pixel objects.
2. void createRed(Pixel[][] image): This method will take an image as a pixel array object and convert it into a red-tinted image. 
3. void createGreen(Pixel[][] image): This method will take an image as a pixel array object and convert it into a green-tinted image. 
4. void createBlue(Pixel[][] image): This method will take an image as a pixel array object and convert it into a green-tinted image. 
5. void valueGrayScale(String searchImageKey, String storeKey): This method will take searchImageKey which searches for image object and storeKey which stores the manipulated image under that name and convert it into a grayscale image using the value component.
6. void lumaGrayScale(String searchImageKey, String storeKey): This method will take searchImageKey which searches for image object and storeKey which stores the manipulated image under that name and convert it into a luma grayscale image using the luma component.
7. void intensityGrayScale(String searchImageKey, String storeKey):  This method will take searchImageKey which searches for image object and storeKey which stores the manipulated image under that name and convert it into an intensity grayscale image using the intensity component.
8. void flipVertically(String searchImageKey, String storeKey): This method will take searchImageKey which searches for image object and storeKey which stores the manipulated image under that name and flip it vertically. 
9. void flipHorizontally(String searchImageKey, String storeKey): This method will take searchImageKey which searches for image object and storeKey which stores the manipulated image under that name and flip it vertically. 
10. void imageBrightenDarken(String searchImageKey, String storeKey, int value): The method will take an image and a positive or a negative value, and it will brighten or darken all the pixels in the image respectively by adding or subtracting the value to it. 
11. void splitImage(String searchImageKey, String[] storeKeys): This method will take searchImageKey which searches for image object and an array of storeKeys which contains the key names which is the red-grayscale, green-grayscale and blue-grayscale.
12. void combineImage(String[] searchImageKeys, String storeKey): This method performs the opposite of split method. This method search for the image keys of all three red, green, blue greyscale and store it under name passed to storeKey. 
13. void saveFile(String filePath, String searchImageKey): This method search the image object using searchImageKey and stores it in the provided path on a provided format. 
14. void createRedGray(String searchImageKey, String storeKey): This method searches image object using searchImageKey , creates a red-tinted version and then generate a grayscale version based on value component.
15. void createGreenGray(String searchImageKey, String storeKey): This method searches image object using searchImageKey , creates a green-tinted version and then generate a grayscale version based on value component.
16. void createBlueGray(String searchImageKey, String storeKey): This method searches image object using searchImageKey , creates a blue-tinted version and then generate a grayscale version based on value component.

##### HashStorageImpl
This class contains methods for creating a key value pair, where value being a pixel array object and key being a string, a name for referencing the pixel array object. The methods under this class are as follows:
1. addImage(String imageName, Pixel[][] inputImage): This method creates a key value pair in Hashmap where the pixel array object inputImage is the value and the string imageName is the key that acts as a reference for the inputImage.
2. fetchImage(String imageName): This method takes the string as a key and returns the pixel array object it references to. 
3.  boolean search(String imageName): This method takes the string as a key and searches if any such key is present in the hashmap. 

#### **Controller Classes:**

##### ImageProcessingControllerImpl
This class decides the sequence of methods to be executed. This class contains the method that matches the methods with the user command. 

void inputSelection(): This method decides the input mode. The input mode can be either loading a text file with commands or using terminal to enter commands.

commandExecution(Scanner input): This methods takes the scanner object as an argument and consists of various cases. Each case executes one of the methods found in ImageProcessingModelImpl. Based on the input, these cases will be executed. If a valid command is not provided, this method will throw an exeception. This function end only when quit command is passed.


##### **Controller -> Commands Classes**:

#### ColorTransformation
This class performs sepia-tone of an image. This class constructor takes commands and perform sepia if correct commands are passed.

#### Dither
This class performs dithering on an image. This class constructor takes dithering commands.

#### Filter
This class performs two commands. The filter constructor takes commands as input and checks which filter has been called and perform it on image.
blur: This filter blurs the image.
sharpen: This filter sharpens the image.

#### Greyscale
This class performs six commands. The greyscale constructor takes commands as input and checks which greyscale component has been called and perform it on image.
red-component: This gives the red greyscale component of an image.
green-component: This gives the green greyscale component of an image.
blue-component: This gives the blue greyscale component of an image.
luma-component: This gives the luma greyscale component of an image.
intensity-component: This gives the intensity greyscale component of an image.
value-component: This gives the value greyscale component of an image.

#### HorizontalFlip
This class calls the model to perform horizontal flip on an image.

#### VerticalFlip
This class calls the model to perform vertical flip on an image.

#### HorizontalFlip
This class calls the model to perform horizontal flip on an image.

#### ImageBrightenDarken
The class calls the model of brighten image on getting the brighten command.

#### LoadImage
The class calls the model of load image on getting the load command.

#### RgbCombine
The class calls the model of combine image on getting the combine command.

#### RgbSplit
The class calls the model of split image on getting the split command.

#### SaveImage
The class calls the model of save image on getting the save command.

#### RunScriptFile
The class runs the .txt file on getting the run command.

##### **Controller -> imageformatoptions Classes**:
##### BMP():
This class consists of load and save method for the BMP format images.
##### PNG():
This class consists of load and save method for the PNG format images.
##### PPM():
This class consists of load and save method for the PPM format images.
##### JPEG():
This class consists of load and save method for the JPEG/JPG format images.

##### **View classes**:

##### HistogramPanel
A class for displaying histogram of iamges in the GUI. 

void paintComponent(Graphics g): A method to create the histogram.

int findMaxValue(int[] data): The method to find the max value from the data.

##### ImageProcessingViewImpl
This class holds all the GUI implementation of view.

 void addFeatures(Features features):This method adds all the features used to present image on GUI.

 String load(): This method loads the image from the system and return the imagePath of the image. 

 String save():This forms the actual command for executing the operations. The method returns the actual formed command.

 void displayImage(String imageName):This displays the image on GUI. The parameter takes the image name. 

 void displayError(): This displays the error encountered while working on image.


 ##### ViewImpl
 String display(String s): This displays the error encountered while working on image.

#### **Model Interfaces**
##### ImageProcessingModel: 
This is the interface for the ImageProcessingModelImpl class which contains all the methods for image manipulation, loading a PPM file and saving data as a PPM file.

##### HashStorage
This is the interface for the HashStorageImpl class which contains the methods for adding pixel array objects and strings as value and key pair respectively. 

##### EnhancedImageProcessingModel
An interface add some extra methods on ImageProcessingModel.

##### **Controller Interfaces**
##### ImageProcessingController
This is the interface for the ImageProcessingControllerImpl class which contains the method that decides which method to execute when a command is entered.

##### CommandController
This is the interface for all the commands which contains method that takes model as an arguement and performs all the commands.

##### ImageFormatOperation
This is the interface contains load and save methods to perform on all the different format options available.

##### Features
This interface represents the operations that can be done using the GUI that will be handled by controller.

##### **View Interfaces**
##### ImageProcessingView:
This interface represents the operations of view that will be handled by controller.
##### View:
This is the older view class to display output in command line.

##### **Main:**
The main method consists of an model and controller object and calls the inputSelection() method in ImageProcessingControllerImpl class. 

### **How to Run from IDE(IntelliJ):**
To run the application.
1. Run the Main program from the src folder. 
2. The program will wait for user commands.
3. The user can either type input commands in the IntelliJ terminal or use the load command in the terminal to load the text file that contains the commands.


### **Operations:**
Our application can perform the following operations:
1. Load : The application can read a ppm file
2. Save : The application can write an image into a ppm file format.
3. Flip vertical: The application can flip an image vertically.
4. Flip Horizontal: The application can flip an image horizontally.
5. Gray scale images: The application can generate a gray scale version of the image based on value, intensity or luma component
6. Channel Gray scale image: The application can also generate a gray scale of tinted images based on value component
7. Brighten an image: The application can brighten an image or darken an image.
8. Sepia tone an image: The application can generate sepia tone of an image.
9. Dithering an image: The application can generate dithered image.
10. blur: The filter can blur an image.
11. sharpen: The filter can sharpen an image.


### References:
Link: https://www.cs.cornell.edu/courses/cs664/2003fa/images/




