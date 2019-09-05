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
        // Excel Embed
        discovery.put("EMBED_VIEW_XLS", "x/_layouts/xlembed.aspx");
        discovery.put("EMBED_VIEW_XLSX", "x/_layouts/xlembed.aspx");
        discovery.put("EMBED_VIEW_XLSB", "x/_layouts/xlembed.aspx");
        discovery.put("EMBED_VIEW_XLM", "x/_layouts/xlembed.aspx");


        // Excel View
        discovery.put("VIEW_ODS", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("VIEW_XLS", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("VIEW_XLSB", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("VIEW_XLSM", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("VIEW_XLSX", "x/_layouts/xlviewerinternal.aspx");

        // Excel MobileView
        discovery.put("MOBILE_VIEW_ODS", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("MOBILE_VIEW_XLS", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("MOBILE_VIEW_XLSB", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("MOBILE_VIEW_XLSM", "x/_layouts/xlviewerinternal.aspx");
        discovery.put("MOBILE_VIEW_XLSX", "x/_layouts/xlviewerinternal.aspx");
    }

    public static String parse(ActionEnum actionEnum, String fileExtension) {
        String key = MessageFormat.format("{0}_{1}", actionEnum.name(), fileExtension.toUpperCase());
        return discovery.get(key);
    }
}
