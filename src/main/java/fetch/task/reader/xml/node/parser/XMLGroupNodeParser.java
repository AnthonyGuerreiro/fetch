package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.profile.Group;
import fetch.profile.ProfileNode;

public class XMLGroupNodeParser implements XMLNodeParser<Group> {

    private final static Logger logger = LogManager.getLogger(XMLGroupNodeParser.class);

    @Override
    public Optional<Group> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        if (!previous.isPresent()) {
            ConfigurationMap map = Configuration.getInstance().getMap();
            logger.warn("rd.node.no.parent", map.getProfilesFile());
            return Optional.empty();
        }

        Group group = new Group();

        ProfileNode node = previous.get();
        node.append(group);
        group.setParent(node);
        return Optional.of(group);
    }

}
