package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.profile.Profile;
import fetch.profile.ProfileNode;

public class XMLProfileNodeParser implements XMLNodeParser {

    @Override
    public Optional<ProfileNode> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        Profile profile = new Profile();
        profile.setName(attributes.getValue("name"));
        return Optional.of(profile);
    }

}
