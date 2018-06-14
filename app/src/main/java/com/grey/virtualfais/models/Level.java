package com.grey.virtualfais.models;

import com.grey.virtualfais.R;

public enum Level {
    ZERO("parter/image", 9963, 6409, R.drawable.maska_parter),
    ONE("Ipietro/image", 9968, 6622, R.drawable.pietro_1_maska),
    TWO("2pietro/image", 9964, 6619, R.drawable.pietro_2_maska);

    private String path;
    private int planWidth;
    private int planHeight;
    private int maskId;

    Level(String path, int planWidth, int planHeight, int maskId) {
        this.path = path;
        this.planWidth = planWidth;
        this.planHeight = planHeight;
        this.maskId = maskId;
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

    public int getMaskId() {
        return maskId;
    }
}
