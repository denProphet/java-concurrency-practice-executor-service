package work1.tasks;

import java.util.Collection;
import java.util.concurrent.Callable;


/**
 * Callable task
 * Получение среднего значения из коллекции целых чисел
 */

    public class FindAvgValueFromIntegerCollectionTask implements Callable<Double> {
        private final Collection<Integer> items;

        public FindAvgValueFromIntegerCollectionTask(Collection<Integer> items) {
            this.items = items;
        }

        @Override
        public Double call() throws Exception {

            return items.stream()

                    .mapToDouble(x -> x)
                    .average()
                    .orElse(0.0);

        }








}
