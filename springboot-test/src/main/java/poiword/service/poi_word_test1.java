package poiword.service;

import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class poi_word_test1 {
	public static void main(String[] args) throws Exception {
		poi_word_test1 t2 = new poi_word_test1();
		t2.createTable();
	}

	/**
	 * @Description: 保存文档
	 */
	public void saveDocument(XWPFDocument document, String savePath)
			throws Exception {
		FileOutputStream fos = new FileOutputStream(savePath);
		document.write(fos);
		fos.close();
	}

	/**
	 * @Description: 设置段落对齐
	 */
	public void setParagraphAlignInfo(XWPFParagraph p,
			ParagraphAlignment pAlign, TextAlignment valign) {
		if (pAlign != null) {
			p.setAlignment(pAlign);
		}
		if (valign != null) {
			p.setVerticalAlignment(valign);
		}
	}

	public XWPFRun getOrAddParagraphFirstRun(XWPFParagraph p, boolean isInsert,
			boolean isNewLine) {
		XWPFRun pRun = null;
		if (isInsert) {
			pRun = p.createRun();
		} else {
			if (p.getRuns() != null && p.getRuns().size() > 0) {
				pRun = p.getRuns().get(0);
			} else {
				pRun = p.createRun();
			}
		}
		if (isNewLine) {
			pRun.addBreak();
		}
		return pRun;
	}

	public void createTable() throws Exception {
		XWPFDocument xdoc = new XWPFDocument();
		for (int i = 0; i < 4; i++) {
			XWPFParagraph p = xdoc.createParagraph();
			xdoc.setParagraph(p, i);
			// 居中
			if (i == 0) {
				setParagraphAlignInfo(p, ParagraphAlignment.LEFT, TextAlignment.CENTER);
			} else if (i == 1) {
				setParagraphAlignInfo(p, ParagraphAlignment.LEFT, TextAlignment.CENTER);
			} else if (i == 2) {
				setParagraphAlignInfo(p, ParagraphAlignment.CENTER, TextAlignment.CENTER);
			} else if (i == 3) {
				setParagraphAlignInfo(p, ParagraphAlignment.CENTER, TextAlignment.CENTER);
			}
			XWPFRun pRun = getOrAddParagraphFirstRun(p, false, false);
			pRun.setText("333=" + i, 0);
		}
		
		saveDocument(xdoc, "g:/sys_.docx");
	}
}
