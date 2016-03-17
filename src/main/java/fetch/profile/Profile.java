package fetch.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Profile extends AbstractNode {
    private String name;
    private List<Show> shows = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    @Override
    public Optional<ProfileNode> append(Show show) {
        shows.add(show);
        return Optional.of(show);
    }

}
