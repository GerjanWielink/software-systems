package ss.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort {
    public static <E extends Comparable<E>>
           void mergesort(List<E> list) {
        if(list.size() < 2) {
            return;
        }

        // split the array into singletons
    	List<List<E>> lists = new ArrayList<>();
    	for(E element:list) {
            lists.add(new ArrayList<>(Collections.singletonList(element)));
        }

        // perform the merge
        List<E> sortedList = MergeSort.doMerge(lists);

        // overwrite the original array, not sure if this is the only/best way to do this
        list.clear();
        list.addAll(sortedList);
    }

    private static <E extends Comparable<E>>
            List<E> doMerge(List<List<E>> lists) {
        List<List<E>> nextLists = new ArrayList<>();

        for (int i = 0; i < lists.size(); i += 2) {
            // prevent out of bounds for uneven lists
            if(i + 1 >= lists.size()) {
                nextLists.add(lists.get(i));
                continue;
            }

            List<E> firstList = lists.get(i);
            List<E> secondList = lists.get(i + 1);

            // account for empty lists
            if (firstList.size() == 0 || secondList.size() == 0) {
                firstList.addAll(secondList);
                nextLists.add(firstList);
                continue;
            }

            nextLists.add(doSingleMerge(firstList, secondList));
        }

        return nextLists.size() > 1 ? doMerge(nextLists) : nextLists.get(0);
    }

    private static <E extends Comparable<E>>
            List<E> doSingleMerge(List<E> firstList, List<E> secondList) {
        List<E> mergedList = new ArrayList<>();

        while(firstList.size() > 0 && secondList.size() > 0) {
            List<E> currentList = firstList.get(0).compareTo(secondList.get(0)) < 0 ? firstList : secondList;
            mergedList.add(currentList.get(0));
            currentList.remove(0);
        }

        // when one of the lists is empty the other can fully appended
        // instead of checking which we just add both as one is empty anyways..
        mergedList.addAll(firstList);
        mergedList.addAll(secondList);

        return mergedList;
    }
}
