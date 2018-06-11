package program;

import program.data.ejb.bean.BankBean;
import program.data.ejb.entity.BankAccountEntity;
import program.data.ejb.entity.BankEntity;
import program.data.ejb.entity.DebitCardEntity;

import java.util.List;

public class Bank {
    private BankEntity bankEntity;

    private Bank() {
    }

    private Integer availableMoney(DebitCardEntity debitCardEntity) {
        List<BankAccountEntity> bankAccounts = bankEntity.getBankAccounts();
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getAccountNumber().equals(debitCardEntity.getCardNumber())) {
                return bankAccounts.get(i).getCash();
            }
        }
        return null;
    }

    public static Bank createBank(BankEntity bankEntity) {
        BankEntity temp;
        if (bankEntity != null) {
            temp = BankBean.findByName(bankEntity.getName());
            if (temp != null &&
                    temp.equals(bankEntity)) {
                Bank bank = new Bank();
                bank.bankEntity = bankEntity;
                return bank;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
