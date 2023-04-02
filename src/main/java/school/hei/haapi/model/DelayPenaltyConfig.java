package school.hei.haapi.model;

public class DelayPenaltyConfig {
    private double interestRate;
    private int gracePeriodInDays;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getGracePeriodInDays() {
        return gracePeriodInDays;
    }

    public void setGracePeriodInDays(int gracePeriodInDays) {
        this.gracePeriodInDays = gracePeriodInDays;
    }
}

