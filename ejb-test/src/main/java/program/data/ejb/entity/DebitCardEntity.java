package program.data.ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "debit_card",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "card_number"}))
@NamedQueries({
        @NamedQuery(name = "DebitCardEntity.findByCardNumber", query = "" +
                "SELECT entity " +
                "  FROM DebitCardEntity entity " +
                " WHERE entity.cardNumber = :cardNumber")})
public class DebitCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @NotNull
    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank_account", nullable = false)
    private BankAccountEntity bankAccount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank", nullable = false)
    private BankEntity bank;

    public DebitCardEntity() {
    }

    public DebitCardEntity(String cardNumber, String pinCode, BankAccountEntity bankAccount, BankEntity bank) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.bankAccount = bankAccount;
        this.bank = bank;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String card_number) {
        this.cardNumber = card_number;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public BankAccountEntity getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountEntity bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }
}