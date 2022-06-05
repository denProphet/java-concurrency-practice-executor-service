package work1.tasks;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Callable task
 * Получение максимального значения из коллекции целых чисел
 */

public class FindMaxValueFromIntegerCollectionTask implements Callable<Integer> {
    private final Collection<Integer> items;

    public FindMaxValueFromIntegerCollectionTask(Collection<Integer> items) {
        this.items = items;
    }

    @Override
    public Integer call() throws Exception {

       return items.stream().max((o1, o2) -> o1-o2).get();

    }
}
