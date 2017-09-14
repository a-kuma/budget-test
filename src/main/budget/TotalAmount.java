package budget;

import com.sun.deploy.util.StringUtils;
import javafx.util.Pair;
import utils.BaseUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TotalAmount {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    private BudgetRepo budgetRepo;

    public TotalAmount(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public void findBetweenNotUse(String startDay, String endDay) {
        try {
            List<Pair<String, Integer>> avaMonths = new ArrayList<>();
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(simpleDateFormat.parse(startDay));
            int startYear = startCal.get(Calendar.YEAR);
            int startMonth = startCal.get(Calendar.MONTH);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(simpleDateFormat.parse(endDay));
            int endYear = endCal.get(Calendar.YEAR);
            int endMonth = endCal.get(Calendar.MONTH);
            while (startMonth != endMonth && startYear != endYear) {
                avaMonths.add(new Pair<String, Integer>(simpleDateFormat.format(startCal.getTime()).substring(0, 6), BaseUtils.getDaysOfMonth(startCal.getTime())));
                startCal.add(Calendar.MONTH, 1);
                startYear = startCal.get(Calendar.YEAR);
                startMonth = startCal.get(Calendar.MONTH);
            }
            List<Budget> all = budgetRepo.findAll();
            List<Budget> result = new ArrayList<>();
            for (Budget budget : all) {
                if (startDay.startsWith(budget.getMonth()))
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void findBetween(String startDay, String endDay) {
        List<Pair<String, Integer>> avaMonths = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(startDay);
        LocalDate endDate = LocalDate.parse(startDay);
        Period period = Period.between(startDate, endDate);
        int startDayOfMonth = startDate.getDayOfMonth();
        int endDayOfMonth = startDate.getDayOfMonth();
        int month = period.getMonths();
        if (startDayOfMonth >= endDayOfMonth) {
            month = period.getMonths() + 1;
        }
        List<Budget> all = budgetRepo.findAll();
        List<Budget> result = new ArrayList<>();
        int i = 0;
        for (Budget budget : all) {
            if (startDay.startsWith(budget.getMonth())) {
                if (i == 0) {

                }
                avaMonths.add(budget.getMonth(), );
                i++;
            }
            if (i == month) {
                break;
            }
        }
    }



}
