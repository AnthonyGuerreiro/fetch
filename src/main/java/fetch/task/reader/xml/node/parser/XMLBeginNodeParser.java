package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.profile.Begin;
import fetch.profile.ProfileNode;

public class XMLBeginNodeParser implements XMLNodeParser<Begin> {

    private final static Logger logger = LogManager.getLogger(XMLBeginNodeParser.class);

    @Override
    public Optional<Begin> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        if (!previous.isPresent()) {
            ConfigurationMap map = Configuration.getInstance().getMap();
            logger.warn("rd.node.no.parent", map.getProfilesFile());
            return Optional.empty();
        }

        Begin begin = new Begin();

        ProfileNode node = previous.get();
        node.append(begin);
        begin.setParent(node);
        return Optional.of(begin);
    }

}
