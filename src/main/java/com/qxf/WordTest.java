package com.qxf;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName WordTest
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/29 20:37
 **/
public class WordTest {
    public static void main(String[] args) {
        XWPFDocument doc = new XWPFDocument();
        // 标题
        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("2020数学期末考试");
        titleRun.setBold(true);
        titleRun.setFontSize(35);
        titleRun.addBreak();
        // 填空题
        XWPFParagraph fill = doc.createParagraph();
        fill.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun fillRun = fill.createRun();
        fillRun.setText("填空题");
        fillRun.setBold(true);
        fillRun.setFontSize(25);
        fillRun.addBreak();
        for (int i=0;i<10;i++){
            XWPFParagraph question = doc.createParagraph();
            question.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun questionRun = question.createRun();
            questionRun.setText(i+1+"这是填空题的题目描述啊这是填空题的题目描述啊这是填空题的题目描述啊这是填空题的题目描述啊这是填空题的题目描述啊");
            questionRun.addBreak();
        }
        // 判断题
        fill = doc.createParagraph();
        fill.setAlignment(ParagraphAlignment.LEFT);
        fillRun = fill.createRun();
        fillRun.setText("判断题");
        fillRun.setBold(true);
        fillRun.setFontSize(25);
        fillRun.addBreak();
        for (int i=0;i<10;i++){
            XWPFParagraph question = doc.createParagraph();
            question.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun questionRun = question.createRun();
            questionRun.setText(i+1+"判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题判断题");
            questionRun.addBreak();
        }
        // 单选题
        // 多选题
        try {
            doc.write(new FileOutputStream(new File("D:\\testWord.docx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
