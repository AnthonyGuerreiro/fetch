package fetch.profile;

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
     */
    void append(Profile profile);

    /**
     * Appends the {@code show} node to this node and returns it
     *
     * @param show
     */
    void append(Show show);

    /**
     * Appends the {@code begin} node to this node and returns it
     *
     * @param begin
     */
    void append(Begin begin);

    /**
     * Appends the {@code subtitles} node to this node and returns it
     *
     * @param subtitles
     */
    void append(Subtitles subtitles);

    /**
     * Appends the {@code node} to this node and returns it
     *
     * @param node
     */
    void append(ProfileNode node);
}
