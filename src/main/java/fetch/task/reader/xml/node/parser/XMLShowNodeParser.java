package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.profile.ProfileNode;
import fetch.profile.Show;

public class XMLShowNodeParser implements XMLNodeParser<Show> {

    private final static Logger logger = LogManager.getLogger(XMLShowNodeParser.class);

    @Override
    public Optional<Show> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        if (!previous.isPresent()) {
            ConfigurationMap map = Configuration.getInstance().getMap();
            logger.warn("rd.node.no.parent", map.getProfilesFile());
            return Optional.empty();
        }

        Show show = new Show();
        show.setName(attributes.getValue("name"));

        ProfileNode node = previous.get();
        node.append(show);
        show.setParent(node);
        return Optional.of(show);
    }

}
