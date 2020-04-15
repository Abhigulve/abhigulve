package sensor;

import controller.Controller;
import model.Request;

public class Sensor {
    private Controller observer;

    private Request request;


    public Sensor(Controller observer) {
        this.observer = observer;
    }

    public Request getState() {
        return request;
    }

    public void setState(Request state) {
        this.request = state;
        notifyAllObservers();
    }

    public void subscribe(Controller observer) {
        this.observer = (observer);
    }

    public void notifyAllObservers() {
        observer.update(request);
    }
}