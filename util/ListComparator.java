package util;

/**
 * This interface is used by the QuestionnaireComparatorService in order to
 * compare lists of questionnaire/question/answer objects.
 * 
 */
public interface ListComparator extends Comparable<ListComparator> {
    
    /**
     * This must be a unique identifier to match the same objects for
     * differences.
     */
    String getIdentifier();
    
}