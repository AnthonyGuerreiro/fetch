package fetch.profile;

import java.util.Optional;

public interface ProfileNode {

    /**
     * Sets the {@code parent} of this node
     * 
     * @param parent
     */
    void setParent(ProfileNode parent);

    /**
     * Returns the parent of this node
     * 
     * @return the parent of this node
     */
    ProfileNode getParent();

    /**
     * Sets the {@code value} of this node
     * 
     * @param value
     */
    void setValue(String value);

    /**
     * Appends the {@code profile} to this node and returns it
     *
     * @param profile
     * @return Optional of {@code profile} if {@code profile} was appended.
     */
    Optional<ProfileNode> append(Profile profile);

    /**
     * Appends the {@code show} to this node and returns it
     *
     * @param show
     * @return Optional of {@code show} if {@code show} was appended.
     */
    Optional<ProfileNode> append(Show show);

}
