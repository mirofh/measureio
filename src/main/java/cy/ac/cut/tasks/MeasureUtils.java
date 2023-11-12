package cy.ac.cut.tasks;

import java.util.Random;

public class MeasureUtils {

    /**
     * Create a byte array of size <code>sizeInBytes</code>
     *
     * @param sizeInBytes
     * @return
     */
    public static byte[] generateRandomData(long sizeInBytes) {
        byte[] data = new byte[(int) sizeInBytes];
        new Random().nextBytes(data);
        return data;
    }

}
