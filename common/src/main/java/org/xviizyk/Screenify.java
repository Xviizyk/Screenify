package org.xviizyk;

import org.xviizyk.css.Css;

public final class Screenify {
    public static final String MOD_ID = "screenify";

    private static final String tmp_base_path = "assets/screenify/css/base.css";

    public static void init() {
        Css.load(tmp_base_path);
    }
}
