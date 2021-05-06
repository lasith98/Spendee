package lk.sliit.spendee.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import lk.sliit.spendee.model.SettingModel;

import static org.hamcrest.core.Is.is;


/**
 * author: Lasith Hansana
 * date: 5/6/2021
 * time: 02:57 AM
 */
public class IncomeDistributionServiceTest {

    private IncomeDistributionService incomeDistributionService;
    private final double INCOME_AMOUNT = 500;
    private SettingModel settingModel;

    @Before
    public void setUp() throws Exception {

        settingModel = new SettingModel();
        settingModel.setExpenseRate(30);
        settingModel.setGoalRate(20);
        settingModel.setInvestmentRate(20);
        settingModel.setSavingRate(30);

        incomeDistributionService = new IncomeDistributionService(INCOME_AMOUNT, settingModel);
    }

    @Test
    public void Should_GalAmount_Return_100_When_Income_500_AND_Rate_20() {
        Assert.assertThat(100.0, is(incomeDistributionService.goalAmount()));
    }

    @Test
    public void Should_InvestmentAmount_Return_150_When_Income_500_AND_Rate_20() {
        Assert.assertThat(100.0, is(incomeDistributionService.investmentAmount()));
    }

    @Test
    public void Should_ExpensesAmount_Return_150_When_Income_500_AND_Rate_30() {
        Assert.assertThat(150.0, is(incomeDistributionService.expensesAmount()));
    }

    @Test
    public void Should_SavingAmount_Return_100_When_Income_500_AND_Rate_30() {
        Assert.assertThat(150.0, is(incomeDistributionService.savingAmount()));
    }


}