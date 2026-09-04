package org.xviizyk.css;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.StyleSheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class Css {

    private static final String BASE_STYLESHEET_PATH = "/assets/screenify/css/base.css";
    private static final URL BASE_URL = createBaseUrl();
    private static final Logger log = LoggerFactory.getLogger("Screenify");

    private static StyleSheet base;

    private Css() {
    }

    public static void load(String path) {
        if (path == "") {
            log.warn("[Screenify] [w] CSS path is empty");
            return;
        }

        log.info("[Screenify] [i] Trying to parse CSS...");
        loadStyleSheet(path);
        log.info("[Screenify] [i] CSS successfully parsed by path: " + path);
    }

    private static StyleSheet loadStyleSheet(String resourcePath) {
        try (InputStream stream = Css.class.getResourceAsStream(resourcePath)) {
            if (stream == null) {
                log.error("[Screenify] [e] CSS resource not found: " + resourcePath);
                return null;
            }
            try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                return CSSFactory.parseString(readAll(reader), BASE_URL);
            }
        } catch (IOException | CSSException | RuntimeException e) {
            log.error("[Screenify] [e] Failed to parse CSS resource: " + resourcePath);
            e.printStackTrace();
            return null;
        }
    }

    private static String readAll(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int read;

        while ((read = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, read);
        }

        return builder.toString();
    }

    private static URL createBaseUrl() {
        try {
            return new URL("file:///screenify/");
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
