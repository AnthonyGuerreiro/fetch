package fetch.task.reader.xml.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.profile.ProfileNode;

public interface XMLNodeParser<N extends ProfileNode> {

    Optional<N> getProfileNode(Attributes attributes, Optional<ProfileNode> previous);
}
