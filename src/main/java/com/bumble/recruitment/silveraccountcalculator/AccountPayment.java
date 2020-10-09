package com.bumble.recruitment.silveraccountcalculator;

public class AccountPayment {

  private final double amount;
  private final Frequency frequency;

  public AccountPayment(double amount, Frequency frequency) {
    this.amount = amount;
    this.frequency = frequency;
  }

  public double getAmount() {
    return amount;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public enum Frequency {
    BIWEEKLY, CALENDAR_MONTHLY
  }

}
