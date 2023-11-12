package cy.ac.cut;

import cy.ac.cut.tasks.*;

public class MeasureIO {

    void run() {
        long maxSize = (long) Math.pow(2,28);

        MeasureTask writeToFile = new WriteToFile();
        for (int i=2; i<maxSize; i*=2) {
            System.out.println("writing to file. bytes: "+ i + " time: " + writeToFile.run(i) + " ns");
        }

        MeasureTask readFromFile = new ReadFromFile();
        for (int i=2; i<maxSize; i*=2) {
            System.out.println("reading from file. bytes: "+ i + " time: " + readFromFile.run(i) + " ns");
        }

        MeasureTask writeToMem = new WriteToMem();
        for (int i=2; i<maxSize; i*=2) {
            System.out.println("writing to memory. bytes: "+ i + " time: " + writeToMem.run(i) + " ns");
        }

        MeasureTask readFromMem = new ReadFromMem();
        for (int i=2; i<maxSize; i*=2) {
            System.out.println("reading from memory. bytes: "+ i + " time: " + readFromMem.run(i) + " ns");
        }
    }

    public static void main(String[] args) {
        MeasureIO m = new MeasureIO();
        m.run();
    }

}