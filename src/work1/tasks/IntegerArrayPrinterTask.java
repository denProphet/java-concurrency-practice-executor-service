package work1.tasks;

import java.util.Collection;

/**
 * Runnable task
 * Печать коллекции целых чисел
 */

public class IntegerArrayPrinterTask implements Runnable{

    private final Collection<Integer> collection;

    public IntegerArrayPrinterTask(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        for (Integer item : collection) {
            System.out.println(item);
        }
    }
}
