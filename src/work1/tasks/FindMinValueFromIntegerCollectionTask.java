package work1.tasks;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Callable task
 * Получение минимального значения из коллекции целых чисел
 */

    public class FindMinValueFromIntegerCollectionTask implements Callable<Integer> {
        private final Collection<Integer> items;

        public FindMinValueFromIntegerCollectionTask(Collection<Integer> items) {
            this.items = items;
        }

        @Override
        public Integer call() throws Exception {

            return items.stream().min((o1, o2) -> o1-o2).get();

        }
    }

