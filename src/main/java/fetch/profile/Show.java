package fetch.profile;

import java.util.Optional;

public class Show extends AbstractNode {

    private String name;
    private Begin begin = new Begin();
    private Subtitles subtitles = new Subtitles();
    private Group group = new Group();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void append(Begin begin) {
        this.begin = begin;
    }

    @Override
    public void append(Subtitles subtitles) {
        this.subtitles = subtitles;
    }

    @Override
    public void append(Group group) {
        this.group = group;
    }

    public Optional<String> getBegin() {
        return Optional.ofNullable(begin.getValue());
    }

    public Optional<String> getSubtitles() {
        return Optional.ofNullable(subtitles.getValue());
    }

    public Optional<String> getGroup() {
        return Optional.ofNullable(group.getValue());
    }
}
