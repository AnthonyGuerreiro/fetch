package fetch.profile;

import java.util.Optional;

public interface ProfileNode {

    void setParent(ProfileNode parent);

    ProfileNode getParent();

    Optional<ProfileNode> append(Profile profile);

    Optional<ProfileNode> append(Show show);

    void setValue(String value);
}
