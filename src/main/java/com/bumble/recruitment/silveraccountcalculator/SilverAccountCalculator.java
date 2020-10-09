package com.bumble.recruitment.silveraccountcalculator;

import com.bumble.recruitment.internal.PromotionServiceServer;

/**
 * The user declares how much they currently pay and their promotion is calculated based on that figure minus a number of deductions.
 * The amount they get back is capped based on number of Spotlight days.
 */
public class SilverAccountCalculator implements Calculator {

  @Override
  public void calculate(int spotlightDays, AccountPayment whatTheyCurrentlyPay) {
    if (convertToCalendarMonthlyAmount(whatTheyCurrentlyPay) > cap(spotlightDays)) {
      PromotionServiceServer.connect().recordCalculatedAmount(cap(spotlightDays));
    }
  }

  private double convertToCalendarMonthlyAmount(AccountPayment whatTheyCurrentlyPay) {
    if (whatTheyCurrentlyPay.getFrequency() == AccountPayment.Frequency.CALENDAR_MONTHLY) {
      return whatTheyCurrentlyPay.getAmount();
    } else {
      return twoWeeksInMonth() * whatTheyCurrentlyPay.getAmount();
    }
  }

  private int twoWeeksInMonth() {
    return (52 / 2) / 12;
  }

  private int cap(int numDays) {
    int cap = -1;
    switch (numDays) {
      case 1:
        cap = 250;
        break;
      case 2:
        cap = 400;
        break;
      default:
        cap = 600;
    }
    return cap;
  }
}
