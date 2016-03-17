package fetch.profile;

import java.util.Optional;

import fetch.log.LogManager;
import fetch.log.Logger;

public abstract class AbstractNode implements ProfileNode {

    private final static String template = "Unsupported operation XML entry %s inside %s";

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

        logError(Profile.class);
        return Optional.empty();
    }

    @Override
    public Optional<ProfileNode> append(Show show) {

        logError(Show.class);
        return Optional.empty();
    }

    @Override
    public void setValue(String value) {
        // nop
    }

    private void logError(Class<?> klass) {
        String log = String.format(template, klass.getSimpleName(),
                getClass().getSimpleName());
        logger.info(log);
    }
}
