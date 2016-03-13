package fetch.profile.reader.xml;

import fetch.profile.reader.xml.node.XMLGenericNodeParser;
import fetch.profile.reader.xml.node.XMLNodeParser;
import fetch.profile.reader.xml.node.XMLProfileNodeParser;
import fetch.profile.reader.xml.node.XMLShowNodeParser;

public class XMLNodeParserFactory {

    public XMLNodeParser getXMLProfileNodeParser(String name) {
        name = name.toLowerCase();
        switch (name) {
            case "profile":
                return new XMLProfileNodeParser();
            case "show":
                return new XMLShowNodeParser();
            default:
                return new XMLGenericNodeParser();
        }
    }
}
