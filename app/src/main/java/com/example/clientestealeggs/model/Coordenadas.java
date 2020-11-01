package com.example.clientestealeggs.model;

public class Coordenadas {

    private int x;
    private int y;
    private String id;
    private boolean dirLeft;
    private boolean dirRight;
    private boolean steal;
    private boolean jump;

    public Coordenadas(int x, int y, boolean dirLeft, boolean dirRight, boolean steal, boolean jump, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.dirLeft = dirLeft;
        this.dirRight = dirRight;
        this.steal = steal;
        this. jump = jump;
    }

    public Coordenadas() {
    }

    public boolean isDirLeft() {
        return dirLeft;
    }

    public void setDirLeft(boolean dirLeft) {
        this.dirLeft = dirLeft;
    }

    public boolean isDirRight() {
        return dirRight;
    }

    public void setDirRight(boolean dirRight) {
        this.dirRight = dirRight;
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
