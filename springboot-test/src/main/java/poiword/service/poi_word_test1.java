package poiword.service;

import java.io.FileOutputStream;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

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
				//setParagraphAlignInfo(p, ParagraphAlignment.LEFT, TextAlignment.CENTER);

				p.setIndentationLeft(200);
			} else if (i == 1) {
				p.setIndentationFirstLine(20);
				//setParagraphAlignInfo(p, ParagraphAlignment.LEFT, TextAlignment.CENTER);
			} else if (i == 2) {
				setParagraphAlignInfo(p, ParagraphAlignment.CENTER, TextAlignment.CENTER);
			} else if (i == 3) {
				setParagraphAlignInfo(p, ParagraphAlignment.CENTER, TextAlignment.CENTER);
			}
			XWPFRun pRun = getOrAddParagraphFirstRun(p, false, false);
			pRun.setText("333=" + i, 0);
		}
		
		addTable(xdoc, "测试用户1");
		
		addTable(xdoc, "测试用户2");
		
		saveDocument(xdoc, "g:/sys_.docx");
	}
	
	private void addTable(XWPFDocument xdoc, String userName){
		XWPFParagraph p = xdoc.createParagraph();
//		// 创建表格21行3列
		XWPFTable table = xdoc.createTable(5, 4);
		//setTableBorders(table, STBorder.SINGLE, "4", "auto", "0");
		setTableWidthAndHAlign(table, "9024", STJc.CENTER);
		setTableCellMargin(table, 0, 108, 0, 108);
		int[] colWidths = new int[] { 3000, 2638, 525, 3692 };
		setTableGridCol(table, colWidths);

		XWPFTableRow row = table.getRow(0);
		//setRowHeight(row, "460", STHeightRule.AT_LEAST);
		XWPFTableCell cell = row.getCell(0);
		setCellShdStyle(cell, true, "FFFFFF", null);
		p = getCellFirstParagraph(cell);
		XWPFRun pRun = getOrAddParagraphFirstRun(p, false, false);
		setParagraphRunFontInfo(p, pRun, "A.负责人或部门资料 ﹡", "宋体",
				"Times New Roman", "24", true, false, false, false, null, null,
				0, 6, 0);
		//mergeCellsHorizontal(table, 0, 0, 3);
		
		row = table.getRow(1);
		//setRowHeight(row, "567", STHeightRule.AT_LEAST);
		cell = row.getCell(0);
		setCellWidthAndVAlign(cell, "2169", STTblWidth.DXA, STVerticalJc.TOP);
		p = getCellFirstParagraph(cell);
		pRun = getOrAddParagraphFirstRun(p, false, false);
		setParagraphRunFontInfo(p, pRun, "1.负责人：", "宋体", "Times New Roman",
				"21", false, false, false, false, null, null, 0, 6, 0);
		cell = row.getCell(1);
		setCellWidthAndVAlign(cell, "2169", STTblWidth.DXA, STVerticalJc.TOP);
		p = getCellFirstParagraph(cell);
		pRun = getOrAddParagraphFirstRun(p, false, false);
		setParagraphRunFontInfo(p, pRun, userName, "宋体", "Times New Roman",
				"21", false, false, false, false, null, null, 0, 6, 0);

		cell = table.getRow(2).getCell(0);
		setCellWidthAndVAlign(cell, "3163", STTblWidth.DXA, STVerticalJc.TOP);
		p = getCellFirstParagraph(cell);
		pRun = getOrAddParagraphFirstRun(p, false, false);
		setParagraphRunFontInfo(p, pRun, "2.负责部门：", "宋体", "Times New Roman",
				"21", false, false, false, false, null, null, 0, 6, 0);
		cell = table.getRow(2).getCell(1);
		setCellWidthAndVAlign(cell, "3163", STTblWidth.DXA, STVerticalJc.TOP);
		p = getCellFirstParagraph(cell);
		pRun = getOrAddParagraphFirstRun(p, false, false);
		setParagraphRunFontInfo(p, pRun, "研发部", "宋体", "Times New Roman",
				"21", false, false, false, false, null, null, 0, 6, 0);
	}

	/**
	 * @Description: 设置表格总宽度与水平对齐方式
	 */
	public void setTableWidthAndHAlign(XWPFTable table, String width,
			STJc.Enum enumValue) {
		CTTblPr tblPr = getTableCTTblPr(table);
		CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr
				.addNewTblW();
		if (enumValue != null) {
			CTJc cTJc = tblPr.addNewJc();
			cTJc.setVal(enumValue);
		}
		tblWidth.setW(new BigInteger(width));
		tblWidth.setType(STTblWidth.DXA);
	}
	
	/**
	 * @Description: 得到Table的CTTblPr,不存在则新建
	 */
	public CTTblPr getTableCTTblPr(XWPFTable table) {
		CTTbl ttbl = table.getCTTbl();
		CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl
				.getTblPr();
		return tblPr;
	}
	
	/**
	 * @Description: 设置列宽和垂直对齐方式
	 */
	public void setCellWidthAndVAlign(XWPFTableCell cell, String width, STTblWidth.Enum typeEnum,
			STVerticalJc.Enum vAlign) {
		CTTcPr tcPr = getCellCTTcPr(cell);
		CTTblWidth tcw = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
		if (width != null) {
			tcw.setW(new BigInteger(width));
		}
		if (typeEnum != null) {
			tcw.setType(typeEnum);
		}
		if (vAlign != null) {
			CTVerticalJc vJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr.addNewVAlign();
			vJc.setVal(vAlign);
		}
	}
	
	/**
	 * @Description: 设置单元格MarginsetTableCellMargin
	 */
	public void setTableCellMargin(XWPFTable table, int top, int left,
			int bottom, int right) {
		table.setCellMargins(top, left, bottom, right);
	}
	
	/**
	 * @Description: 设置表格列宽
	 */
	public void setTableGridCol(XWPFTable table, int[] colWidths) {
		CTTbl ttbl = table.getCTTbl();
		CTTblGrid tblGrid = ttbl.getTblGrid() != null ? ttbl.getTblGrid() : ttbl.addNewTblGrid();
		for (int j = 0, len = colWidths.length; j < len; j++) {
			CTTblGridCol gridCol = tblGrid.addNewGridCol();
			gridCol.setW(new BigInteger(String.valueOf(colWidths[j])));
		}
	}
	
	/**
	 * @Description: 设置底纹
	 */
	public void setCellShdStyle(XWPFTableCell cell, boolean isShd,
			String shdColor, STShd.Enum shdStyle) {
		CTTcPr tcPr = getCellCTTcPr(cell);
		if (isShd) {
			// 设置底纹
			CTShd shd = tcPr.isSetShd() ? tcPr.getShd() : tcPr.addNewShd();
			if (shdStyle != null) {
				shd.setVal(shdStyle);
			}
			if (shdColor != null) {
				shd.setColor(shdColor);
				shd.setFill(shdColor);
			}
		}
	}
	
	/**
	 * 
	 * @Description: 得到Cell的CTTcPr,不存在则新建
	 */
	public CTTcPr getCellCTTcPr(XWPFTableCell cell) {
		CTTc cttc = cell.getCTTc();
		CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
		return tcPr;
	}
	
	/**
	 * @Description: 得到单元格第一个Paragraph
	 */
	public XWPFParagraph getCellFirstParagraph(XWPFTableCell cell) {
		XWPFParagraph p;
		if (cell.getParagraphs() != null && cell.getParagraphs().size() > 0) {
			p = cell.getParagraphs().get(0);
		} else {
			p = cell.addParagraph();
		}
		return p;
	}
	
	/**
	 * @Description: 得到XWPFRun的CTRPr
	 */
	public CTRPr getRunCTRPr(XWPFParagraph p, XWPFRun pRun) {
		CTRPr pRpr = null;
		if (pRun.getCTR() != null) {
			pRpr = pRun.getCTR().getRPr();
			if (pRpr == null) {
				pRpr = pRun.getCTR().addNewRPr();
			}
		} else {
			pRpr = p.getCTP().addNewR().addNewRPr();
		}
		return pRpr;
	}
	
	/**
	 * @Description: 设置段落文本样式(高亮与底纹显示效果不同)设置字符间距信息(CTSignedTwipsMeasure)
	 * @param verticalAlign
	 *            : SUPERSCRIPT上标 SUBSCRIPT下标
	 * @param position
	 *            :字符间距位置：>0提升 <0降低=磅值*2 如3磅=6
	 * @param spacingValue
	 *            :字符间距间距 >0加宽 <0紧缩 =磅值*20 如2磅=40
	 * @param indent
	 *            :字符间距缩进 <100 缩
	 */
	public void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun pRun,
			String content, String cnFontFamily, String enFontFamily,
			String fontSize, boolean isBlod, boolean isItalic,
			boolean isStrike, boolean isShd, String shdColor,
			STShd.Enum shdStyle, int position, int spacingValue, int indent) {
		CTRPr pRpr = getRunCTRPr(p, pRun);
		if (StringUtils.isNotBlank(content)) {
			// pRun.setText(content);
			if (content.contains("\n")) {// System.properties("line.separator")
				String[] lines = content.split("\n");
				pRun.setText(lines[0], 0); // set first line into XWPFRun
				for (int i = 1; i < lines.length; i++) {
					// add break and insert new text
					pRun.addBreak();
					pRun.setText(lines[i]);
				}
			} else {
				pRun.setText(content, 0);
			}
		}
		// 设置字体
		CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr
				.addNewRFonts();
		if (StringUtils.isNotBlank(enFontFamily)) {
			fonts.setAscii(enFontFamily);
			fonts.setHAnsi(enFontFamily);
		}
		if (StringUtils.isNotBlank(cnFontFamily)) {
			fonts.setEastAsia(cnFontFamily);
//			fonts.setHint(STHint.EAST_ASIA);
		}
		// 设置字体大小
		CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
		sz.setVal(new BigInteger(fontSize));

		CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr
				.addNewSzCs();
		szCs.setVal(new BigInteger(fontSize));

		// 设置字体样式
		// 加粗
		if (isBlod) {
			pRun.setBold(isBlod);
		}
		// 倾斜
		if (isItalic) {
			pRun.setItalic(isItalic);
		}
		// 删除线
		if (isStrike) {
			pRun.setStrike(isStrike);
		}
		if (isShd) {
			// 设置底纹
			CTShd shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
			if (shdStyle != null) {
				shd.setVal(shdStyle);
			}
			if (shdColor != null) {
				shd.setColor(shdColor);
				shd.setFill(shdColor);
			}
		}

		// 设置文本位置
		if (position != 0) {
			pRun.setTextPosition(position);
		}
//		if (spacingValue > 0) {
//			// 设置字符间距信息
//			CTSignedTwipsMeasure ctSTwipsMeasure = pRpr.isSetSpacing() ? pRpr
//					.getSpacing() : pRpr.addNewSpacing();
//			ctSTwipsMeasure
//					.setVal(new BigInteger(String.valueOf(spacingValue)));
//		}
//		if (indent > 0) {
//			CTTextScale paramCTTextScale = pRpr.isSetW() ? pRpr.getW() : pRpr
//					.addNewW();
//			paramCTTextScale.setVal(indent);
//		}
	}
	
}
