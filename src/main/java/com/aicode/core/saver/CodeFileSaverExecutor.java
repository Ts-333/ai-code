package com.aicode.core.saver;

import com.aicode.ai.model.HtmlCodeResult;
import com.aicode.ai.model.MultiFileCodeResult;
import com.aicode.exception.BusinessException;
import com.aicode.exception.ErrorCode;
import com.aicode.model.enums.CodeGenTypeEnum;

import java.io.File;

/**
 * 代码文件保存器执行器
 *
 */
public class CodeFileSaverExecutor {

    private static final HtmlCodeFileSaverTemplate htmlCodeFileSaver = new HtmlCodeFileSaverTemplate();

    private static final MultiFileCodeFileSaverTemplate multiFileCodeFileSaver = new MultiFileCodeFileSaverTemplate();

    /**
     * 执行代码保存
     * @param codeType  代码生成类型
     * @param result    代码结果对象
     * @param appId     应用ID
     * @return          保存的目录
     */
   public static File executeSaver( Object result,CodeGenTypeEnum codeType, Long appId) {
        switch (codeType) {
            case HTML:
                return htmlCodeFileSaver.saveCode((HtmlCodeResult) result, appId);
            case MULTI_FILE:
                return multiFileCodeFileSaver.saveCode((MultiFileCodeResult) result, appId);
            default:
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"不支持的代码生成类型: " + codeType);
        }
    }
}
