package fetch.task.reader.node.parser;

import java.util.Optional;

import org.xml.sax.Attributes;

import fetch.task.reader.node.ProfileNode;
import fetch.task.reader.node.Show;

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
