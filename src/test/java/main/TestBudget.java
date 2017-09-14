package main;

import budget.BudgetRepo;
import budget.TotalAmount;
import org.junit.Test;

public class TestBudget {

    @Test
    public void totalAmount() {
        TotalAmount totalAmount = new TotalAmount(new BudgetRepo());
        System.out.println(totalAmount.findBetween("2017-06-10", "2017-07-15"));
    }

}
