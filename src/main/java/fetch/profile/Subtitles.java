package fetch.profile;

public class Subtitles extends AbstractNode {
    private String value;

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
