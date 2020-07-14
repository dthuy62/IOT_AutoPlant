package com.example.iot_autoplant.Model;

public class Info {
    public boolean isPump() {
        return pump;
    }

    public void setPump(boolean pump) {
        this.pump = pump;
    }

    public Info(boolean pump) {
        this.pump = pump;
    }

    public boolean pump;
}
