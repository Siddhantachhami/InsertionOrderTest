package com.progressoft;

import java.util.*;

public class InsertionOrderSorter {
    private final Map<String, Set<String>> tables;

    public InsertionOrderSorter(Map<String, Set<String>> tables) {
        this.tables = tables;
    }

    public List<String> calculateInsertionOrder() {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();
        Set<String> visitedInCycle = new HashSet<>();

        for (String table : tables.keySet()) {
            if (!visited.contains(table)) {
                if (hasCircularDependency(table, visited, recursionStack, visitedInCycle)) {
                    throw new IllegalStateException("Circular dependency detected involving table: " + table);
                }
                dfs(table, visited, recursionStack, result);
            }
        }

        return result;
    }

    private void dfs(String table, Set<String> visited, Set<String> recursionStack, List<String> result) {
        visited.add(table);
        recursionStack.add(table);

        Set<String> dependencies = tables.getOrDefault(table, new HashSet<>());
        for (String dependency : dependencies) {
            if (!visited.contains(dependency)) {
                dfs(dependency, visited, recursionStack, result);
            }
        }

        recursionStack.remove(table);
        result.add(table);
    }

    private boolean hasCircularDependency(String table, Set<String> visited, Set<String> recursionStack, Set<String> visitedInCycle) {
        visited.add(table);
        recursionStack.add(table);
        visitedInCycle.add(table);

        Set<String> dependencies = tables.getOrDefault(table, new HashSet<>());
        for (String dependency : dependencies) {
            if (!visited.contains(dependency)) {
                if (hasCircularDependency(dependency, visited, recursionStack, visitedInCycle)) {
                    return true;
                }
            } else if (recursionStack.contains(dependency) && visitedInCycle.contains(dependency)) {
                return true;
            }
        }

        recursionStack.remove(table);
        visitedInCycle.remove(table);
        return false;
    }
}
