package com.shiningcity.csv;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;

public class CsvExportUtil {
 
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /**
     * CSV文件行分隔符
     */
    private static final String CSV_ROW_SEPARATOR = "\r\n";
 
    /**
     * 
     */
    public static void doExport(List<Map<String, Object>> dataList, String titles, String keys, OutputStream os) throws Exception {
        StringBuffer buf = new StringBuffer();
        String[] titleArr = titles.split(CSV_COLUMN_SEPARATOR);
        String[] keyArr = keys.split(CSV_COLUMN_SEPARATOR);
        // 组装表头
        for (int i = 0; i < keyArr.length; i++) {
            String title = titleArr[i];
            buf.append(title);
            if (i < keyArr.length - 1) {
                // 列分隔符
                buf.append(CSV_COLUMN_SEPARATOR);
            }
        }
        // 换行符
        buf.append(CSV_ROW_SEPARATOR);
        // 组装数据
        if (CollectionUtils.isNotEmpty(dataList)) { 
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> data = dataList.get(i);
                for (int j = 0; j < keyArr.length; j++) {
                    String key = keyArr[j];
                    buf.append(data.get(key));
                    if (j < keyArr.length - 1) {
                        // 列分隔符
                        buf.append(CSV_COLUMN_SEPARATOR);
                    }
                }
                if (i < dataList.size() - 1) {
                    // 换行符
                    buf.append(CSV_ROW_SEPARATOR);
                }
            }
        }
        // 写出响应
        //os.write(buf.toString().getBytes("GBK"));
        os.write(buf.toString().getBytes("utf-8"));
        os.flush();
    }
 
    /**
     * 设置Header
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName + sdf.format(new Date()) + ".csv";
        // 读取字符编码
        String utf = "UTF-8";
        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }
 
}