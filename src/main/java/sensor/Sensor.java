package sensor;

import controller.Controller;
import model.CorridorType;
import model.Request;
import org.omg.PortableInterceptor.ObjectReferenceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Sensor {

    private List<Controller> observers = new ArrayList<>();
    private Request request;

    public Request getState() {
        return request;
    }

    public void setState(Request state) {
        this.request = state;
        notifyAllObservers();
    }

    public void subscribe(Controller observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Controller observer : observers) {
            observer.update(request);
        }
    }
}