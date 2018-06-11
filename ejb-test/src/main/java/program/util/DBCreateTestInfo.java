package program.util;

import program.data.ejb.bean.*;
import program.data.ejb.entity.*;

public final class DBCreateTestInfo {

    public static void createInfo() {
        bank();
        cashMachine();
        physicalClient();
        physicalClientAccount();
        legalClient();
        legalClientAccount();
        debitCard();
    }

    private static void bank() {
        try {
            BankBean bankBean = new BankBean();

            BankEntity bankEntity = new BankEntity();
            bankEntity.setName("Сбербанк");

            bankBean.create(bankEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cashMachine() {
        try {
            CashMachineBean cashMachineBean = new CashMachineBean();

            CashMachineEntity cashMachine = new CashMachineEntity();
            cashMachine.setBank(BankBean.findByName("Сбербанк"));
            cashMachine.setInventoryCode("CASH_MACHINE_01");
            cashMachine.setCountBills100(100);
            cashMachine.setCountBills500(100);
            cashMachine.setCountBills1000(100);
            cashMachine.setCountBills5000(100);

            cashMachineBean.create(cashMachine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void physicalClient() {
        try {
            PhysicalClientBean physicalClientBean = new PhysicalClientBean();

            PhysicalClientEntity physicalClientEntity = new PhysicalClientEntity();
            physicalClientEntity.setSurname("Фамилия");
            physicalClientEntity.setName("Имя");
            physicalClientEntity.setPatronymic("Отчество");
            physicalClientEntity.setBank(BankBean.findByName("Сбербанк"));

            physicalClientBean.create(physicalClientEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void physicalClientAccount() {
        try {
            BankAccountEntity bankAccountEntity = new BankAccountEntity();
            bankAccountEntity.setAccountNumber("12345678");
            bankAccountEntity.setBank(BankBean.findByName("Сбербанк"));
            bankAccountEntity.setCash(5000);

            BankAccountBean bankAccountBean = new BankAccountBean();
            bankAccountBean.create(bankAccountEntity);

            PhysicalClientEntity physicalClientEntity = PhysicalClientBean.findByFullName("Фамилия", "Имя", "Отчество");
            physicalClientEntity.getBankAccounts().add(BankAccountBean.findByAccountNumber("12345678"));

            PhysicalClientBean physicalClientBean = new PhysicalClientBean();
            physicalClientBean.edit(physicalClientEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void legalClient() {
        try {
            LegalClientBean legalClientBean = new LegalClientBean();
            LegalClientEntity physicalClientEntity = new LegalClientEntity();
            physicalClientEntity.setFirmName("ООО КомфортЦентр");
            physicalClientEntity.setBank(BankBean.findByName("Сбербанк"));
            legalClientBean.create(physicalClientEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void legalClientAccount() {
        try {
            LegalClientBean legalClientBean = new LegalClientBean();
            LegalClientEntity legalClientEntity = legalClientBean.findByFirmName("ООО КомфортЦентр");
            BankAccountEntity bankAccountEntity = new BankAccountEntity();
            bankAccountEntity.setAccountNumber("12345679");
            bankAccountEntity.setBank(BankBean.findByName("Сбербанк"));
            bankAccountEntity.setCash(50000);
            BankAccountBean bankAccountBean = new BankAccountBean();
            bankAccountBean.create(bankAccountEntity);

            legalClientEntity.getBankAccounts().add(BankAccountBean.findByAccountNumber("12345679"));
            legalClientBean.edit(legalClientEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void debitCard() {
        try {
            DebitCardBean debitCardBean = new DebitCardBean();
            DebitCardEntity debitCardEntity = new DebitCardEntity();
            debitCardEntity.setBankAccount(BankAccountBean.findByAccountNumber("12345678"));
            debitCardEntity.setBank(BankBean.findByName("Сбербанк"));
            debitCardEntity.setCardNumber("5450904080503540");
            debitCardEntity.setPinCode("5246");
            debitCardBean.create(debitCardEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
