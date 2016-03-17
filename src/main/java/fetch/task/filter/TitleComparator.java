package fetch.task.filter;

import java.util.Comparator;

import fetch.task.searcher.Entry;

public class TitleComparator implements Comparator<Entry> {

    private String showSearched;
    private Similarity similarity;

    public TitleComparator(String showSearched, Similarity similarity) {
        this.showSearched = showSearched;
        this.similarity = similarity;
    }

    @Override
    public int compare(Entry e1, Entry e2) {
        float similarity1 = similarity.compare(showSearched, e1.getTitle());
        float similarity2 = similarity.compare(showSearched, e2.getTitle());

        int s1 = (int) (similarity1 * 1000);
        int s2 = (int) (similarity2 * 1000);

        return s1 - s2;
    }

}
