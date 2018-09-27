package com.work.frames;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;



@SuppressWarnings("serial")
public class DrawFrame extends JFrame {

	private static JTextArea textArea = new JTextArea("***Start working***");
	private JPanel main;
	private JPanel draw;
	// Список координат точек для построения графика
	private Double[][] graphicsData;
	// Флаговые переменные, задающие правила отображения графика
	private boolean showAxis = true;
	private boolean showMarkers = true;
	// Границы диапазона пространства, подлежащего отображению
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	// Используемый масштаб отображения
	private double scale;
	// Различные стили черчения линий
	private BasicStroke graphicsStroke;
	private BasicStroke axisStroke;
	private BasicStroke markerStroke;
	// Шрифт отображения подписей к осям координат
	private Font axisFont;




	public DrawFrame () {
		super("Draw");
		

		main = new JPanel();
		draw = new JPanel();

		main.setLayout(new BorderLayout());
		draw.setBackground(Color.WHITE);

		main.add(draw, BorderLayout.CENTER);
		main.add(textArea, BorderLayout.NORTH);

		// Перо для рисования графика
		graphicsStroke = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f);
		// Перо для рисования осей координат
		axisStroke = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		// Перо для рисования контуров маркеров
		markerStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		// Шрифт для подписей осей координат
		axisFont = new Font("Serif", Font.BOLD, 36);



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


	public void showGraphics(Double[][] graphicsData) {
		this.graphicsData = graphicsData;
		repaint();
	}



}
