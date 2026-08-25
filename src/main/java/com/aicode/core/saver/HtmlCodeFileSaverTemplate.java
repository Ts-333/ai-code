package com.aicode.core.saver;


import cn.hutool.core.util.StrUtil;
import com.aicode.ai.model.HtmlCodeResult;
import com.aicode.exception.BusinessException;
import com.aicode.exception.ErrorCode;
import com.aicode.model.enums.CodeGenTypeEnum;

/**
 * HTML代码文件保存器模板方法
 *
 * @author 杰尼
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult>{

    // HTML代码文件保存器模板方法
    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    // 保存文件
    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }
    }

}
