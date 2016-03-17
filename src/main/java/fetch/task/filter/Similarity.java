package fetch.task.filter;

@FunctionalInterface
public interface Similarity {
    float compare(String s1, String s2);
}
