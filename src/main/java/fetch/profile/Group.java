package fetch.profile;

public class Group extends AbstractNode {
    private String value;

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
