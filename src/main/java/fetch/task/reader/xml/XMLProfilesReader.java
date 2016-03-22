package fetch.task.reader.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fetch.exception.ConfigurationException;
import fetch.message.Messages;
import fetch.profile.Profile;
import fetch.profile.ProfileNode;
import fetch.task.reader.ProfilesReader;
import fetch.task.reader.xml.node.parser.XMLNodeParser;

public class XMLProfilesReader extends DefaultHandler implements ProfilesReader {

    public final static String PROFILE_NAME_ATTR = "name";

    private List<Profile> profiles = new ArrayList<>();
    private Stack<ProfileNode> nodes = new Stack<>();

    private StringBuilder value;

    @Override
    public List<Profile> getProfiles(String filename) throws ConfigurationException {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(filename);
            parser.parse(is, this);
            return profiles;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            String msg = new Messages().get("rd.fail.parse", filename);
            throw new ConfigurationException(msg, e);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        Optional<ProfileNode> previous = nodes.isEmpty() ? Optional.empty()
                : Optional.of(nodes.peek());

        XMLNodeParserFactory factory = new XMLNodeParserFactory();
        XMLNodeParser nodeParser = factory.getXMLProfileNodeParser(qName);
        Optional<ProfileNode> node = nodeParser.getProfileNode(attributes, previous);
        if (node.isPresent()) {
            ProfileNode pNode = node.get();
            nodes.push(pNode);
            if (pNode instanceof Profile) {
                profiles.add((Profile) pNode);
            }
        }
        value = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (!nodes.isEmpty()) {
            ProfileNode node = nodes.pop();
            node.setValue(value.toString());
        }
    }
}
