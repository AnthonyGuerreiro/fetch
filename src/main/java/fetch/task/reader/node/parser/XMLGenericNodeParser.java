package fetch.task.reader.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.profile.ProfileNode;

public class XMLGenericNodeParser implements XMLNodeParser {

    @Override
    public Optional<ProfileNode> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        return Optional.empty();
    }

}
