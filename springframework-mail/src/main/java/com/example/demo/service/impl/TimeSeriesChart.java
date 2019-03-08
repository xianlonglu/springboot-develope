package com.example.demo.service.impl;

import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {

	public static void main(String[] args) {
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
				"Legal & General单位信托基金价格", "日期", "价格", xydataset, true, true,
				true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("dd HH"));
		ChartPanel frame1 = new ChartPanel(jfreechart, true);
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		try {
			File file = new File("d:/lineChart.png");
			ChartUtilities.saveChartAsPNG(file, jfreechart, 400, 300);// 把报表保存为文件
		} catch (Exception e) {
			String s = e.getLocalizedMessage();
			s = e.getMessage();
			s = e.toString();
		}
		ChartFrame mChartFrame = new ChartFrame("折线图", jfreechart);
		mChartFrame.pack();
		mChartFrame.setVisible(true);
	}

	private static XYDataset createDataset() { // 这个数据集有点多，但都不难理解
		TimeSeries timeseries = new TimeSeries("legal & general欧洲指数信任", Minute.class);
		timeseries.add(new Minute(15, 00, 27, 02, 2019), 181.80000000000001D);
		timeseries.add(new Minute(30, 00, 27, 02, 2019), 167.30000000000001D);
		timeseries.add(new Minute(45, 00, 27, 02, 2019), 153.80000000000001D);
		timeseries.add(new Minute(00, 01, 27, 02, 2019), 167.59999999999999D);
		timeseries.add(new Minute(15, 01, 27, 02, 2019), 158.80000000000001D);
		timeseries.add(new Minute(30, 01, 27, 02, 2019), 148.30000000000001D);
		timeseries.add(new Minute(45, 01, 27, 02, 2019), 153.90000000000001D);
		timeseries.add(new Minute(00, 02, 27, 02, 2019), 142.69999999999999D);
		timeseries.add(new Minute(15, 02, 27, 02, 2019), 123.2D);
		timeseries.add(new Minute(30, 02, 27, 02, 2019), 131.80000000000001D);
		timeseries.add(new Minute(45, 02, 27, 02, 2019), 139.59999999999999D);
		timeseries.add(new Minute(00, 03, 27, 02, 2019), 142.90000000000001D);
		timeseries.add(new Minute(15, 03, 27, 02, 2019), 138.69999999999999D);
		timeseries.add(new Minute(30, 03, 27, 02, 2019), 137.30000000000001D);
		timeseries.add(new Minute(45, 03, 27, 02, 2019), 143.90000000000001D);
		timeseries.add(new Minute(00, 04, 27, 02, 2019), 139.80000000000001D);
		timeseries.add(new Minute(15, 04, 27, 02, 2019), 137D);
		timeseries.add(new Minute(30, 04, 27, 02, 2019), 132.80000000000001D);
		timeseries.add(new Minute(45, 04, 27, 02, 2019), 132.80000000000001D);
		
		TimeSeries timeseries1 = new TimeSeries("legal & general英国指数信任", Minute.class);
		timeseries1.add(new Minute(15, 00, 27, 02, 2019),  129.59999999999999D);
		timeseries1.add(new Minute(30, 00, 27, 02, 2019),  123.2D);
		timeseries1.add(new Minute(45, 00, 27, 02, 2019),  117.2D);
		timeseries1.add(new Minute(00, 01, 27, 02, 2019),  124.09999999999999D);
		timeseries1.add(new Minute(15, 01, 27, 02, 2019),  122.59999999999999D);
		timeseries1.add(new Minute(30, 01, 27, 02, 2019),  119.2D);
		timeseries1.add(new Minute(45, 01, 27, 02, 2019),  116.5D);
		timeseries1.add(new Minute(00, 02, 27, 02, 2019),  112.7D);
		timeseries1.add(new Minute(15, 02, 27, 02, 2019),  101.5D);
		timeseries1.add(new Minute(30, 02, 27, 02, 2019),  106.09999999999999D);
		timeseries1.add(new Minute(45, 02, 27, 02, 2019),  110.3D);
		timeseries1.add(new Minute(00, 03, 27, 02, 2019),  111.7D);
		timeseries1.add(new Minute(15, 03, 27, 02, 2019),  111D);
		timeseries1.add(new Minute(30, 03, 27, 02, 2019),  109.59999999999999D);
		timeseries1.add(new Minute(45, 03, 27, 02, 2019),  113.2D);
		timeseries1.add(new Minute(00, 04, 27, 02, 2019),  111.59999999999999D);
		timeseries1.add(new Minute(15, 04, 27, 02, 2019),  108.8D);
		timeseries1.add(new Minute(30, 04, 27, 02, 2019),  101.59999999999999D);
		timeseries1.add(new Minute(45, 04, 27, 02, 2019),  101.59999999999999D);
                                  
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}
}