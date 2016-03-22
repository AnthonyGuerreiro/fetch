package fetch.test.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import fetch.profile.Show;
import fetch.task.filter.CosineSimilarityFilter;
import fetch.task.filter.Filter;
import fetch.task.filter.ShowFilter;
import fetch.task.searcher.Entry;
import fetch.test.TestEntryImpl;

public class TestFilters {

    private String referenceName = "The quick brown fox jumps over the lazy dog";
    private Show referenceShow = new Show();

    @Before
    public void init() {
        referenceShow.setName(referenceName);
    }

    @Test
    public void testLoadFilters() {
        List<Filter> filters = new ShowFilter().getFilters();
        assertEquals(1, filters.size());
        assertTrue(filters.get(0) instanceof CosineSimilarityFilter);
    }

    @Test
    public void testCosineSimilarityFilter() {

        CosineSimilarityFilter filter = new CosineSimilarityFilter();
        Optional<Entry> max = filter.filterMax(referenceShow, getTestEntries());
        assertTrue("CosineSimilarityFilter should keep at least one show",
                max.isPresent());

        String expected = "fox dog The quick jumps red lazy over the ";
        Entry entry = getEntry(expected);

        String msg = "Expected <" + expected + "> but actual was <" + max.get().getTitle()
                + ">";
        assertEquals(msg, entry, max.get());
    }

    private Entry getEntry(String name) {
        return new TestEntryImpl(name);
    }

    private List<Entry> getTestEntries() {

        //@formatter:off
        String[] entryValues = new String[] {
                "fox dog The quick jumps red lazy over the ",
                "The quick ",
                "The quick red small dog jumps over the lazy fox",
                "The quick green small dog jumps over the lazy fox tomorrow",
                "The dog jumps over the lazy fox",
                "The deer rabbit dog jumps over the lazy fox" };
        //@formatter:on

        List<Entry> entries = new ArrayList<>();

        for (String entryValue : entryValues) {
            entries.add(getEntry(entryValue));
        }

        return entries;
    }

}
