package com.pensio.api.request;

import com.pensio.Amount;
import com.pensio.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class PaymentReservationRequestTest {

    @Test
    void chosenScheme_defaultsToNull() {
        PaymentReservationRequest request = new PaymentReservationRequest(
                "order-1", "terminal-1", Amount.get(10.0, Currency.EUR));

        assertNull(request.getChosenScheme());
    }

    @Test
    void chosenScheme_setterGetterRoundTrip() {
        PaymentReservationRequest request = new PaymentReservationRequest(
                "order-1", "terminal-1", Amount.get(10.0, Currency.EUR));

        request.setChosenScheme("Visa");

        assertEquals("Visa", request.getChosenScheme());
    }

    @Test
    void chosenScheme_setterReturnsSameInstance_forFluentChaining() {
        PaymentReservationRequest request = new PaymentReservationRequest(
                "order-1", "terminal-1", Amount.get(10.0, Currency.EUR));

        PaymentReservationRequest returned = request.setChosenScheme("Mastercard");

        assertSame(request, returned);
    }
}
