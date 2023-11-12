package cy.ac.cut.tasks;

/**
 * Defines an i/o task to be performance
 */
public interface MeasureTask {

    void setup();

    // returns the time to run the task
    long run(long sizeInBytes);

}
