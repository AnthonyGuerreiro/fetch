package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.profile.ProfileNode;
import fetch.profile.Show;

public class XMLShowNodeParser implements XMLNodeParser {

    private final static Logger logger = LogManager.getLogger(XMLShowNodeParser.class);

    @Override
    public Optional<ProfileNode> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        if (!previous.isPresent()) {
            ConfigurationMap map = Configuration.getInstance().getMap();
            logger.warn("rd.node.no.parent", map.getProfilesFile());
            return Optional.empty();
        }

        Show show = new Show();

        ProfileNode node = previous.get();
        node.append(show);
        show.setParent(node);
        return Optional.of(show);
    }

}
