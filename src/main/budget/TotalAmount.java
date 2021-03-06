package budget;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class TotalAmount {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    private BudgetRepo budgetRepo;

    public TotalAmount(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

//    public void findBetweenNotUse(String startDay, String endDay) {
//        try {
//            List<Pair<String, Integer>> avaMonths = new ArrayList<>();
//            Calendar startCal = Calendar.getInstance();
//            startCal.setTime(simpleDateFormat.parse(startDay));
//            int startYear = startCal.get(Calendar.YEAR);
//            int startMonth = startCal.get(Calendar.MONTH);
//            Calendar endCal = Calendar.getInstance();
//            endCal.setTime(simpleDateFormat.parse(endDay));
//            int endYear = endCal.get(Calendar.YEAR);
//            int endMonth = endCal.get(Calendar.MONTH);
//            while (startMonth != endMonth && startYear != endYear) {
//                avaMonths.add(new Pair<String, Integer>(simpleDateFormat.format(startCal.getTime()).substring(0, 6), BaseUtils.getDaysOfMonth(startCal.getTime())));
//                startCal.add(Calendar.MONTH, 1);
//                startYear = startCal.get(Calendar.YEAR);
//                startMonth = startCal.get(Calendar.MONTH);
//            }
//            List<Budget> all = budgetRepo.findAll();
//            List<Budget> result = new ArrayList<>();
//            for (Budget budget : all) {
//                if (startDay.startsWith(budget.getMonth()))
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    public double findBetween(String startDay, String endDay) {
        LocalDate startDate = LocalDate.parse(startDay);
        LocalDate endDate = LocalDate.parse(endDay);
        Period period = Period.between(startDate, endDate);
        int startDayOfMonth = startDate.getDayOfMonth();
        int endDayOfMonth = endDate.getDayOfMonth();
        int month = period.getYears() * 12 + period.getMonths() + 1;
        if (startDayOfMonth >= endDayOfMonth) {
            month = month + 1;
        }
        List<Budget> resultBudgets = new ArrayList<>();
        LocalDate tmpStartDate = LocalDate.parse(startDay);
        for (int i = 0; i < month; i++) {
            Budget budget = getAmonut(tmpStartDate);
            if (budget != null) {
                resultBudgets.add(budget);
            } else {
                Budget empty = new Budget();
                resultBudgets.add(empty);
            }
            tmpStartDate = tmpStartDate.plusMonths(1);
        }
        if (resultBudgets.size() == 1) {
            return (endDate.getDayOfMonth() - startDate.getDayOfMonth() + 1) * resultBudgets.get(0).getAmount() / startDate.lengthOfMonth();
        }
        double totalAmount = 0;
        int j = 0;
        for (Budget bdt : resultBudgets) {
            j++;
            double amount;
            if (j == 1) {
                amount = (startDate.lengthOfMonth() - startDate.getDayOfMonth() + 1) * bdt.getAmount() / startDate.lengthOfMonth();
            } else if (j == resultBudgets.size()) {
                amount = endDate.getDayOfMonth() * bdt.getAmount() / endDate.lengthOfMonth();
            } else {
                amount = bdt.getAmount();
            }
            totalAmount += amount;
            if (j == resultBudgets.size()) {
                break;
            }
        }
        return totalAmount;
    }

    private Budget getAmonut(LocalDate date) {
        List<Budget> all = budgetRepo.findAll();
        for (Budget budget : all) {
            String yearmonth = date.getYear() + "" + String.format("%02d", date.getMonth().getValue());
            if (budget.getMonth().equals(yearmonth)) {
                return budget;
            }
        }
        return null;
    }


}
