package com.shiningcity.controller;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

public class ExcelStyleImpl implements IExcelExportStyler {
    
    // 大标题样式
    private CellStyle headerStyle;
    // 列标题样式
    private CellStyle titleStyle;
    // 行样式
    private CellStyle linestyle;

    // 设置样式（宽度在实体类的注解中设置）
    public ExcelStyleImpl(Workbook workbook) {
        short fontHeight = 12;
        this.headerStyle = setStyle(workbook,"宋体",false,fontHeight,null);
        this.titleStyle = setStyle(workbook,"宋体",false,fontHeight,null);
        this.linestyle = setStyle(workbook,"宋体",false,fontHeight,null);
    }

    @Override
    public CellStyle getHeaderStyle(short headerColor) {
        return headerStyle;
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        return titleStyle;
    }

    @Override
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
        return linestyle;
    }

    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        return linestyle;
    }

    @Override
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
        return null;
    }

    private CellStyle setStyle(Workbook workbook,String fontName,Boolean bold,Short fontHeight,IndexedColors fillForegroundColor) {
        CellStyle style = workbook.createCellStyle();
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 上下居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置自动换行
        style.setWrapText(true);
        Font font = workbook.createFont();
        // 字体样式
        font.setFontName(fontName);
        // 是否加粗
        font.setBold(bold);
        // 字体大小
        font.setFontHeightInPoints(fontHeight);
        style.setFont(font);
        // 背景色
        if (null != fillForegroundColor) {
            style.setFillForegroundColor(fillForegroundColor.getIndex());
        }
        // 背景图案
        //style.setFillPattern(FillPatternType.NO_FILL);
        //
        style.setDataFormat((short) BuiltinFormats.getBuiltinFormat("TEXT"));
        return style;
    }

    
    
}
