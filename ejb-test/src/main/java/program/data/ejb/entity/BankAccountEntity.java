package program.data.ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "bank_account",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "account_number"}))
@NamedQueries({
        @NamedQuery(name = "BankAccountEntity.findByAccountNumber", query = "" +
                "SELECT entity " +
                "  FROM BankAccountEntity entity " +
                " WHERE entity.accountNumber = :accountNumber")})
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @NotNull
    @Column(name = "cash", nullable = false)
    private int cash;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank", nullable = false)
    private BankEntity bank;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<DebitCardEntity> debitCards;

    public BankAccountEntity() {
    }

    public BankAccountEntity(String accountNumber, int cash, BankEntity bank, List<DebitCardEntity> debitCards) {
        this.accountNumber = accountNumber;
        this.cash = cash;
        this.bank = bank;
        this.debitCards = debitCards;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }

    public List<DebitCardEntity> getDebitCards() {
        return debitCards;
    }

    public void setDebitCards(List<DebitCardEntity> debitCards) {
        this.debitCards = debitCards;
    }

}