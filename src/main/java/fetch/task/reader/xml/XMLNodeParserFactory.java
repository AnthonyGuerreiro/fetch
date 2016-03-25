package fetch.task.reader.xml;

import fetch.profile.ProfileNode;
import fetch.task.reader.xml.node.parser.XMLBeginNodeParser;
import fetch.task.reader.xml.node.parser.XMLGenericNodeParser;
import fetch.task.reader.xml.node.parser.XMLGroupNodeParser;
import fetch.task.reader.xml.node.parser.XMLNodeParser;
import fetch.task.reader.xml.node.parser.XMLProfileNodeParser;
import fetch.task.reader.xml.node.parser.XMLShowNodeParser;
import fetch.task.reader.xml.node.parser.XMLSubtitlesNodeParser;

public class XMLNodeParserFactory {

    public XMLNodeParser<? extends ProfileNode> getXMLProfileNodeParser(String name) {
        name = name.toLowerCase();
        switch (name) {
            case "profile":
                return new XMLProfileNodeParser();
            case "show":
                return new XMLShowNodeParser();
            case "begin":
                return new XMLBeginNodeParser();
            case "subtitles":
                return new XMLSubtitlesNodeParser();
            case "group":
                return new XMLGroupNodeParser();
            default:
                return new XMLGenericNodeParser();
        }
    }
}
