package com.softserve.jbehavedemo.db;

import com.softserve.jbehavedemo.domain.Discount;
import com.softserve.jbehavedemo.domain.ServiceTier;
import com.softserve.jbehavedemo.domain.Client;

import java.util.HashMap;
import java.util.Map;

public class PseudoDb {

    private Map<Integer, Client> userPool;

    public PseudoDb() {
        userPool = new HashMap<>();
        userPool.put(123, new Client(1234, Discount.NONE, ServiceTier.NONE));
        userPool.put(234, new Client(234, Discount.NONE, ServiceTier.PRO));
        userPool.put(345, new Client(345, Discount.NONE, ServiceTier.ULTRAVIP));
    }

    public Client findClientWithId(Integer id) {
        return this.userPool.get(id);
    }
}
