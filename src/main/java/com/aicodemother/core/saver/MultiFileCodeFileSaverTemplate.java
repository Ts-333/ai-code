package com.aicodemother.core.saver;

import cn.hutool.core.util.StrUtil;
import com.aicodemother.ai.model.MultiFileCodeResult;
import com.aicodemother.exception.BusinessException;
import com.aicodemother.exception.ErrorCode;
import com.aicodemother.model.enums.CodeGenTypeEnum;


/**
 * 多文件代码文件保存器
 *
 */
public class MultiFileCodeFileSaverTemplate extends CodeFileSaverTemplate<MultiFileCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.MULTI_FILE;
    }

    @Override
    protected void saveFiles(MultiFileCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
        writeToFile(baseDirPath, "index.css", result.getCssCode());
        writeToFile(baseDirPath, "index.js", result.getJsCode());
    }

    @Override
    protected void validateInput(MultiFileCodeResult result) {
        super.validateInput(result);
        // 至少要有HTML, CSS和JS可以为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }
    }


}
