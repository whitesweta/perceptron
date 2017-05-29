import java.util.ArrayList;
import java.util.List;

/**
 * Created by shwetabarapatre on 7/04/17.
 */
public class Perceptron {

    private final List<Image> images;
    private final ArrayList<Double> weights;

    public Perceptron(List<Image> images, ArrayList<Double> weights) {
        this.images = images;
        this.weights = weights;
    }

    public void perceptronAlgorithm(){
        for (int epoch=0;epoch<100;epoch++){
            int correct=0;
            predict();
            for (Image image:images){
                image.calcFeatureValues();
                //if correct do nothing
                if(image.getCat().equals(image.getPrediction())){
                    correct++;
                }
                //If -ve example and wrong
                else{
                    if (image.getCat().equals("other")){
                        if(image.getPrediction().equals("Yes")){
                            for (int neg = 0; neg < 51; neg++) {
                                //Subtract feature vector from weight vector
                                double weight = weights.get(neg);
                                weights.set(neg, weight - image.featureValues[neg]);
                            }
                        }
                    }
                    //If +ve example and wrong
                    else if (image.getCat().equals("Yes")){
                        if(image.getPrediction().equals("other")) {
                            for (int pos = 0; pos < 51; pos++) {
                                //Add feature vector to weight vector
                                double weight = weights.get(pos);
                                weights.set(pos, weight + image.featureValues[pos]);
                            }
                        }
                    }
                }
                // System.out.println("correct" + correct + " total"+images.size());
                System.out.println("Accuracy: "+(((double)correct/images.size())*100.00)+"%");
                System.out.println("Final set of weights: ");
                for(double weight : weights) {
                    System.out.println("    "+weight);
                }
            }
        }
    }

    private void predict(){
        //System.out.println("predict");
        for (Image i: images){
            double prediction = 0;
            for (int j=0;j<i.featureValues.length;j++){
                prediction = prediction + (weights.get(j)*i.featureValues[j]);
            }

            if (prediction>0){
                i.setPrediction("Yes");
            }
            else{
                i.setPrediction("other");
            }

        }
    }
}
