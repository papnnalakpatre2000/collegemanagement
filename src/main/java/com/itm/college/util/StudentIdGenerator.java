package com.itm.college.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentIdGenerator implements IdentifierGenerator {
    
    private static final AtomicInteger counter = new AtomicInteger(1000);
    
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        int currentYear = Year.now().getValue();
        long customId = Long.parseLong(currentYear + String.format("%05d", counter.incrementAndGet()));
        return customId;
    }
}
