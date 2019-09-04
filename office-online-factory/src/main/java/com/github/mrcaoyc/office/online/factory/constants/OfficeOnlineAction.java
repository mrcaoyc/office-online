package com.github.mrcaoyc.office.online.factory.constants;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * office 操作
 *
 * @author CaoYongCheng
 */
public class OfficeOnlineAction {

    private static Map<String, String> discovery = new HashMap<>();

    static {
        // excel embed_view
        discovery.put("EMBED_VIEW_XLSX", "x/_layouts/xlembed.aspx");
        discovery.put("EMBED_VIEW_XLS", "x/_layouts/xlembed.aspx");
    }

    public static String parse(ActionEnum actionEnum, String fileExtension) {
        String key = MessageFormat.format("{0}_{1}", actionEnum.name(), fileExtension.toUpperCase());
        return discovery.get(key);
    }
}
