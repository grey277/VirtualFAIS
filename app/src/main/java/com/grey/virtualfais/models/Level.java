package com.grey.virtualfais.models;

import com.grey.virtualfais.R;

public enum Level {
    ZERO("parter/image", 9963, 6409, 0),
    ONE("Ipietro/image", 9968, 6622, 1),
    TWO("2pietro/image", 9964, 6619, 2);

    private String path;
    private int planWidth;
    private int planHeight;
    private int id;

    Level(String path, int planWidth, int planHeight, int id) {
        this.path = path;
        this.planWidth = planWidth;
        this.planHeight = planHeight;
        this.id = id;
    }

    public static Level getByNumber(int number) {
        switch (number) {
            case 0:
                return Level.ZERO;
            case 1:
                return Level.ONE;
            case 2:
                return Level.TWO;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Level getByButtonId(int buttonId) {
        switch (buttonId) {
            case R.id.level_one:
                return Level.ZERO;
            case R.id.level_two:
                return Level.ONE;
            case R.id.level_three:
                return Level.TWO;
            default:
                throw new IllegalStateException();
        }
    }


    public String getPath() {
        return path;
    }

    public int getPlanWidth() {
        return planWidth;
    }

    public int getPlanHeight() {
        return planHeight;
    }

    public int getId() {
        return id;
    }
}
