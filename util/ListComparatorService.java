package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListComparatorService<T extends ListComparator> {
    
    /**
     * Given two lists of the same objects implementing
     * <code>ListComparator</code> (one containing modified items and the other
     * the original), this method will iterate through them and break them into
     * 3 parts. The items are placed into a Map with the
     * <code>ListComparatorKey</code> as key. <br>
     * All items that match, are placed into the returned map with the EXISTING
     * key. All the items from the modifiedList that is not present in the
     * originalList, are placed into the returned map with the NEW key. All the
     * items from the originalList that is no longer present in the
     * modifiedList, are placed into the returned map with the REMOVED key.
     * 
     * @param originalList
     * @param modifiedList
     * @return
     */
    public Map<ListComparatorKey, List<T>> compare(List<T> originalList, List<T> modifiedList) {
        List<T> existingItems = new ArrayList<T>();
        List<T> newItems = new ArrayList<T>();
        List<T> removedItems = new ArrayList<T>();
        
        boolean matchFound = false;
        for (ListComparator original : originalList) {
            matchFound = false;
            for (ListComparator modified : modifiedList) {
                if (original.compareTo(modified) == 0) {
                    matchFound = true;
                    existingItems.add((T) modified);
                    break;
                }
            }
            
            if (!matchFound) {
                // There is an existing item that doesnt appear in the modified list of items so delete it
                removedItems.add((T) original);
            }
        }
        
        for (ListComparator modified : modifiedList) {
            matchFound = false;
            for (ListComparator existing : existingItems) {
                if (existing.getIdentifier().equals(modified.getIdentifier())) {
                    matchFound = true;
                    break;
                }
            }
            
            if (!matchFound) {
                // There is a item that doesnt appear in the existing list of items so create it
                newItems.add((T) modified);
            }
        }
        
        Map<ListComparatorKey, List<T>> comparedItems = new HashMap<ListComparatorKey, List<T>>();
        comparedItems.put(ListComparatorKey.EXISTING, existingItems);
        comparedItems.put(ListComparatorKey.NEW, newItems);
        comparedItems.put(ListComparatorKey.REMOVED, removedItems);
        
        return comparedItems;
    }
}
