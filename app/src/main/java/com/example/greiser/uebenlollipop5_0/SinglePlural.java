package com.example.greiser.uebenlollipop5_0;

class SinglePlural {

    private String sRight;
    private String article;
    private String sWrong1;
    private String sWrong2;
    private String pRight;
    private String pWrong1;
    private String pWrong2;

    public SinglePlural(String[] split) {
        this.sRight = split[1];
        this.article = split[2];
        this.sWrong1 = split[3];
        this.sWrong2 = split[4];
        this.pRight = split[5];
        this.pWrong1 = split[6];
        this.pWrong2 = split[7];
    }

    public String getpRight() {
        return pRight;
    }

    public String getpWrong1() {
        return pWrong1;
    }

    public String getpWrong2() {
        return pWrong2;
    }

    public String getsRight() {
        return sRight;
    }

    public String getsWrong1() {
        return sWrong1;
    }

    public String getsWrong2() {
        return sWrong2;
    }

    public String getArticle() {
        return article;
    }
}
