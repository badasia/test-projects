package program;

import program.data.ejb.bean.CashMachineBean;
import program.data.ejb.bean.DebitCardBean;
import program.data.ejb.entity.CashMachineEntity;
import program.data.ejb.entity.DebitCardEntity;

public class CashMachine {
    private CashMachineEntity cashMachineEntity;
    private DebitCardEntity debitCardEntity;
    private boolean isActiveSession = false;
    private String cardNumber;
    private String pinCode;

    private CashMachine() {
    }

    public void insertCard(String cardNumber) {
        this.cardNumber = cardNumber;
        debitCardEntity = DebitCardBean.findByCardNumber(cardNumber);
        isActiveSession = true;
    }

    public void pullOutCard() {
        debitCardEntity = null;
        cardNumber = null;
        pinCode = null;
        isActiveSession = false;
    }

    public void enterPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    private boolean checkCardNumber() {
        if (debitCardEntity != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPinCode() {
        if (debitCardEntity != null &&
                pinCode != null &&
                debitCardEntity.getPinCode().equals(pinCode)) {
            isActiveSession = true;
            return true;
        } else {
            return false;
        }
    }

//    private int availableMoney(){
//        BankEntity bankEntity = cashMachineEntity.getBank();
//        List<BankAccountEntity> bankAccounts = bankEntity.getBankAccounts();
//
//        BankAccountEntity bankAccountEntity = BankAccountBean.findByAccountNumber()
//        if ()
//    }

    public static CashMachine createCashMachine(CashMachineEntity cashMachineEntity) {
        CashMachineEntity temp;
        if (cashMachineEntity != null) {
            temp = CashMachineBean.findByInventoryCode(cashMachineEntity.getInventoryCode());
            if (temp != null &&
                    temp.equals(cashMachineEntity)) {
                CashMachine cashMachine = new CashMachine();
                cashMachine.cashMachineEntity = cashMachineEntity;
                return cashMachine;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean isActiveSession() {
        return isActiveSession;
    }

    public DebitCardEntity getDebitCardEntity() {
        return debitCardEntity;
    }

    public CashMachineEntity getCashMachineEntity() {
        return cashMachineEntity;
    }
}
