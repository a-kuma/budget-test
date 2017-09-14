package budget;

import java.util.List;

public class BudgetRepo {

    private List<Budget> allBudgets;

    public BudgetRepo() {
        Budget budget6 = new Budget("201706", 310);
        Budget budget7 = new Budget("201707", 620);
        allBudgets.add(budget6);
        allBudgets.add(budget7);
    }

    public List<Budget> findAll() {
        return this.allBudgets;
    }

}
