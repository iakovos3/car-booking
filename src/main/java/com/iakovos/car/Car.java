package com.iakovos.car;

import java.math.BigDecimal;
import java.util.UUID;

public record Car(
    UUID id,
    String regNumber,
    BigDecimal rentalPricePerDay,
    Brand brand,
    String modelName,
    boolean isElectric
) {}
