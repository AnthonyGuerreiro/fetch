package fetch.test;

import java.time.LocalDateTime;
import java.util.Optional;

import fetch.task.searcher.Entry;

public class TestEntryImpl implements Entry {

    private String value;

    public TestEntryImpl(String value) {
        this.value = value;
    }

    @Override
    public String getTitle() {
        return value;
    }

    @Override
    public Optional<String> getAuthor() {
        return Optional.ofNullable(value);
    }

    @Override
    public String getLink() {
        return value;
    }

    @Override
    public LocalDateTime getPublishedDate() {
        return LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestEntryImpl)) {
            return false;
        }
        return value.equals(((TestEntryImpl) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
