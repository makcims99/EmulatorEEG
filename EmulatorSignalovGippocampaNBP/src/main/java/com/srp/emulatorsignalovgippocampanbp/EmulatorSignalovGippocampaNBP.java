/*
package com.srp.emulatorsignalovgippocampanbp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;


public class EmulatorSignalovGippocampaNBP {

    public static void main(String[] args) {
        new StartStopWindow();
    }
}
*/

package com.srp.emulatorsignalovgippocampanbp;

/**
 *
 * @author Maxim-Nt
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class EmulatorSignalovGippocampaNBP extends javax.swing.JFrame implements ActionListener{
    private JButton startButton;
    private JButton stopButton;
    private JButton clearButton;
    private JComboBox jComboBox1;
    private boolean isRunning;
    private Timer timer;
    
    private DefaultXYDataset datasetT3;
    private JFreeChart chartT3;
    
    private DefaultXYDataset datasetT5;
    private JFreeChart chartT5;
    
    private DefaultXYDataset datasetT4;
    private JFreeChart chartT4;
    
    private DefaultXYDataset datasetT6;
    private JFreeChart chartT6;
    
    public EmulatorSignalovGippocampaNBP() {
        // Установка заголовка окна
        super("Программный эмулятор сигналов гиппокампа");

        // Создание активных компонентов окна
        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");
        clearButton = new JButton("Очистить");
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>
            (new String[] { "Нормальный ритм", "Альцгеймер неявно выраженный", "Альцгеймер явно выраженный", "Деменция неявно выраженная","Деменция явно выраженная" }));

        // Добавление обработчика событий для кнопок
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        clearButton.addActionListener(this);
        jComboBox1.addActionListener(this);

        // Создание панели для размещения кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Установка выравнивания и отступов
        buttonPanel.add(jComboBox1);
        buttonPanel.add(clearButton);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        
        // Установка компоновщика BorderLayout и добавление панели с кнопками в южную (нижнюю) часть окна
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        // Инициализация графика
        datasetT3 = new DefaultXYDataset();
        chartT3 = ChartFactory.createXYLineChart("", "", "T3", datasetT3);
        XYPlot plotT3 = chartT3.getXYPlot();
        NumberAxis domainAxisT3 = (NumberAxis) plotT3.getDomainAxis();
        domainAxisT3.setAutoRangeIncludesZero(false);
        
        datasetT5 = new DefaultXYDataset();
        chartT5 = ChartFactory.createXYLineChart("", "", "T5", datasetT5);
        XYPlot plotT5 = chartT5.getXYPlot();
        NumberAxis domainAxisT5 = (NumberAxis) plotT5.getDomainAxis();
        domainAxisT5.setAutoRangeIncludesZero(false);
        
        datasetT4 = new DefaultXYDataset();
        chartT4 = ChartFactory.createXYLineChart("", "", "T4", datasetT4);
        XYPlot plotT4 = chartT4.getXYPlot();
        NumberAxis domainAxisT4 = (NumberAxis) plotT4.getDomainAxis();
        domainAxisT4.setAutoRangeIncludesZero(false);
        
        datasetT6 = new DefaultXYDataset();
        chartT6 = ChartFactory.createXYLineChart("", "", "T6", datasetT6);
        XYPlot plotT6 = chartT6.getXYPlot();
        NumberAxis domainAxisT6 = (NumberAxis) plotT6.getDomainAxis();
        domainAxisT6.setAutoRangeIncludesZero(false);
        
        // Изменение рендерера графика для соединения точек линиями и отключения отображения точек
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true); // Установка отображения линий
        renderer.setSeriesShapesVisible(0, false); // Отключение отображения точек
        renderer.setSeriesVisibleInLegend(0, false); // Отключение отображения легенды графика
        
        plotT3.setRenderer(renderer);
        plotT5.setRenderer(renderer);
        plotT4.setRenderer(renderer);
        plotT6.setRenderer(renderer);

        // Создание панели для размещения графика
        ChartPanel chartPanelT3 = new ChartPanel(chartT3);
        ChartPanel chartPanelT5 = new ChartPanel(chartT5);
        ChartPanel chartPanelT4 = new ChartPanel(chartT4);
        ChartPanel chartPanelT6 = new ChartPanel(chartT6);
        
        // Добавление панели с графиком в окно
        JPanel grid = new JPanel();
        GridLayout layout = new GridLayout(4, 0, 1, 1);
        grid.setLayout(layout);
        
        grid.add(chartPanelT3);
        grid.add(chartPanelT5);
        grid.add(chartPanelT4);
        grid.add(chartPanelT6);
        
        getContentPane().add(grid);
        pack();
        setVisible(true);

        // Установка размеров окна и видимость
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Синхронизация датчиков
    long seedA = (long) (1000000000*Math.random());
    long seedB = (long) (1000000000*Math.random());
    
    ArrayList <Double> sensorValuesT3 = new ArrayList();
    ArrayList <Double> sensorTimesT3 = new ArrayList();
    Sensor sensorT3 = new Sensor("Teta", seedA);
    
    ArrayList <Double> sensorValuesT5 = new ArrayList();
    ArrayList <Double> sensorTimesT5 = new ArrayList();
    Sensor sensorT5 = new Sensor("Teta", seedA);
    
    ArrayList <Double> sensorValuesT4 = new ArrayList();
    ArrayList <Double> sensorTimesT4 = new ArrayList();
    Sensor sensorT4 = new Sensor("Teta", seedB);
    
    ArrayList <Double> sensorValuesT6 = new ArrayList();
    ArrayList <Double> sensorTimesT6 = new ArrayList();
    Sensor sensorT6 = new Sensor("Teta", seedB);
    
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startButton) {
        if (!isRunning) {
            isRunning = true;
            // Создание таймера с интервалом 80 мс (0.08 сек)
            timer = new Timer(20, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    sensorT3.run();
                    sensorT5.run();
                    sensorT4.run();
                    sensorT6.run();
                    
                    sensorValuesT3.add(sensorT3.getValue());
                    sensorTimesT3.add(sensorT3.getTime());
                    datasetT3.addSeries("T3" , new double [][]{ convertDoubleListToDoubleArray(sensorTimesT3), 
                        convertDoubleListToDoubleArray(sensorValuesT3) });
                    
                    sensorValuesT5.add(sensorT5.getValue());
                    sensorTimesT5.add(sensorT5.getTime());
                    datasetT5.addSeries("T5" , new double [][]{ convertDoubleListToDoubleArray(sensorTimesT5), 
                        convertDoubleListToDoubleArray(sensorValuesT5) });
                    
                    sensorValuesT4.add(sensorT4.getValue());
                    sensorTimesT4.add(sensorT4.getTime());
                    datasetT4.addSeries("T4" , new double [][]{ convertDoubleListToDoubleArray(sensorTimesT4), 
                        convertDoubleListToDoubleArray(sensorValuesT4) });
                    
                    sensorValuesT6.add(sensorT6.getValue());
                    sensorTimesT6.add(sensorT6.getTime());
                    datasetT6.addSeries("T6" , new double [][]{ convertDoubleListToDoubleArray(sensorTimesT6), 
                        convertDoubleListToDoubleArray(sensorValuesT6) });
                }
            });
            // Запуск таймера
            timer.start();
        }
    } else if (e.getSource() == stopButton) {
        if (isRunning) {
            isRunning = false;
            // Остановка таймера
            timer.stop();
            // создание самого excel файла в памяти
            HSSFWorkbook workbook = new HSSFWorkbook();
            // создание листа с названием "Гиппокамп"
            HSSFSheet sheet = workbook.createSheet("Гиппокамп");
            // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("T3");
            row.createCell(1).setCellValue("T5");
            row.createCell(2).setCellValue("T4");
            row.createCell(3).setCellValue("T6");
            // заполняем лист данными
            for (int i = 0; i < sensorValuesT3.size(); i++) {
                row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(sensorValuesT3.get(i));
                row.createCell(1).setCellValue(sensorValuesT5.get(i));
                row.createCell(2).setCellValue(sensorValuesT4.get(i));
                row.createCell(3).setCellValue(sensorValuesT6.get(i));
            }
            // сохраняем результаты на диск в "Мои документы"
            String pathToMyDocs = new JFileChooser().getFileSystemView().getDefaultDirectory().toString().replace("\\", "\\\\");
            String fileName = Objects.toString(System.currentTimeMillis());
            new File(pathToMyDocs+"\\EmulatorSignalovGippokampa").mkdirs();
            try (FileOutputStream out = new FileOutputStream(new File(pathToMyDocs+"\\EmulatorSignalovGippokampa\\"+fileName+".xls"))) {
                workbook.write(out);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    } else if (e.getSource() == clearButton) {
        // очистка значений
        if (!isRunning) {
            sensorValuesT3.clear();
            sensorTimesT3.clear();
            datasetT3.removeSeries("T3");
            sensorT3.setTime(0.0);
            chartT3.clearSubtitles();
        
            sensorValuesT5.clear();
            sensorTimesT5.clear();
            datasetT5.removeSeries("T5");
            sensorT5.setTime(0.0);
            chartT5.clearSubtitles();
        
            sensorValuesT4.clear();
            sensorTimesT4.clear();
            datasetT4.removeSeries("T4");
            sensorT4.setTime(0.0);
            chartT4.clearSubtitles();
        
            sensorValuesT6.clear();
            sensorTimesT6.clear();
            datasetT6.removeSeries("T6");
            sensorT6.setTime(0.0);
            chartT6.clearSubtitles();
        }
    } else if (e.getSource() == jComboBox1) {
        switch ((String)jComboBox1.getSelectedItem()) {
            case ("Нормальный ритм") -> {
                sensorT3.setOscilationType("Teta");
                sensorT5.setOscilationType("Teta");
                sensorT4.setOscilationType("Teta");
                sensorT6.setOscilationType("Teta");
            }
            case ("Альцгеймер неявно выраженный") -> {
                sensorT3.setOscilationType("TetaAlczgamerLow");
                sensorT5.setOscilationType("TetaAlczgamerLow");
                sensorT4.setOscilationType("TetaAlczgamerLow");
                sensorT6.setOscilationType("TetaAlczgamerLow");
            }
            case ("Альцгеймер явно выраженный") -> {
                sensorT3.setOscilationType("TetaAlczgamerHigh");
                sensorT5.setOscilationType("TetaAlczgamerHigh");
                sensorT4.setOscilationType("TetaAlczgamerHigh");
                sensorT6.setOscilationType("TetaAlczgamerHigh");
            }
            case ("Деменция неявно выраженная") -> {
                sensorT3.setOscilationType("TetaDementionLow");
                sensorT5.setOscilationType("TetaDementionLow");
                sensorT4.setOscilationType("TetaDementionLow");
                sensorT6.setOscilationType("TetaDementionLow");
            }
            case ("Деменция явно выраженная") -> {
                sensorT3.setOscilationType("TetaDementionHigh");
                sensorT5.setOscilationType("TetaDementionHigh");
                sensorT4.setOscilationType("TetaDementionHigh");
                sensorT6.setOscilationType("TetaDementionHigh");
            }
            default -> {
                // ничего не меняем
            }
        }
    }
}

    private double[] convertDoubleListToDoubleArray(ArrayList <Double> listDouble) {
        double[] ret = new double[listDouble.size()];
        for ( int i = 0; i < listDouble.size(); i++) {
            ret[i] = listDouble.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmulatorSignalovGippocampaNBP();
            }
        });
    }
}



