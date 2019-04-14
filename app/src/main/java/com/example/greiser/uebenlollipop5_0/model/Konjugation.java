package com.example.greiser.uebenlollipop5_0.model;

public class Konjugation {
  private final String infinitive;
  private final String i;
  private final String sYou;
  private final String he;
  private final String we;
  private final String your;
  private final String pYou;

  public Konjugation(String[] verbForms) {
    this.infinitive = verbForms[1];
    this.i = verbForms[2];
    this.sYou = verbForms[3];
    this.he = verbForms[4];
    this.we = verbForms[5];
    this.your = verbForms[6];
    this.pYou = verbForms[7];
  }

  public String getpYou() {
    return pYou;
  }

  public String getYour() {
    return your;
  }

  public String getWe() {
    return we;
  }

  public String getHe() {
    return he;
  }

  public String getsYou() {
    return sYou;
  }

  public String getI() {
    return i;
  }

  public String getInfinitive() {
    return infinitive;
  }
}
