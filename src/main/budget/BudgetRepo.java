package budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetRepo {

    private List<Budget> allBudgets = new ArrayList<>();

    public BudgetRepo() {
        Budget budget6 = new Budget("201706", 300);
        Budget budget7 = new Budget("201707", 310);
        allBudgets.add(budget6);
        allBudgets.add(budget7);
    }

    public List<Budget> findAll() {
        return this.allBudgets;
    }

}
