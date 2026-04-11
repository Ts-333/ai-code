package com.aicodemother.core.parser;

import com.aicodemother.ai.model.HtmlCodeResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * HTML代码解析器
 */
public class HtmlCodeParser implements CodeParser<HtmlCodeResult>{

    private static final Pattern HTML_CODE_PATTERN = Pattern.compile("```html\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);

    @Override
    public HtmlCodeResult parseCode(String codeContent) {
        HtmlCodeResult result = new HtmlCodeResult();
        // 解析HTML代码
        String htmlCode = extractHtmlCode(codeContent);
        if (htmlCode != null && !htmlCode.trim().isEmpty()) {
            result.setHtmlCode(htmlCode.trim());
        } else {
            // 如果没有找到代码块，将整个内容作为HTML
            result.setHtmlCode(codeContent.trim());
        }
        return result;
    }

    /**
     * 从代码内容中提取HTML代码
     *
     * @param codeContent 代码内容
     * @return HTML代码
     */
    private String extractHtmlCode(String codeContent) {
        // 使用正则表达式匹配HTML代码
        Matcher matcher = HTML_CODE_PATTERN.matcher(codeContent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
