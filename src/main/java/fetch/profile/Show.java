package fetch.profile;

import java.util.Optional;

public class Show extends AbstractNode {

    private String name;
    private Begin begin = new Begin();
    private Subtitles subtitles = new Subtitles();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Optional<Begin> append(Begin begin) {
        this.begin = begin;
        return Optional.of(begin);
    }

    @Override
    public Optional<Subtitles> append(Subtitles subtitles) {
        this.subtitles = subtitles;
        return Optional.of(subtitles);
    }

    public Optional<String> getBegin() {
        return Optional.ofNullable(begin.getValue());
    }

    public Optional<String> getSubtitles() {
        return Optional.ofNullable(subtitles.getValue());
    }
}
