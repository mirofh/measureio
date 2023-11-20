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
        byte[] data = MeasureUtils.generateRandomData(sizeInBytes);
        long beginTime = System.nanoTime();
        // fill with mock value
        Arrays.fill(data, (byte) 10L);
        return  System.nanoTime() - beginTime;
    }

}

