package com.work.frames;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class ShowECG extends JFrame {
	private static JTextArea textArea = new JTextArea("***Start working***");
	private static TimeSeries series;
	private JPanel main;
	private JPanel draw;

	@SuppressWarnings("deprecation")
	public ShowECG () {
		super("ECG");

		ShowECG.series = new TimeSeries("Data From COM", Millisecond.class);
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);

		main = new JPanel();
		draw = new JPanel();

		main.setLayout(new BorderLayout());
		draw.setBackground(Color.WHITE);

		main.add(draw, BorderLayout.CENTER);
		//main.add(textArea, BorderLayout.NORTH);

		main.add(chartPanel);



		this.add(main);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		/*clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
			}
		});
		 */


	}  // ****END CONSTRUCTOR******

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				"Dynamic Data Draw", 
				"Time", 
				"Value",
				dataset, 
				true, 
				true, 
				false
				);
		final XYPlot plot = result.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		axis.setFixedAutoRange(600000.0);  // 60 seconds
		axis = plot.getRangeAxis();
		axis.setRange(-200.0, 200.0); 
		return result;
	}

	public static void showECG(LinkedList<Integer> dataChanel) {
		for(int i = 0; i < dataChanel.size(); i++) {
			final Millisecond now = new Millisecond();
			while(now.equals(new Millisecond())) {}
			//series.add(new Millisecond(), temp[i]);
			series.add(new Millisecond(), dataChanel.get(i));
		}
	}
	
	





}
