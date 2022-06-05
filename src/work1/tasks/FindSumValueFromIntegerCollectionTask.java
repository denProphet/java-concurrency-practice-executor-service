package work1.tasks;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Callable task
 * Получение суммы всех значений из коллекции целых чисел
 */

    public class FindSumValueFromIntegerCollectionTask implements Callable<Integer> {
        private final Collection<Integer> items;

        public FindSumValueFromIntegerCollectionTask(Collection<Integer> items) {
            this.items = items;
        }

        @Override
        public Integer call() throws Exception {

            return items.stream().mapToInt(x->x).sum();

        }
}
