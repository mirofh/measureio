
package cy.ac.cut.tasks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Read from disk
 */
public class ReadFromFile implements MeasureTask {

    private String tmpDir;

    public ReadFromFile () {
        setup();
    }

    @Override
    public String getName() {
        return "ReadFromFile";
    }

    @Override
    public void setup() {
        this.tmpDir = System.getProperty("user.dir") + "/readFromFile/";

        // create output dir if it doesn't exist
        if (!Files.exists(Path.of(tmpDir))) {
            try {
                Files.createDirectories(Path.of(tmpDir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Create a new temp file name
     *
     * @return
     */
    private Path getNewFileName (long sizeInBytes) {
        return Paths.get(tmpDir, sizeInBytes + "-read.bin");
    }

    private void writeToFile(Path filePath, byte[] data) {
        FileOutputStream fos;
        try  {
            fos = new FileOutputStream(filePath.toString());
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile (Path filePath) {
        if (!Files.exists(filePath)){
            throw new IllegalStateException("Trying to read missing file: " + filePath);
        }

        FileInputStream fos;
        try  {
            fos = new FileInputStream(filePath.toString());
            fos.readAllBytes();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long run(long sizeInBytes) {
        Path filePath = getNewFileName(sizeInBytes);
        byte[] data = MeasureUtils.generateRandomData(sizeInBytes);
        if (!Files.exists(filePath)) {
            writeToFile(filePath, data);
        }
        long beginTime = System.nanoTime();
        readFromFile(filePath);
        return System.nanoTime() - beginTime;
    }

}

