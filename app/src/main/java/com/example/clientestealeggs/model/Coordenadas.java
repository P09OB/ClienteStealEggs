package com.example.clientestealeggs.model;

public class Coordenadas {

    private int x;
    private int y;
    private String id;
    private boolean dir;
    private boolean steal;
    private boolean jump;

    public Coordenadas(int x, int y, boolean dir, boolean steal, boolean jump, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.dir = dir;
        this.steal = steal;
        this. jump = jump;
    }

    public Coordenadas() {
    }

    public boolean isSteal() {
        return steal;
    }

    public void setSteal(boolean steal) {
        this.steal = steal;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
