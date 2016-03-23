package fetch.profile;

public class Begin extends AbstractNode {
    private String value;

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
