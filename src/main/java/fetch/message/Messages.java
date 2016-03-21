package fetch.message;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public class Messages {

    private final static String DEFAULT_BUNDLE_NAME = "messages";

    private final String bundleName;

    public Messages() {
        this(DEFAULT_BUNDLE_NAME);
    }

    public Messages(String bundleName) {
        if (bundleName == null) {
            bundleName = DEFAULT_BUNDLE_NAME;
        }
        this.bundleName = bundleName;
    }

    public String get(String key) {
        return get(key, bundleName, new Object[0]);
    }

    public String get(String key, Object... args) {

        Optional<ResourceBundle> bundle = MessagesCache.getInstance()
                .getBundle(bundleName);

        if (!bundle.isPresent() || !bundle.get().containsKey(key)) {
            return key;
        }
        String value = bundle.get().getString(key);
        if (args == null || args.length == 0) {
            return value;
        }
        return MessageFormat.format(value, args);
    }

}
