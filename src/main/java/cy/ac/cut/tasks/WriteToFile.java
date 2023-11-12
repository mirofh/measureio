package cy.ac.cut.tasks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Write to Disk
 */
public class WriteToFile implements MeasureTask {

    private String tmpDir;

    public WriteToFile () {
        setup();
    }

    /**
     * Create a new temp file name
     *
     * @return
     */
    private Path getNewFileName () {
        return Paths.get(tmpDir, System.nanoTime() + "-write.bin");
    }

    @Override
    public void setup() {
        this.tmpDir = System.getProperty("user.dir") + "/writeToFile/";

        // create output dir if it doesn't exist
        if (!Files.exists(Path.of(tmpDir))) {
            try {
                Files.createDirectories(Path.of(tmpDir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public long run(long sizeInBytes) {

        Path filePath = getNewFileName();

        // creates byte stream
        byte[] data = MeasureUtils.generateRandomData(sizeInBytes);

        long beginTime = System.nanoTime();
        long totalTime = 0L;

        // write to file
        FileOutputStream fos;
        try  {
            fos = new FileOutputStream(filePath.toString());
            fos.write(data);
            totalTime = System.nanoTime() - beginTime;
        fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalTime;
    }

}
