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
     * Appends the {@code profile} node to this node and returns it
     *
     * @param profile
     * @return Optional of {@code profile} if {@code profile} was appended.
     */
    Optional<Profile> append(Profile profile);

    /**
     * Appends the {@code show} node to this node and returns it
     *
     * @param show
     * @return Optional of {@code show} if {@code show} was appended.
     */
    Optional<Show> append(Show show);

    /**
     * Appends the {@code begin} node to this node and returns it
     *
     * @param show
     * @return Optional of {@code begin} if {@code begin} was appended.
     */
    Optional<Begin> append(Begin begin);

    /**
     * Appends the {@code node} to this node and returns it
     *
     * @param show
     * @return Optional of {@code node} if {@code node} was appended.
     */
    Optional<ProfileNode> append(ProfileNode node);
}
