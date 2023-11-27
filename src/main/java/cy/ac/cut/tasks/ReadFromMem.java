package cy.ac.cut.tasks;

import java.util.Arrays;

/**
 * Write to Disk
 */
public class ReadFromMem implements MeasureTask {

    public ReadFromMem () {
        setup();
    }

    @Override
    public String getName() {
        return "ReadFromMem";
    }

    @Override
    public void setup() {
    }

    @Override
    public long run(long sizeInBytes) {
        byte[][] data = MeasureUtils.generateRandomData(sizeInBytes);
        for (int i=0; i<data.length; i++) {
            Arrays.fill(data[i], (byte) 10L);
        }

        long beginTime = System.nanoTime();

        byte[][] bytesToRead = new byte[data.length][];
        for (int i=0; i<data.length; i++) {
            bytesToRead[i] = new byte[(int) sizeInBytes];
        }
        return  System.nanoTime() - beginTime;
    }

}

