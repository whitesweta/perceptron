import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Shweta on 2/04/2017.
 */
public class Main {
    private int rows = 10;
    private int cols = 10;
    private boolean[][] image = new boolean[rows][cols];
    private String category = "other";
    private String categoryName;

    private long seed = 10L;

    private static ArrayList<Image> images = new ArrayList<Image>();
    private static ArrayList<Double> weights = new ArrayList<Double>();

    public static ArrayList<Feature> getFeatures() {
        return features;
    }

    private static ArrayList<Feature> features= new ArrayList<Feature>();


    public void load(String filePath){
        System.out.println("loading");
        boolean[][] newimage = null;
        try{
            java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
            //System.out.println(file);
            Scanner file = new Scanner(new File(filePath));
            //System.out.println(file.length());
            if (!file.next().equals("P1")){
                System.out.println("Not a P1 PBM file" );
            }
            category = file.next().substring(1);
            if (!category.equals("other")) categoryName=category;
            rows = file.nextInt();
            cols = file.nextInt();

            newimage = new boolean[rows][cols];
            for (int row=0; row<rows; row++){
                for (int column=0; column<cols; column++){
                    newimage[row][column] = (file.findWithinHorizon(bit,0).equals("1"));
                }
                images.add(new Image(newimage,category));
                //System.out.println("added image");
            }
            file.close() ;
        }
        catch(IOException e){
            System.out.println("Load from file failed");
        }
        if (newimage!=null) {
            image=newimage;
        }
    }

    public void initialiseFeatures() {
        Random randomS = new Random(seed);
        for (int i =0; i<54;i++){
            Random random = new Random(randomS.nextLong());
            int[] rs = new int[4];
            int[] cs = new int[4];
            boolean[] ss = new boolean[4];

            for(int j=0;i<4;i++){
                int k = random.nextInt(10);
                rs[j] = k;
            }

            for(int j=0;i<4;i++){
                int k = random.nextInt(10);
                cs[j] = k;
            }

            for(int j=0;i<4;i++){
                boolean k = random.nextBoolean();
                ss[j] = k;
            }
            features.add(new Feature(rs,cs,ss));
        }
        System.out.println("features.size()"+features.size());
    }

    public void initialiseWeights(){
        //System.out.println("initWeights");
        //weights = new double [51];
        Random random = new Random(seed);
        weights.add(1.0);
        for(int i=1;i<51;i++) {
            weights.add(random.nextDouble()-0.5);
        }
    }


    public static void main(String[] args){
        Main main = new Main();
        main.load(args[0]);
        main.initialiseFeatures();
        main.initialiseWeights();
        Perceptron perceptron = new Perceptron(images, weights);
        perceptron.perceptronAlgorithm();
    }
}
