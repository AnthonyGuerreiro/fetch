package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.profile.ProfileNode;
import fetch.profile.Subtitles;

public class XMLSubtitlesNodeParser implements XMLNodeParser<Subtitles> {

    private final static Logger logger = LogManager
            .getLogger(XMLSubtitlesNodeParser.class);

    @Override
    public Optional<Subtitles> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        if (!previous.isPresent()) {
            ConfigurationMap map = Configuration.getInstance().getMap();
            logger.warn("rd.node.no.parent", map.getProfilesFile());
            return Optional.empty();
        }

        Subtitles subtitles = new Subtitles();

        ProfileNode node = previous.get();
        node.append(subtitles);
        subtitles.setParent(node);
        return Optional.of(subtitles);
    }

}
