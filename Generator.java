package org.coursera.lab.project1;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Collections;



public class Generator {
    // main for Generator
    public static void main(String[] args) {
        Generator g = new Generator();
        g.execute();
    }

    // creates an ArrayList of n random numbers from 1 of the 3 selected
    // randomNumberGenerators
    ArrayList<Double> populate(int n, int randNumGen) {
        
        ArrayList<Double> random = new ArrayList<Double>();

        // talking to the random number generators
        Random rand = new Random();
        double rv = 0.0;
        // random number generator imported from Java.util
        if (randNumGen == 0)
            rv = rand.nextDouble();
        // random number generator built into Java.lang
        if (randNumGen == 1)
            rv = Math.random();
        // random number generator imported from java.util.concurrent
        if (randNumGen == 2)
            rv = ThreadLocalRandom.current().nextDouble();

        for(int i = 0;i<n;i++){
            random.add(rv);
        }

        return random; // return the ArrayList of n random values
    }
 
    // calculates mean, sample standard deviation, minimum, and maximum
    // from the randomValues provided, and returns an ArrayList with
    // five results [n, mean, stddev, min, max]

    ArrayList<Double> statistics(ArrayList<Double> randomValues) {
        // your code here
        ArrayList<Double> arr = new ArrayList<Double>();
        int n = randomValues.size();
        double mean = 0.0;
        for (double d:randomValues) {
            mean = mean + d;
        }
        mean /= n;
        arr.add((double)n);
        arr.add(mean);
        arr.add(std(arr, n, mean));
        arr.add(Collections.max(arr));
        arr.add(Collections.min(arr));
        return arr; // return the ArrayList of five results
    }

    double std(ArrayList<Double> rv, int size, double mean)
    {
        double sum = 0;
        for (double d:rv) {
            sum = sum + Math.pow((d - mean), 2);
        }
        double stanDev = Math.sqrt(sum / (size - 1));
        return stanDev;
    }
        

    // displays the ArrayList of five result values in a tabular fashion in the
    // system console 
    void display(ArrayList<Double> results, boolean headerOn) {
       
        if (headerOn) {
            System.out.printf("%-10s%-15s%-15s%-15s%-15s\n", "n", "Mean", "StdDev", "Min", "Max");
        }
        System.out.printf("%-10.0f%-15.6f%-15.6f%-15.6f%-15.6f\n",results.get(0), results.get(1), results.get(2), results.get(3),results.get(4));
    }

    // calls populate, statistics, and display methods for each combination of n and
    // randomNumberGenerator â€“ a total of 9 results
    void execute() {
        String[] rm = { "Java.util.Random", "Math.random", "ThreadLocalRandom" };
        // select the random number generator
        for (int k = 0; k < 3; k++) {
            // select n
            for (int i = 1; i < 4; i++) {
                int n = (int) Math.pow(10.0, (double) i);
                ArrayList<Double> rv = populate(n, k);    // calls your populate method
                ArrayList<Double> res = statistics(rv);   // calls your statistics method
                boolean headerOn = false;
                if (i == 1) {
                    headerOn = true;
                    System.out.println(rm[k]);
                }
                display(res, headerOn); // calls your display method
            }
        }
    }
}
