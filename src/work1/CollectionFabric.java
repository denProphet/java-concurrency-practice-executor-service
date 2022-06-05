package work1;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionFabric {

    /**
     * Сгенерировать коллекцию целых чисел указанного размера (int size) и
     * в пределах указанного диапазона (int from;int to).
     *
     * Бросает IllegalArgumentException в случае неправильно указанного диапазона, например
     * если from>to
     */

    public static Collection<Integer> getRandomIntegerCollection(int size,int from,int to)
            throws IllegalArgumentException{
        Collection<Integer> items = new ArrayList<>();
        if (from>to) throw new  IllegalArgumentException("Указан неправильный диапазон");
        for (int i = 0; i < size; i++) {
            items.add((int)(Math.random()*(to-from))+from);
        }
        return items;
    }

}
