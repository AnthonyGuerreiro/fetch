package fetch.message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import fetch.log.LogManager;
import fetch.log.Logger;

public class MessagesCache {

    private final static Logger logger = LogManager.getLogger(MessagesCache.class);

    private Map<String, ResourceBundle> bundles = new HashMap<>();
    private Set<String> bundlesNotFound = new HashSet<>();

    private static MessagesCache cache = new MessagesCache();

    public static MessagesCache getInstance() {
        return cache;
    }

    public Optional<ResourceBundle> getBundle(String bundleName) {
        if (bundles.containsKey(bundleName)) {
            return Optional.of(bundles.get(bundleName));
        }

        if (bundlesNotFound.contains(bundleName)) {
            return Optional.empty();
        }

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
            bundles.put(bundleName, bundle);
            return Optional.of(bundle);
        } catch (Exception e) {
            bundlesNotFound.add(bundleName);
            String msg = "Could not find resource bundle " + bundleName;
            logger.warn(msg);
            return Optional.empty();
        }
    }

}
