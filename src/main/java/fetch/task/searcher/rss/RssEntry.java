package fetch.task.searcher.rss;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.rometools.rome.feed.synd.SyndEntry;

import fetch.task.searcher.Entry;

public class RssEntry implements Entry {

    private String title;
    private String author;
    private String link;
    private LocalDateTime localTime;

    public RssEntry(SyndEntry entry) {
        title = entry.getTitle();
        author = entry.getAuthor();
        link = entry.getLink();
        Instant instant = entry.getPublishedDate().toInstant();
        localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Optional<String> getAuthor() {
        return Optional.ofNullable(author);
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public LocalDateTime getPublishedDate() {
        return localTime;
    }

}
