package work1;

import work1.tasks.*;
import java.util.Collection;
import java.util.concurrent.*;

public class Test {

    /**
     *
     * Решение 1 задачи
     * (Выполните вывод коллекции целых чисел в отдельной задаче)
     * с использованеием Executor
     * */

    static class SoluteTask1WithExecutor {
        public static void main(String[] args) {

            //обьявление и инициализация коллекции целых чисел
            IntegerArrayPrinterTask integerArrayPrinterTask = new IntegerArrayPrinterTask
                    (CollectionFabric.getRandomIntegerCollection(10, -1000, 1000));

            //Использование Executor
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(integerArrayPrinterTask);

        }
    }

    /**
     *
     * Решение 1 задачи
     * (Выполните вывод коллекции целых чисел в отдельной задаче)
     * с использованеием ExecutorService
     * */

    static class SoluteTask1WithExecutorService {
        public static void main(String[] args) {

            //обьявление и инициализация коллекции целых чисел
            IntegerArrayPrinterTask integerArrayPrinterTask = new IntegerArrayPrinterTask
                    (CollectionFabric.getRandomIntegerCollection(10, 2000, 1000));

            //Использование ExecutorService
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<?> result = executor.submit(integerArrayPrinterTask);
            try{
                result.get();
                System.out.println("Task has done");
            }catch (ExecutionException | InterruptedException ignored){}

        }
    }

    /**
     *
     * Решение 2 задачи
     * (Выполните подсчет минимального, максимального, среднего значения и суммы элементов коллекции.)
     * с использованеием ExecutorService c неск. потоками внутри
     * */

    static class SoluteTask2 {
        public static void main(String[] args) {

            //обьявление и инициализация коллекции целых чисел
            Collection<Integer> items = CollectionFabric.
                    getRandomIntegerCollection(10, -1000, 1000);

            //обьявление и инициализация Runnable&Callable tasks
            IntegerArrayPrinterTask integerArrayPrinterTask = new IntegerArrayPrinterTask(items);

            FindMaxValueFromIntegerCollectionTask findMaxValueFromIntegerCollectionTask =
                    new FindMaxValueFromIntegerCollectionTask(items);

            FindMinValueFromIntegerCollectionTask findMinValueFromIntegerCollectionTask =
                    new FindMinValueFromIntegerCollectionTask(items);

            FindAvgValueFromIntegerCollectionTask findAvgValueFromIntegerCollectionTask =
                    new FindAvgValueFromIntegerCollectionTask(items);

            FindSumValueFromIntegerCollectionTask findSumValueFromIntegerCollectionTask =
                    new FindSumValueFromIntegerCollectionTask(items);

            //Использование ExecutorService
            ExecutorService executor = Executors.newFixedThreadPool(5);

            Future<?> result = executor.submit(integerArrayPrinterTask);
            Future<Integer> resultMax = executor.submit(findMaxValueFromIntegerCollectionTask);
            Future<Integer> resultMin = executor.submit(findMinValueFromIntegerCollectionTask);
            Future<Double> resultAvg = executor.submit(findAvgValueFromIntegerCollectionTask);
            Future<Integer> resultSum = executor.submit(findSumValueFromIntegerCollectionTask);

            try{
                result.get();
                int max = resultMax.get();
                System.out.println("Максимальное значение: " + max);
                int min = resultMin.get();
                System.out.println("Минимальное значение: " + min);
                double avg = resultAvg.get();
                System.out.println("Среднее значение: " + avg);
                int sum = resultSum.get();
                System.out.println("Сумма всех значений: " + sum);
            }catch (ExecutionException | InterruptedException ignored){}


        }
    }

    /**
     *
     * Решение 2 задачи
     * (Выполните подсчет минимального, максимального, среднего значения и суммы элементов коллекции.)
     * с использованеием неск. ExecutorService c 1 потоком внутри
     * */

    static class SoluteTask2WithDiffExecutors {
        public static void main(String[] args) {

            //обьявление и инициализация коллекции целых чисел
            Collection<Integer> items = CollectionFabric.
                    getRandomIntegerCollection(10, -1000, 1000);

            //Печать коллекции
            IntegerArrayPrinterTask integerArrayPrinterTask = new IntegerArrayPrinterTask(items);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            System.out.println("Вывод коллекции: ");
            Future<?> result = executor.submit(integerArrayPrinterTask);
            try{
                result.get(); // бесполезно
            }catch (ExecutionException | InterruptedException ignored){}

            //Поиск максимального в коллекции
            FindMaxValueFromIntegerCollectionTask findMaxValueFromIntegerCollectionTask =
                    new FindMaxValueFromIntegerCollectionTask(items);
            ExecutorService executor2 = Executors.newSingleThreadExecutor();
            Future<Integer> resultMax = executor.submit(findMaxValueFromIntegerCollectionTask);
            try{
                int max = resultMax.get();
                System.out.println("Максимальное значение: " + max);
            }catch (ExecutionException | InterruptedException ignored){}

            //Поиск минимального в коллекции
            FindMinValueFromIntegerCollectionTask findMinValueFromIntegerCollectionTask =
                    new FindMinValueFromIntegerCollectionTask(items);
            ExecutorService executor3 = Executors.newSingleThreadExecutor();
            Future<Integer> resultMin = executor.submit(findMinValueFromIntegerCollectionTask);
            try{
                int min = resultMin.get();
                System.out.println("Минимальное значение: " + min);
            }catch (ExecutionException | InterruptedException ignored){}

            //поиск среднего значения
            FindAvgValueFromIntegerCollectionTask findAvgValueFromIntegerCollectionTask =
                    new FindAvgValueFromIntegerCollectionTask(items);
            ExecutorService executor4 = Executors.newSingleThreadExecutor();
            Future<Double> resultAvg = executor.submit(findAvgValueFromIntegerCollectionTask);
            try{
                double avg = resultAvg.get();
                System.out.println("Среднее значение: " + avg);
            }catch (ExecutionException | InterruptedException ignored){}

            //Сумма всех значений
            FindSumValueFromIntegerCollectionTask findSumValueFromIntegerCollectionTask =
                    new FindSumValueFromIntegerCollectionTask(items);
            ExecutorService executor5 = Executors.newSingleThreadExecutor();

            Future<Integer> resultSum = executor.submit(findSumValueFromIntegerCollectionTask);

            try{
                int sum = resultSum.get();
                System.out.println("Сумма всех значений: " + sum);
            }catch (ExecutionException | InterruptedException ignored){}

        }
    }

    /**
     *
     * Решение 3 задачи:
     * Исследуйте загрузку процессора при использовании Future.
     * Выполните Thread.sleep() в отдельной задаче и ожидайте завершения с использованием
     * метода Future.get()
     * */

    static class SoluteTask3WithFutureGet {
        public static void main(String[] args) {

            //обьявление и инициализация коллекции целых чисел
            Collection<Integer> items = CollectionFabric.
                    getRandomIntegerCollection(10, -1000, 1000);

           FindCountOfPositiveItemsFromIntegerCollectionTask task
                   = new FindCountOfPositiveItemsFromIntegerCollectionTask(items);

           ExecutorService executor = Executors.newSingleThreadExecutor();
           Future<Long> res = executor.submit(task);
           try{
               long count = res.get();
               System.out.println(count);
           }catch (ExecutionException | InterruptedException ignored){}


        }
    }

    /**
     *
     * Решение 3 задачи:
     * Исследуйте загрузку процессора при использовании Future.
     * Выполните Thread.sleep() в отдельной задаче и ожидайте завершения с использованием
     * цикла while и метода Future.isDone()
     * */

    static class SoluteTask3WithFutureIsDone {
        public static void main(String[] args) {

            //обьявление и инициализация коллекции целых чисел
            Collection<Integer> items = CollectionFabric.
                    getRandomIntegerCollection(10, -1000, 1000);

            FindCountOfPositiveItemsFromIntegerCollectionTask task
                    = new FindCountOfPositiveItemsFromIntegerCollectionTask(items);

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Long> res = executor.submit(task);
            try{
                int loopCount = 0;
                while(!res.isDone()){
                    System.out.println(loopCount++);
                }

                long countOfItems = res.get();
                System.out.println(countOfItems);

            }catch (ExecutionException | InterruptedException ignored){}


        }
    }


}
