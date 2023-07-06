package com.srp.emulatorsignalovgippocampanbp;

import java.util.Random;
/**
 *
 * @author Maxim-Nt
 */
public class Sensor extends Thread{
    private double value = 0.0; // Текущее значение датчика
    private double time = 0.0; // время работы сенсора 1 = 0.08 мкСек
    private String osciType = "Default"; // Переменная зерна для синхронизации показаний датчиков
    private Random rand = new Random(); // Переменная зерна для синхронизации показаний датчиков
    
    private double tetaFrequency = randomFromTo(4.0, 8.0); // Переменная значения частоты Тета ритма (от 4 до 8 Гц)
    private double alczgamerFrequencyLow = randomFromTo(3.5, 7.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3.5 до 6.5 Гц)
    private double alczgamerFrequencyHigh = randomFromTo(3.0, 6.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3 до 5.5 Гц)
    private double dementionFrequencyLow = randomFromTo(2.5, 5.2); // Переменная значения частоты Тета ритма при деменции (от 2.5 до 5.2 Гц)
    private double dementionFrequencyHigh = randomFromTo(1.0, 4.5); // Переменная значения частоты Тета ритма при деменции (от 1 до 4.5 Гц)
    
    
    private double splashLength = -1.0;
    private double splashStart = -1.0;
    private double splashEnd = splashStart + splashLength;
    private double splashI = 0.0;
    
    public Sensor (String osciType, long seed) { // Конструкторы
        this.osciType = osciType;
        this.rand.setSeed(seed);
        tetaFrequency = randomFromTo(4.0, 8.0); // Переменная значения частоты Тета ритма (от 4 до 8 Гц)
        alczgamerFrequencyLow = randomFromTo(3.5, 7.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3.5 до 6.5 Гц)
        alczgamerFrequencyHigh = randomFromTo(3.0, 6.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3 до 5.5 Гц)
        dementionFrequencyLow = randomFromTo(2.5, 5.2); // Переменная значения частоты Тета ритма при деменции (от 2.5 до 5.2 Гц)
        dementionFrequencyHigh = randomFromTo(1.0, 4.5); // Переменная значения частоты Тета ритма при деменции (от 1 до 4.5 Гц)
    }
    public Sensor (String osciType) {
        this.osciType = osciType;
    }
    public Sensor (long seed) {
        this.rand.setSeed(seed);
        tetaFrequency = randomFromTo(4.0, 8.0); // Переменная значения частоты Тета ритма (от 4 до 8 Гц)
        alczgamerFrequencyLow = randomFromTo(3.5, 7.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3.5 до 6.5 Гц)
        alczgamerFrequencyHigh = randomFromTo(3.0, 6.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3 до 5.5 Гц)
        dementionFrequencyLow = randomFromTo(2.5, 5.2); // Переменная значения частоты Тета ритма при деменции (от 2.5 до 5.2 Гц)
        dementionFrequencyHigh = randomFromTo(1.0, 4.5); // Переменная значения частоты Тета ритма при деменции (от 1 до 4.5 Гц)
    }
    public Sensor () {    }
    
    
    public double getValue() { // Методы класса
        return value;
    }
    public void setOscilationType(String osciType) {
        this.osciType = osciType;
    }
    public void setSeed(long seed) {
        this.rand.setSeed(seed);
        tetaFrequency = randomFromTo(4.0, 8.0); // Переменная значения частоты Тета ритма (от 4 до 8 Гц)
        alczgamerFrequencyLow = randomFromTo(3.5, 7.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3.5 до 6.5 Гц)
        alczgamerFrequencyHigh = randomFromTo(3.0, 6.5); // Переменная значения частоты Тета ритма при Альцгеймере (от 3 до 5.5 Гц)
        dementionFrequencyLow = randomFromTo(2.5, 5.2); // Переменная значения частоты Тета ритма при деменции (от 2.5 до 5.2 Гц)
        dementionFrequencyHigh = randomFromTo(1.0, 4.5); // Переменная значения частоты Тета ритма при деменции (от 1 до 4.5 Гц)
    }
    public void setTime(double time) { // устанавливает текущее время работы сенсора в миллисекундах.
        this.time = time/20.0;
    }
    public double getTime() { // возвращает время работы сенсора в миллисекундах.
        return time*20.0;
    }
    public void setTetaFrequency(double tetaFrequency) {
        this.tetaFrequency = tetaFrequency;
    }
    public double getTetaFrequency() {
        return tetaFrequency;
    }
    
    @Override
    public void run() {
            switch (osciType) {
                case ("TetaDementionHigh") -> {
                    value = osciTypeTetaDementionHigh(time);
                }
                case ("TetaDementionLow") -> {
                    value = osciTypeTetaDementionLow(time);
                }
                case ("TetaAlczgamerHigh") -> {
                    value = osciTypeTetaAlczgamerHigh(time);
                }
                case ("TetaAlczgamerLow") -> {
                    value = osciTypeTetaAlczgamerLow(time);
                }
                case ("Teta") -> {
                    value = osciTypeTeta(time);
                }
                default -> {
                    value = osciTypeSin(time);
                }
            }
            time = time + 1.0;
    }

    // ******
    private double osciTypeSin(double i) { // синусойда с частотой 1 Гц - дефолтная
	return ( Math.sin(Math.toRadians( i*7.2 )) ); //возможно надо конвертить i в дабл // i*7.2 = 1 Гц
    }
    
    private double additionalDelta(double i) { // Дельта ритм до 4 Гц. Фаза медленного сна, длительная концентрация внимания
	return ( Math.sin(Math.toRadians( (i*7.2)+(21.6*rand.nextDouble()) ))
                *(2.0+(8.0*Math.random())) );
    }

    private double makeSplash(double splashLength) {
        splashI += 1.0;
        return Math.sin(Math.toRadians( splashI*(180.0/splashLength) ))
                *(25.0+(15.0*Math.random()));
    }
    private double makeSplashAlczgamerHigh(double splashLength) {
        splashI += 1.0;
        return Math.sin(Math.toRadians( splashI*(180.0/splashLength) ))
                *(25.0+(30.0*Math.random()));
    }
    private double makeSplashDementionLow(double splashLength) {
        splashI += 1.0;
        return Math.sin(Math.toRadians( splashI*(180.0/splashLength) ))
                *(30.0+(35.0*Math.random()));
    }
    private double makeSplashDementionHigh(double splashLength) {
        splashI += 1.0;
        return Math.sin(Math.toRadians( splashI*(180.0/splashLength) ))
                *(40.0+(60.0*Math.random()));
    }
    
    private double randomFromTo(double from, double to) { // Установка случайного значения частоты Тета ритма (от 4 до 8 Гц)
        return from + ((to - from)*rand.nextDouble());
    }
    
    private double osciTypeTeta(double i) { // Тета ритм 4-8 Гц. Полусон, расслабление.
        double ret = 0.0;
        if (i > splashStart && i < splashEnd) {
            ret += makeSplash(splashLength);
        }
        if (i > splashEnd) {
            splashStart = i + 50 + (100.0*rand.nextDouble());
            splashLength = 20.0 + 25.0*rand.nextDouble();
            splashEnd = splashStart + splashLength;
        }
        
        ret += additionalDelta(i) + ( Math.sin(Math.toRadians( 
                (i*7.2*tetaFrequency)+(28.8*rand.nextDouble()) ))
                *(5.0+(15.0*Math.random())) );
	return ret;
    }
    
    private double osciTypeTetaAlczgamerLow(double i) { // Небольшое замедление ритма
        double ret = 0.0;
        if (i > splashStart && i < splashEnd) {
            ret += makeSplash(splashLength);
        }
        if (i > splashEnd) {
            splashStart = i + 50 + (100.0*rand.nextDouble());
            splashLength = 20.0 + 35.0*rand.nextDouble();
            splashEnd = splashStart + splashLength;
        }
        ret += additionalDelta(i) + ( Math.sin(Math.toRadians( 
                (i*7.2*alczgamerFrequencyLow)
                +(28.8*rand.nextDouble()) ))*(10.0+(20.0*Math.random())) );
	return ret;
    }
    
    private double osciTypeTetaAlczgamerHigh(double i) { // Чуть большее замедление ритма, усиление сплэшей
        double ret = 0.0;
        if (i > splashStart && i < splashEnd) {
            ret += makeSplashAlczgamerHigh(splashLength);
        }
        if (i > splashEnd) {
            splashStart = i + 50 + (100.0*rand.nextDouble());
            splashLength = 20.0 + 35.0*rand.nextDouble();
            splashEnd = splashStart + splashLength;
        }
        ret += additionalDelta(i) + ( Math.sin(Math.toRadians( 
                (i*7.2*alczgamerFrequencyHigh)
                +(28.8*rand.nextDouble()) ))*(10.0+(25.0*Math.random())) );
	return ret;
    }
    
    private double osciTypeTetaDementionLow(double i) { // Замедление ритма, большее усиление сплэшей, сокращение фоновой частоты
        double ret = 0.0;
        if (i > splashStart && i < splashEnd) {
            ret += makeSplashDementionLow(splashLength);
        }
        if (i > splashEnd) {
            splashStart = i + 40 + (100.0*rand.nextDouble());
            splashLength = 20.0 + 40.0*rand.nextDouble();
            splashEnd = splashStart + splashLength;
        }
        ret += additionalDelta(i) + ( Math.sin(Math.toRadians( 
                (i*7.2*dementionFrequencyLow)
                +(28.8*rand.nextDouble()) ))*(7.0+(25.0*Math.random())) );
	return ret;
    }
    
    private double osciTypeTetaDementionHigh(double i) { // Серьезное замедление ритма, серьезное усиление сплэшей, сокращение фоновой частоты
        double ret = 0.0;
        if (i > splashStart && i < splashEnd) {
            ret += makeSplashDementionHigh(splashLength);
        }
        if (i > splashEnd) {
            splashStart = i + 20 + (100.0*rand.nextDouble());
            splashLength = 30.0 + 55.0*rand.nextDouble();
            splashEnd = splashStart + splashLength;
        }
        ret += additionalDelta(i) + ( Math.sin(Math.toRadians( 
                (i*7.2*dementionFrequencyHigh)
                +(28.8*rand.nextDouble()) ))*(5.0+(20.0*Math.random())) );
	return ret;
    }
    
}
