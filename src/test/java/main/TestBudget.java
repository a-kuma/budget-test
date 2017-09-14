package main;

import budget.Budget;
import budget.BudgetRepo;
import budget.TotalAmount;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestBudget {

    @Test
    public void totalAmountEmpty() {
        BudgetRepo budgetRepo = Mockito.mock(BudgetRepo.class);
        when(budgetRepo.findAll()).thenReturn(new ArrayList<>());
        TotalAmount totalAmount = new TotalAmount(budgetRepo);

        assertEquals(0, totalAmount.findBetween("2016-06-10", "2017-07-15"), 0.001);
    }

    @Test
    public void totalAmountSeqSingleMonth() {
        BudgetRepo budgetRepo = Mockito.mock(BudgetRepo.class);
        List<Budget> allBudgets = new ArrayList<>();
        allBudgets.add(new Budget("201706", 300));
        allBudgets.add(new Budget("201707", 310));
        when(budgetRepo.findAll()).thenReturn(allBudgets);
        TotalAmount totalAmount = new TotalAmount(budgetRepo);
        assertEquals(160, totalAmount.findBetween("2017-06-10", "2017-06-25"), 0.001);
    }

    @Test
    public void totalAmountMultiMonths() {
        BudgetRepo budgetRepo = Mockito.mock(BudgetRepo.class);
        List<Budget> allBudgets = new ArrayList<>();
        allBudgets.add(new Budget("201706", 300));
        allBudgets.add(new Budget("201707", 310));
        when(budgetRepo.findAll()).thenReturn(allBudgets);
        TotalAmount totalAmount = new TotalAmount(budgetRepo);
        assertEquals(610, totalAmount.findBetween("2017-02-10", "2017-08-15"), 0.001);
    }

    @Test
    public void totalAmountCrossYear() {
        BudgetRepo budgetRepo = Mockito.mock(BudgetRepo.class);
        List<Budget> allBudgets = new ArrayList<>();
        allBudgets.add(new Budget("201706", 300));
        allBudgets.add(new Budget("201707", 310));
        when(budgetRepo.findAll()).thenReturn(allBudgets);
        TotalAmount totalAmount = new TotalAmount(budgetRepo);
        assertEquals(610, totalAmount.findBetween("2016-02-10", "2017-08-15"), 0.001);
    }

    @Test
    public void totalAmountCrossYearInterrupt() {
        BudgetRepo budgetRepo = Mockito.mock(BudgetRepo.class);
        List<Budget> allBudgets = new ArrayList<>();
        allBudgets.add(new Budget("201606", 300));
        allBudgets.add(new Budget("201706", 300));
        allBudgets.add(new Budget("201707", 310));
        when(budgetRepo.findAll()).thenReturn(allBudgets);
        TotalAmount totalAmount = new TotalAmount(budgetRepo);
        assertEquals(910, totalAmount.findBetween("2016-02-10", "2017-08-15"), 0.001);
    }

    @Test
    public void totalAmountCrossYearPartial() {
        BudgetRepo budgetRepo = Mockito.mock(BudgetRepo.class);
        List<Budget> allBudgets = new ArrayList<>();
        allBudgets.add(new Budget("201602", 290));
        allBudgets.add(new Budget("201706", 300));
        allBudgets.add(new Budget("201708", 310));
        when(budgetRepo.findAll()).thenReturn(allBudgets);
        TotalAmount totalAmount = new TotalAmount(budgetRepo);
        assertEquals(580, totalAmount.findBetween("2016-02-17", "2017-08-15"), 0.001);
    }

}
