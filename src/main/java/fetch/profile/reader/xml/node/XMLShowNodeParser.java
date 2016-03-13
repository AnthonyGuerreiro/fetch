package fetch.profile.reader.xml.node;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.profile.ProfileNode;
import fetch.profile.Show;

public class XMLShowNodeParser implements XMLNodeParser {

    @Override
    public Optional<ProfileNode> getProfileNode(Attributes attributes,
            Optional<ProfileNode> previous) {

        if (!previous.isPresent()) {
            return Optional.empty();
        }

        Show show = new Show();

        ProfileNode node = previous.get();
        node.appendShow(show);
        return Optional.of(show);
    }

}
