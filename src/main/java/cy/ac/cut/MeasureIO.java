package cy.ac.cut;

import cy.ac.cut.tasks.*;

import java.util.*;

public class MeasureIO {


    /**
     * Run the task and get its runtime.
     * All measurements are in nanoseconds.
     *
     * @param amountOfData
     * @param task
     * @return
     */
    TreeMap<Integer, Long> getTime (long amountOfData, MeasureTask task) {
        TreeMap<Integer, Long> m = new TreeMap<>();
        long runtime = 0L;
        System.out.println("AmountOfData," + task.getName());
        for (int i=2; i<amountOfData; i*=2) {
            runtime = task.run(i);
            m.put(i, runtime);
            System.out.println(i + "," + runtime);
        }
        return m;
    }

    void runAndPrintToCSV() {
        long maxSize = (long) Math.pow(2, 28);

        TreeMap<String, TreeMap<Integer, Long>> m = new TreeMap<>();

        m.put("WriteToFile", getTime(maxSize, new WriteToFile()));
        m.put("ReadFromFile", getTime(maxSize, new ReadFromFile()));
        m.put("WriteToMem", getTime(maxSize, new WriteToMem()));
        m.put("ReadFromMem", getTime(maxSize, new ReadFromMem()));

        // in order header
        List<String> header = new ArrayList<>();
        header.add("WriteToFile");
        header.add("ReadFromFile");
        header.add("WriteToMem");
        header.add("ReadFromMem");

        OutputCSV output = new OutputCSV();
        output.writeAndClose("output.csv", header, m);
    }

    void run() {
        long maxSize = (long) Math.pow(2, 28);
        getTime(maxSize, new WriteToFile());
        getTime(maxSize, new ReadFromFile());
        getTime(maxSize, new WriteToMem());
        getTime(maxSize, new ReadFromMem());
    }

    public static void main(String[] args) {
        MeasureIO m = new MeasureIO();
        m.runAndPrintToCSV();
    }

}