package com.example._datastruc2_20102669;

public class LandmarkInfo {

    int xCoord;
    int yCoord;
    String name = "";
    int cultureVal = 0;

    public LandmarkInfo(String name, PointPixel coords, int cultureVal) {
        setName(name);
        setyCoord(coords.getyCoord());
        setxCoord(coords.getxCoord());
        setCultureVal(cultureVal);
    }

    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }

    public String getName() {
        return name;
    }
    public int getCultureVal() {
        return cultureVal;
    }


    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCultureVal(int cultureVal) {
        this.cultureVal = cultureVal;
    }

    @Override
    public String toString() {
        return "LandmarkInfo{" +
                "x=" + xCoord +
                ", y=" + yCoord +
                ", name='" + name + '\'' +
                '}';
    }
}
