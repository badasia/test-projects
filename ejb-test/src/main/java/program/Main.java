package program;

import program.data.ejb.bean.BankBean;
import program.data.ejb.bean.CashMachineBean;

public class Main {

    public static void main(String[] args) {
//        DBCreateTestInfo.createInfo();
        Bank bank = Bank.createBank(BankBean.findByName("Сбербанк"));

        CashMachine cashMachine = CashMachine.createCashMachine(CashMachineBean.findByInventoryCode("CASH_MACHINE_01"));
        cashMachine.insertCard("5450904080503540");
    }
}
