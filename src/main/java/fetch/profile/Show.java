package fetch.profile;

import java.util.Optional;

public class Show extends AbstractNode {

    private String name;
    private Begin begin = new Begin();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Optional<Begin> append(Begin begin) {
        this.begin = begin;
        return Optional.ofNullable(begin);
    }

    public Optional<String> getBegin() {
        return Optional.ofNullable(begin.getValue());
    }
}
