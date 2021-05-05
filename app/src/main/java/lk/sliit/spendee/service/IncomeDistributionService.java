package lk.sliit.spendee.service;

import lk.sliit.spendee.model.SettingModel;

/**
 * author: Lasith Hansana
 * date: 5/6/2021
 * time: 12:57 AM
 */
public class IncomeDistributionService {

    private double income;
    private SettingModel settingModel;

    public IncomeDistributionService(double income, SettingModel settingModel) {
        this.income = income;
        this.settingModel = settingModel;
    }

    public double investmentAmount() {
        return (settingModel.getInvestmentRate() / 100) * income;
    }
    public double expensesAmount() {
        return (settingModel.getExpenseRate() / 100) * income;
    }
    public double goalAmount() {
        return (settingModel.getGoalRate() / 100) * income;
    }
    public double savingAmount() {
        return (settingModel.getSavingRate() / 100) * income;
    }
}
