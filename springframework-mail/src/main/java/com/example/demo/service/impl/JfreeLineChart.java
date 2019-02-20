package com.example.demo.service.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;

public class JfreeLineChart {

	public static void main(String[] args) {
		try {
			//getLineImg();
			getScatterImg1();
			// 散点图例子：https://zhenping1991.iteye.com/blog/2006457
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static DefaultXYDataset getDefaultXYDataset() {
		DefaultXYDataset xydataset = new DefaultXYDataset();
        for (int i = 1; i < 3; i++) { 
    		double[][] data = new double[2][10];
    		data[0][0] = 0.0 + i;//x轴
    		data[1][0] = 0.0 + i;//y轴 
    		data[0][1] = 0.1 + i;data[1][1] = 0.1 + i;
    		data[0][2] = 0.2 + i;data[1][2] = 0.2 + i;
    		data[0][3] = 0.3 + i;data[1][3] = 0.3 + i;
    		data[0][4] = 0.4 + i;data[1][4] = 0.4 + i;
    		data[0][5] = 0.5 + i;data[1][5] = 0.5 + i;
    		data[0][6] = 0.6 + i;data[1][6] = 0.6 + i;
    		data[0][7] = 0.7 + i;data[1][7] = 0.7 + i;
    		data[0][8] = 0.8 + i;data[1][8] = 0.8 + i;
    		data[0][9] = 0.9 + i;data[1][9] = 0.9 + i;
            xydataset.addSeries("car position" + i, data); //为类别标签
		}
		/*double[] x = { 0, 1, 2 };
		double[] y = { 0, 1, 2 };
		double[][] data = { x, y };
		xydataset.addSeries("car position", data);
		*/
        List<Double> xList = new ArrayList<Double>();
        List<Double> yList = new ArrayList<Double>();
        for (int i = 0; i < 10; i++) {
			xList.add(4 + 0.1 * i);
			yList.add(4 + 0.1 * i);
		}
        int length = xList.size();
    	double[][] data = new double[2][length];
        for (int i = 0; i < length; i++) {
        	data[0][i] = xList.get(i);
        	data[1][i] = yList.get(i);
		}
        xydataset.addSeries("car position", data); 
		return xydataset;
	}
    
	public static String getScatterImg1() throws IOException {
    	DefaultXYDataset xydataset = getDefaultXYDataset(); 
        //创建主题样式  
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
        //设置标题字体  
        mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));  
        //设置轴向字体  
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));  
        //设置图例字体  
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));  
        //应用主题样式  
        ChartFactory.setChartTheme(mChartTheme);  
        
        JFreeChart chart = ChartFactory.createScatterPlot(
        		"", "X轴", "Y轴",// 标题、X轴、Y轴
                xydataset, 
                PlotOrientation.VERTICAL,
                true, 
                false,
                false);  
		chart.setBackgroundPaint(Color.white);
		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(1.5f));
		XYPlot xyplot = (XYPlot) chart.getPlot();

		xyplot.setBackgroundPaint(new Color(255, 253, 246));
		ValueAxis vaaxis = xyplot.getDomainAxis();
		vaaxis.setAxisLineStroke(new BasicStroke(1.5f));

		ValueAxis va = xyplot.getDomainAxis(0);
		va.setAxisLineStroke(new BasicStroke(1.5f));

		va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
		va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
		xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细
		va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
		va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色
		ValueAxis axis = xyplot.getRangeAxis();
		axis.setAxisLineStroke(new BasicStroke(1.5f));

		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE);
		xylineandshaperenderer.setUseOutlinePaint(true);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setTickMarkInsideLength(2.0F);
		numberaxis.setTickMarkOutsideLength(0.0F);
		numberaxis.setAxisLineStroke(new BasicStroke(1.5f));   
          
		try {
			File file = new File("d:/scatterChart2.png");
			ChartUtilities.saveChartAsPNG(file, chart, 400, 300);// 把报表保存为文件
		} catch (Exception e) {
			String s = e.getLocalizedMessage();
			s = e.getMessage();
			s = e.toString();
		}
		ChartFrame mChartFrame = new ChartFrame("散点图", chart);
		mChartFrame.pack();
		mChartFrame.setVisible(true);
		return "";
	
		/*Base64 base64 = new Base64();
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		try {
			ChartUtilities.writeChartAsPNG(bas, chart, 400, 300);
			bas.flush();
			bas.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] byteArray = bas.toByteArray();
		try {
			InputStream is = new ByteArrayInputStream(byteArray);
			byteArray = new byte[is.available()];
			is.read(byteArray);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imgStr = base64.encodeAsString(byteArray);
		// System.out.println(imgStr);
		return imgStr;*/
    }   
	
	public static String getLineImg() throws IOException{ 
		/*StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);*/
		//创建主题样式  
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
        //设置标题字体  
        mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));  
        //设置轴向字体  
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));  
        //设置图例字体  
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));  
        //应用主题样式  
        ChartFactory.setChartTheme(mChartTheme);  
		CategoryDataset mDataset = GetDataset();
		JFreeChart mChart = ChartFactory.createLineChart("折线图",// 图名字
				"邻居用户数",// 横坐标
				"RMSE",// 纵坐标
				mDataset,// 数据集
				PlotOrientation.VERTICAL, true, // 显示图例
				true, // 采用标准生成器
				false);// 是否生成超链接
		// .LIGHT_GRAY
		CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
		mPlot.setBackgroundPaint(Color.white);
		mPlot.setRangeGridlinePaint(Color.BLUE);// 背景底部横虚线
		mPlot.setOutlinePaint(Color.RED);// 边界线

		/*
		 * Y轴设置
		 */
		NumberAxis vn = (NumberAxis) mPlot.getRangeAxis();
		// DecimalFormat df = new DecimalFormat("#0.00");
		// vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
		vn.setUpperMargin(0.1);
		vn.setLowerMargin(0.1);
		vn.setAutoRangeMinimumSize(0.01);// 最小跨度
		vn.setLowerBound(0.70);// 最小值显示
		vn.setUpperBound(1.10);
		LineAndShapeRenderer lasp = (LineAndShapeRenderer) mPlot.getRenderer();// 获取显示线条的对象
		//lasp.setBaseShapesVisible(true);// 设置拐点是否可见/是否显示拐点
		lasp.setDrawOutlines(true);// 设置拐点不同用不同的形状
		lasp.setUseOutlinePaint(true);//设置是否画曲线数据点的轮廓图形
		lasp.setUseFillPaint(true);// 设置线条是否被显示填充颜色
		lasp.setBaseFillPaint(Color.blue);// // 设置拐点颜色

		/*LineAndShapeRenderer renderer = (LineAndShapeRenderer) mPlot.getRenderer();// 设置样式
		// 设置颜色-----------------------------------------------------------
		//renderer.setSeriesStroke(0, new BasicStroke(4.0F));//设置折线大小
		renderer.setSeriesPaint(0, Color.RED);//红色
		//renderer.setSeriesStroke(1, new BasicStroke(4.0F));
		renderer.setSeriesPaint(1, Color.GREEN);//绿色
		//renderer.setSeriesStroke(2, new BasicStroke(4.0F));
		renderer.setSeriesPaint(2, Color.ORANGE);//c橙色
		//renderer.setSeriesStroke(3, new BasicStroke(4.0F));
		renderer.setSeriesPaint(3, Color.BLUE);//蓝色
		 */
		
		/*
		 * X轴
		 */
		CategoryAxis domainAxis = mPlot.getDomainAxis();
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		domainAxis.setLowerMargin(-0.08);

		// domainAxis.setCategoryMargin(0.5);;
		// System.out.println(domainAxis.getCategoryMargin());;

		// domainAxis.setLabelFont(new Font("宋书", Font.PLAIN, 15)); // 设置横轴字体
		// domainAxis.setTickLabelFont(new Font("宋书", Font.PLAIN, 15));//
		// 设置坐标轴标尺值字体
		// domainAxis.setLowerMargin(0.01);// 左边距 边框距离
		// domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
		// domainAxis.setMaximumCategoryLabelLines(10);
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);//
		// 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
		// domainAxis.setm

		/*try {
			File file = new File("d:/lineChart.png");
			ChartUtilities.saveChartAsPNG(file, mChart, 400, 300);// 把报表保存为文件
		} catch (Exception e) {
			String s = e.getLocalizedMessage();
			s = e.getMessage();
			s = e.toString();
		}
		ChartFrame mChartFrame = new ChartFrame("折线图", mChart);
		mChartFrame.pack();
		mChartFrame.setVisible(true);
        return "";*/
		
		Base64 base64 = new Base64();
        
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ChartUtilities.writeChartAsPNG(bas, mChart, 400, 300);
            bas.flush();
            bas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        byte[] byteArray = bas.toByteArray();
        try {
            InputStream is = new ByteArrayInputStream(byteArray);
            byteArray = new byte[is.available()];
            is.read(byteArray);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgStr = base64.encodeAsString(byteArray);
        //System.out.println(imgStr); 
        return  imgStr;
	}

	public static CategoryDataset GetDataset() {
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		mDataset.addValue(1.10, "First", "5");
		mDataset.addValue(1.05, "First", "10");
		mDataset.addValue(1.00, "First", "15");
		mDataset.addValue(0.95, "First", "20");
		mDataset.addValue(0.90, "First", "25");

		mDataset.addValue(1.05, "Second", "5");
		mDataset.addValue(1.00, "Second", "10");
		mDataset.addValue(0.96, "Second", "15");
		mDataset.addValue(0.91, "Second", "20");
		mDataset.addValue(0.88, "Second", "25");

		mDataset.addValue(1.02, "Third", "5");
		mDataset.addValue(0.90, "Third", "10");
		mDataset.addValue(0.88, "Third", "15");
		mDataset.addValue(0.85, "Third", "20");
		mDataset.addValue(0.7, "Third", "25");
		return mDataset;
	}

}