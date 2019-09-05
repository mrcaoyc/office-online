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
        discovery.put("EMBED_VIEW_ODS", "x/_layouts/xlembed.aspx");
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


        // PPT Embed
        discovery.put("EMBED_VIEW_ODP", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_POT", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_POTM", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_POTX", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_PPS", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_PPSM", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_PPSX", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_PPT", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_PPTM", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");
        discovery.put("EMBED_VIEW_PPTX", "p/PowerPointFrame.aspx?PowerPointView=ChromelessView&Embed=1");

        // PPT View
        discovery.put("VIEW_ODP", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_POT", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_POTM", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_POTX", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_PPS", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_PPSM", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_PPSX", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_PPT", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_PPTM", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("VIEW_PPTX", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");

        // PPT MobileView
        discovery.put("MOBILE_VIEW_ODP", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_POT", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_POTM", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_POTX", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_PPS", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_PPSM", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_PPSX", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_PPT", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_PPTM", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");
        discovery.put("MOBILE_VIEW_PPTX", "p/PowerPointFrame.aspx?PowerPointView=ReadingView");

        // Word Embed View
        discovery.put("EMBED_VIEW_DOC", "wv/wordviewerframe.aspx?embed=1");
        discovery.put("EMBED_VIEW_DOCM", "wv/wordviewerframe.aspx?embed=1");
        discovery.put("EMBED_VIEW_DOCX", "wv/wordviewerframe.aspx?embed=1");
        discovery.put("EMBED_VIEW_DOT", "wv/wordviewerframe.aspx?embed=1");
        discovery.put("EMBED_VIEW_DOTM", "wv/wordviewerframe.aspx?embed=1");
        discovery.put("EMBED_VIEW_DOTX", "wv/wordviewerframe.aspx?embed=1");
        discovery.put("EMBED_VIEW_ODT", "wv/wordviewerframe.aspx?embed=1");


        // Word  View
        discovery.put("VIEW_DOC", "wv/wordviewerframe.aspx");
        discovery.put("VIEW_DOCM", "wv/wordviewerframe.aspx");
        discovery.put("VIEW_DOCX", "wv/wordviewerframe.aspx");
        discovery.put("VIEW_DOT", "wv/wordviewerframe.aspx");
        discovery.put("VIEW_DOTM", "wv/wordviewerframe.aspx");
        discovery.put("VIEW_DOTX", "wv/wordviewerframe.aspx");
        discovery.put("VIEW_ODT", "wv/wordviewerframe.aspx");

        // Word  MobileView
        discovery.put("MOBILE_VIEW_DOC", "wv/wordviewerframe.aspx");
        discovery.put("MOBILE_VIEW_DOCM", "wv/wordviewerframe.aspx");
        discovery.put("MOBILE_VIEW_DOCX", "wv/wordviewerframe.aspx");
        discovery.put("MOBILE_VIEW_DOT", "wv/wordviewerframe.aspx");
        discovery.put("MOBILE_VIEW_DOTM", "wv/wordviewerframe.aspx");
        discovery.put("MOBILE_VIEW_DOTX", "wv/wordviewerframe.aspx");
        discovery.put("MOBILE_VIEW_ODT", "wv/wordviewerframe.aspx");

        // PDF Embed View
        discovery.put("EMBED_VIEW_PDF", "wv/wordviewerframe.aspx?embed=1&PdfMode=1");

        // PDF View
        discovery.put("VIEW_PDF", "wv/wordviewerframe.aspx?PdfMode=1");

        // PDF MobileView
        discovery.put("MOBILE_VIEW_PDF", "wv/wordviewerframe.aspx?PdfMode=1");
    }

    public static String parse(ActionEnum actionEnum, String fileExtension) {
        String key = MessageFormat.format("{0}_{1}", actionEnum.name(), fileExtension.toUpperCase());
        return discovery.get(key);
    }
}
