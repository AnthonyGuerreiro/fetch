package fetch.task.searcher;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Entry {
    String getTitle();

    Optional<String> getAuthor();

    String getLink();

    LocalDateTime getPublishedDate();

}
