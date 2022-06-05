package work1.tasks;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Callable task
 * Получение количество положительных значений из коллекции целых чисел
 * Тест Thread.sleep()
 */

public class FindCountOfPositiveItemsFromIntegerCollectionTask implements Callable<Long> {

    public FindCountOfPositiveItemsFromIntegerCollectionTask(Collection<Integer> items) {
        this.items = items;
    }

    private final Collection<Integer> items;

    @Override
    public Long call() throws Exception {
        Thread.sleep(10000);
        return items.stream().filter(x->x>0).count();
    }
}
