/**
 * Project Name:demo
 * File Name:JfreeChartServiceImpl.java
 * Package Name:com.example.demo.service.impl
 * Date:2019年1月28日下午5:34:35
 * Copyright (c) 2019, SPRIXIN.com All Rights Reserved.
 */
package com.example.demo.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.example.demo.service.JfreeChartService;

/**这里用一句话描述这个类的作用<br/><br/>
 * 创建时间： 2019年1月28日 下午5:34:35 <br/>
 * @author yao
 */
@Service
public class JfreeChartServiceImpl implements JfreeChartService{
    public String getImg() throws IOException{ 
        CategoryDataset dataset = getDataSet2(); 
        JFreeChart chart = ChartFactory.createLineChart( 
                           "水果产量图", // 图表标题
                           "水果", // 目录轴的显示标签
                           "产量", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            false, // 是否生成工具
                            false  // 是否生成 URL 链接
                            ); 
        
        chart.setBackgroundPaint(Color.WHITE); 
        CategoryPlot plot = chart.getCategoryPlot(); 
        CategoryAxis domainAxis = plot.getDomainAxis(); 
        domainAxis.setAxisLineVisible(false); 
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        plot.setDomainAxis(domainAxis); 
        BarRenderer3D renderer = new BarRenderer3D(); 
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 显示每个柱的数值，并修改该数值的字体属性 
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        // 设置每个地区所包含的平行柱的之间距离 
        renderer.setItemMargin(0.1); 
        // 设置柱的数值可见
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer); 
        // 设置柱的透明度 
        plot.setForegroundAlpha(0.8f); 
                            
        Base64 base64 = new Base64();
        
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
        System.out.println(imgStr); 
        return  imgStr;
    
        /*FileOutputStream fos_jpg = null; 
        try { 
            fos_jpg = new FileOutputStream("D:\\fruitt.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300); 
        } finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        } 
        return "";*/
    }  
    
    public static void main(String[] args) {
    	JfreeChartServiceImpl aa = new JfreeChartServiceImpl();
    	try {
			aa.getImg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /** 
    * 获取一个演示用的组合数据集对象
    * @return 
    */ 
    private static CategoryDataset getDataSet2() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        dataset.addValue(100, "北京", "苹果"); 
        dataset.addValue(100, "上海", "苹果"); 
        dataset.addValue(100, "广州", "苹果"); 
        dataset.addValue(200, "北京", "梨子"); 
        dataset.addValue(200, "上海", "梨子"); 
        dataset.addValue(200, "广州", "梨子"); 
        dataset.addValue(300, "北京", "葡萄"); 
        dataset.addValue(300, "上海", "葡萄"); 
        dataset.addValue(300, "广州", "葡萄"); 
        dataset.addValue(400, "北京", "香蕉"); 
        dataset.addValue(400, "上海", "香蕉"); 
        dataset.addValue(400, "广州", "香蕉"); 
        dataset.addValue(500, "北京", "荔枝"); 
        dataset.addValue(500, "上海", "荔枝"); 
        dataset.addValue(500, "广州", "荔枝"); 
        return dataset; 
    } 
}
