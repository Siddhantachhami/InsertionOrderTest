package com.progressoft;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;

public class InsertionOrderSorterBuilder {
    private final Map<String, Set<String>> tables = new HashMap<>();

    public InsertionOrderSorterBuilder addTable(String table) {
        tables.putIfAbsent(table, new HashSet<>());
        return this;
    }

    public InsertionOrderSorterBuilder addLink(String foreignKeyTable, String primaryKeyTable) {
        addTable(primaryKeyTable);
        addTable(foreignKeyTable);
        tables.get(foreignKeyTable).add(primaryKeyTable);
        return this;
    }

    public InsertionOrderSorter build() {
        return new InsertionOrderSorter(tables);
    }
}
