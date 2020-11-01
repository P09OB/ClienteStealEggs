package com.example.clientestealeggs.model;

public class Restart {

    boolean finish;

    public Restart() {
    }

    public Restart(boolean finish) {
        this.finish = finish;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
