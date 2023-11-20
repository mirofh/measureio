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
        byte[] data = MeasureUtils.generateRandomData(sizeInBytes);
        Arrays.fill(data, (byte) 10L);

        long beginTime = System.nanoTime();
        byte[] bytesToRead = new byte[(int) sizeInBytes];
        return  System.nanoTime() - beginTime;
    }

}

