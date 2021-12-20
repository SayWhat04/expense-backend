package com.expense.domain;

import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Value
public class Money {
    public static Currency DefaultCurrency =
            Currency.getInstance(Locale.getDefault());

    public static Currency USD = Currency.getInstance("USD");
    public static Currency EUR = Currency.getInstance("EUR");
    public static Currency JPY = Currency.getInstance("JPY");

    @NonNull
    private BigDecimal amount;

    @NonNull
    private Currency currency;

    public Money negated() {
        return of(amount.negate(), currency);
    }

    public Money add(Money other) {
        Validate.notNull(other);
        Validate.isTrue(currency.equals(other.currency));
        return of(amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        return add(other.negated());
    }

    public boolean isGreaterThan(Money other) {
        return isGreaterThan(other.amount);
    }

    public boolean isGreaterThan(BigDecimal amount) {
        return this.amount.compareTo(amount) > 0;
    }

    public boolean isLessThan(Money other) {
        return isLessThan(other.amount);
    }

    public boolean isLessThan(BigDecimal amount) {
        return this.amount.compareTo(amount) < 0;
    }

    public static Money of(int amount) {
        return of(BigDecimal.valueOf(amount));
    }

    public static Money of(long amount) {
        return of(BigDecimal.valueOf(amount));
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    public static Money of(BigDecimal amount) {
        return of(amount, DefaultCurrency);
    }
}
