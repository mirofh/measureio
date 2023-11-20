package cy.ac.cut.tasks;

import java.io.*;
import java.util.*;

/**
 * Output results to a csv file
 */
public class OutputCSV {

    private PrintWriter openFile (String path) {

        File file = new File(path);
        if (file.exists()) {
            // System.out.println("Rewriting output file " + file);
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            return new PrintWriter(outputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private String getHeader(List<String> header) {
        StringBuilder h = new StringBuilder();
        h.append("size");
        for (int i=0; i<header.size(); i++) {
            h.append(",");
            h.append(header.get(i));
        }
        h.append("\n");
        return h.toString();
    }

    /**
     * Join results by the amount of read or written data
     *
     * @param header
     * @param m
     * @return
     */
    private String joinResults(List<String> header, TreeMap<String, TreeMap<Integer, Long>> m){
        StringBuilder s = new StringBuilder();

        TreeMap<Integer, Long> firstMeasure = m.get(m.firstKey());
        List<Integer> amountOfData = new ArrayList<>(firstMeasure.keySet());

        for (int amount : amountOfData) {
            s.append(amount);
            for (String measure : header) {
                s.append(",");
                TreeMap<Integer, Long> curr = m.get(measure);
                s.append(curr.get(amount));
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Write results to the output
     *
     * @param outputFile
     * @param header
     * @param m
     */
    public void writeAndClose(String outputFile, List<String> header, TreeMap<String, TreeMap<Integer, Long>> m) {
        PrintWriter output = openFile(outputFile);
        output.write(getHeader(header));
        output.write(joinResults(header, m));
        output.close();
    }

}
