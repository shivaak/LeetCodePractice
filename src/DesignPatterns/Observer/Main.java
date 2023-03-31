package DesignPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    public void update(float temperature);
}

interface Subject {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);

    public void notifyObservers();
}

class WeatherData implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    private int temperature;

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    public void updateTemperature(int temperature){
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observerList) {
            o.update(temperature);
        }
    }
}

class TvMedia implements  Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Update from TV : " + temperature);
    }
}

class YoutubeMedia implements  Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Update from Youtube : " + temperature);
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherData data = new WeatherData();
        data.updateTemperature(100);
        data.updateTemperature(200);

        data.addObserver(new TvMedia());
        data.addObserver(new YoutubeMedia());
        data.updateTemperature(50);
        data.updateTemperature(60);

    }
}
