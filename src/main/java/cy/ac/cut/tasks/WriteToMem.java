package cy.ac.cut.tasks;

import java.util.Arrays;

/**
 * Write to Disk
 */
public class WriteToMem implements MeasureTask {

    public WriteToMem () {
        setup();
    }

    @Override
    public String getName() {
        return "WriteToMem";
    }

    @Override
    public void setup() {
    }

    @Override
    public long run(long sizeInBytes) {
        byte[][] data = MeasureUtils.generateRandomData(sizeInBytes);
        long beginTime = System.nanoTime();
        // fill with mock value
        for (int i=0; i<data.length; i++) {
            Arrays.fill(data[i], (byte) 10L);
        }
        return  System.nanoTime() - beginTime;
    }

}

