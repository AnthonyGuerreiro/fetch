package fetch.task.filter;

import static org.simmetrics.builders.StringMetricBuilder.with;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.CosineSimilarity;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizers;

import fetch.profile.Show;
import fetch.task.searcher.Entry;

/**
 * This filter is to be used after every other filter.<br />
 * It receives a list of duplicate entries that were selected for download and
 * chooses only one per episode based on the Cosine Similarity algorithm.
 *
 */
public class CosineSimilarityFilter implements Filter {

    @Override
    public void filter(Show show, List<Entry> entries) {

        Optional<Entry> max = filterMax(show, entries);

    }

    public Optional<Entry> filterMax(Show show, List<Entry> entries) {
        String searched = show.getName();

        TitleComparator comparator = new TitleComparator(searched,
                (s1, s2) -> getCosineSimilarity(s1, s2));

        Optional<Entry> max = entries.stream().max(comparator);
        return max;
    }

    @Override
    public int getOrder() {
        return 200;
    }

    private float getCosineSimilarity(String searched, String actual) {

        StringMetric metric = with(new CosineSimilarity<String>())
                .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
                .simplify(Simplifiers.replaceNonWord()).tokenize(Tokenizers.whitespace())
                .build();

        return metric.compare(searched, actual);
    }

}
