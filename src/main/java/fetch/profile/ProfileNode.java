package fetch.profile;

import java.util.Optional;

public interface ProfileNode {
    Optional<ProfileNode> appendProfile(Profile profile);

    Optional<ProfileNode> appendShow(Show show);

    void setValue(String value);
}
