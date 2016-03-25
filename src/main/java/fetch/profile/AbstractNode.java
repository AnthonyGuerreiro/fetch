package fetch.profile;

import fetch.log.LogManager;
import fetch.log.Logger;

public abstract class AbstractNode implements ProfileNode {

    private final static Logger logger = LogManager.getLogger(AbstractNode.class);

    private ProfileNode parent;

    @Override
    public ProfileNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ProfileNode parent) {
        this.parent = parent;
    }

    @Override
    public void append(Profile profile) {
        log(Profile.class);
    }

    @Override
    public void append(Show show) {
        log(Show.class);
    }

    @Override
    public void append(Begin begin) {
        log(Begin.class);
    }

    @Override
    public void append(Subtitles subtitles) {
        log(Subtitles.class);
    }

    @Override
    public void append(Group group) {
        log(Group.class);
    }

    @Override
    public void append(ProfileNode node) {
        log(node.getClass());
    }

    @Override
    public void setValue(String value) {
        // nop
    }

    private void log(Class<?> klass) {
        logger.warn("pf.not.supported.child", klass, getClass());
    }
}
