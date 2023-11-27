package cy.ac.cut.tasks;

import java.util.Random;

public class MeasureUtils {

    /**
     * Create a byte array of size <code>sizeInBytes</code>
     *
     * @param sizeInBytes
     * @return
     */
    public static byte[][] generateRandomData(long sizeInBytes) {

        long currSize = sizeInBytes;
        int maxSize = (int) Math.pow(2, 20);

        // array smaller than 1GB
        if (sizeInBytes <= maxSize) {
            System.out.println("Allocating array of " + sizeInBytes);
            byte[][] data = new byte[1][];
            data[0] = new byte[(int) sizeInBytes];
            new Random().nextBytes(data[0]);
            return data;
        }

        // arrays bigger than maxSize are split in portions of maxSize
        int partitions = 0;
        while (currSize >= maxSize) {
            partitions++;
            currSize -= maxSize;
        }

        System.out.println("Allocating array of " + sizeInBytes);
        byte[][] data = new byte[partitions][];
        for (int i=0; i<partitions-1 ; i++) {
            data[i] = new byte[maxSize];
            new Random().nextBytes(data[i]);
        }

        // fill up last partition
        int i = partitions - 1;
        data[i] = new byte[(int) currSize];
        new Random().nextBytes(data[i]);

        return data;
    }

}
