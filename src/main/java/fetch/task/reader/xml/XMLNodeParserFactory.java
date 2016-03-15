package fetch.task.reader.xml;

import fetch.task.reader.node.parser.XMLGenericNodeParser;
import fetch.task.reader.node.parser.XMLNodeParser;
import fetch.task.reader.node.parser.XMLProfileNodeParser;
import fetch.task.reader.node.parser.XMLShowNodeParser;

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