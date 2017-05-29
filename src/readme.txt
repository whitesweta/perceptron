A program that implement a perceptron with random features that learns to distinguish between two classes of black and white images (X’s and O’s).

The program takes one file name (the image data file) as a command line argument, then
• loads the set of images from the data file
• constructs a set of random features
• constructs a perceptron that uses the features as inputs
• trains the perceptron on the images until either it has presented the whole set of images at least 100
times or the perceptron weights have converged (ie, the perceptron is correct on all the images).
• report on the number of training cycles to convergence, or the number of images that are still classified
wrongly.
• print out the random features that it created, and the final set of weights the perceptron acquired.


to run, insert the image.data file as a command line argument and run the main class
