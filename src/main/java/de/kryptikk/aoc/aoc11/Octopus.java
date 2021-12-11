package de.kryptikk.aoc.aoc11;

public class Octopus {
  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public boolean isFlashed() {
    return flashed;
  }

  public void setFlashed(boolean flashed) {
    this.flashed = flashed;
  }

  private int energy;
  private boolean flashed = false;

  public Octopus(int energy) {
    this.energy = energy;
  }

  public void increaseEnergy() {
    energy++;
  }
}
