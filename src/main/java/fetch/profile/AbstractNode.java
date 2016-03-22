package fetch.profile;

import java.util.Optional;

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
    public Optional<ProfileNode> append(Profile profile) {

        log(Profile.class);
        return Optional.empty();
    }

    @Override
    public Optional<ProfileNode> append(Show show) {

        log(Show.class);
        return Optional.empty();
    }

    @Override
    public void setValue(String value) {
        // nop
    }

    private void log(Class<?> klass) {
        logger.warn("pf.not.supported.child", klass, getClass());
    }
}
