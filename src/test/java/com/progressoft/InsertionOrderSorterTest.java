package com.progressoft;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class InsertionOrderSorterTest {
    @Test
    public void givenEmptyList_whenSort_thenEmptyListIsReturned() {
        InsertionOrderSorter sorter = new InsertionOrderSorterBuilder().build();
        assertEquals(emptyList(), sorter.calculateInsertionOrder());
    }

    @Test
    public void givenOneTable_whenSort_thenOneTableIsReturned() {
        InsertionOrderSorter sorter = new InsertionOrderSorterBuilder()
                .addTable("Student")
                .build();
        assertEquals(singletonList("Student"), sorter.calculateInsertionOrder());
    }

    @Test
    public void givenTwoUnRelatedTable_whenSort_thenSortedOrderOfDefinition() {
        InsertionOrderSorter sorter = new InsertionOrderSorterBuilder()
                .addTable("Student")
                .addTable("Teacher")
                .build();
        assertEquals(asList("Teacher", "Student"), sorter.calculateInsertionOrder());
    }

    @Test
    public void givenTwoRelatedTable_whenSort_thenOneTableIsReturned() {
        InsertionOrderSorter sorter = new InsertionOrderSorterBuilder()
                .addLink("Student", "Course")
                .build();
        assertEquals(asList("Course", "Student"), sorter.calculateInsertionOrder());
    }

    @Test
    public void givenUnRelatedTable_whenSort_thenUnrelatedComesFirst() {
        InsertionOrderSorter sorter = new InsertionOrderSorterBuilder()
                .addLink("Student", "Course")
                .addTable("User")
                .build();
        assertEquals(asList("User", "Course", "Student"), sorter.calculateInsertionOrder());
    }

    @Test
    public void givenUnRelatedTable_whenSort_thenUnrelatedComesFirst_2() {
        InsertionOrderSorter sorter = new InsertionOrderSorterBuilder()
                .addLink("Grades", "Students")
                .addTable("Teachers")
                .build();
        assertEquals(asList("Teachers", "Students", "Grades"), sorter.calculateInsertionOrder());
    }

    @Test
    public void complexTest() {
    	InsertionOrderSorter sorter = new InsertionOrderSorterBuilder()
                .addLink("T3", "T2")
                .addLink("T4", "T2")
                .addLink("T4", "T3")
                .addLink("T5", "T3")
                .addTable("T1")
                .addLink("T6", "T3")
                .addLink("T5", "T4")
                .addLink("T6", "T5")
                .build();

        List<String> insertionOrder = sorter.calculateInsertionOrder();
        System.out.println("Insertion Order: " + insertionOrder);
    }


}