package com.example.clientestealeggs.model;

public class Coordenadas {

    private int x;
    private int y;
    private String id;
    private boolean dir;

    public Coordenadas(int x, int y, boolean dir, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.dir = dir;
    }

    public Coordenadas() {
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
