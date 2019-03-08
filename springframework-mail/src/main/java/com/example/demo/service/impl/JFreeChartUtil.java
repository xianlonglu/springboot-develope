package com.example.demo.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class JFreeChartUtil {

	public static void main(String[] args) {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		/*ds.setValue(10, "ibm", "2018-05-21");
		ds.setValue(20, "ibm", "2018-05-22");
		ds.setValue(32, "ibm", "2018-05-23");
		ds.setValue(25, "ibm", "2018-05-24");
		ds.setValue(0, "ibm", "2018-05-25");
		ds.setValue(4, "ibm", "2018-05-26");
		ds.setValue(32, "ibm", "2018-05-27");
		ds.setValue(0, "ibm", "2018-05-28");
		ds.setValue(358, "ibm", "2018-05-29");
		ds.setValue(4, "ibm", "2018-05-30");*/
		Date date = new Date();
		for (int i = 0; i < 10; i++) {
			ds.setValue(i+1.5, "ibm", "2018-05-29 11:00" + i);
		}
		for (int i = 0; i < 10; i++) {
			ds.setValue(i+1.5, "ibm", "2018-05-29 11:01" + i);
		}
		for (int i = 0; i < 10; i++) {
			ds.setValue(i+1.5, "ibm", "2018-05-29 11:02" + i);
		}
		String filePath = "d:/lgg.jpg";
		createLineChart(ds, filePath);
	}

	public static void createLineChart(DefaultCategoryDataset ds,
			String filePath) {
		try {
			// 创建柱状图.标题,X坐标,Y坐标,数据集合,orientation,是否显示legend,是否显示tooltip,是否使用url链接
			JFreeChart chart = ChartFactory.createLineChart("近30天每日放屁次数趋势图",
					"", "次数", ds, PlotOrientation.VERTICAL, false, true, true);
			chart.setBackgroundPaint(Color.WHITE);
			Font font = new Font("宋体", Font.BOLD, 12);
			chart.getTitle().setFont(font);
			chart.setBackgroundPaint(Color.WHITE);
			// 配置字体（解决中文乱码的通用方法）
			Font xfont = new Font("仿宋", Font.BOLD, 12); // X轴
			Font yfont = new Font("宋体", Font.BOLD, 12); // Y轴
			Font titleFont = new Font("宋体", Font.BOLD, 12); // 图片标题
			CategoryPlot categoryPlot = chart.getCategoryPlot();
			categoryPlot.getDomainAxis().setLabelFont(xfont);
			categoryPlot.getDomainAxis().setLabelFont(xfont);
			categoryPlot.getRangeAxis().setLabelFont(yfont);
			chart.getTitle().setFont(titleFont);
			categoryPlot.setBackgroundPaint(Color.WHITE);
			// x轴 // 分类轴网格是否可见
			categoryPlot.setDomainGridlinesVisible(true);
			// y轴 //数据轴网格是否可见
			categoryPlot.setRangeGridlinesVisible(true);
			// 设置网格竖线颜色
			categoryPlot.setDomainGridlinePaint(Color.LIGHT_GRAY);
			// 设置网格横线颜色
			categoryPlot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			// 没有数据时显示的文字说明
			categoryPlot.setNoDataMessage("没有数据显示");
			// 设置曲线图与xy轴的距离
			categoryPlot.setAxisOffset(new RectangleInsets(0d, 0d, 0d, 0d));
			// 设置面板字体
			Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
			// 取得Y轴
			NumberAxis rangeAxis = (NumberAxis) categoryPlot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setAutoRangeIncludesZero(true);
			rangeAxis.setUpperMargin(0.20);
			rangeAxis.setLabelAngle(Math.PI / 2.0);
			// 取得X轴
			CategoryAxis categoryAxis = (CategoryAxis) categoryPlot
					.getDomainAxis();
			// 设置X轴坐标上的文字
			categoryAxis.setTickLabelFont(labelFont);
			// 设置X轴的标题文字
			categoryAxis.setLabelFont(labelFont);
			// 横轴上的 Lable 45度倾斜
			categoryAxis
					.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			// 设置距离图片左端距离
			categoryAxis.setLowerMargin(0.0);
			// 设置距离图片右端距离
			categoryAxis.setUpperMargin(0.0);
			// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
			LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryPlot
					.getRenderer();
			// 是否显示折点
			lineandshaperenderer.setBaseShapesVisible(true);
			// 是否显示折线
			lineandshaperenderer.setBaseLinesVisible(true);
			// series 点（即数据点）间有连线可见 显示折点数据
			lineandshaperenderer
					.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			lineandshaperenderer.setBaseItemLabelsVisible(true);
			ChartUtilities
					.saveChartAsJPEG(new File(filePath), chart, 400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
