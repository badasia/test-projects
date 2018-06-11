package program.data.ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "legal_client",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "firm_name"}))
@NamedQueries({
        @NamedQuery(name = "LegalClientEntity.findByFirmName", query = "" +
                "SELECT entity " +
                "  FROM LegalClientEntity entity " +
                " WHERE entity.firmName = :firmName")})
public class LegalClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull
    @Column(name = "firm_name", nullable = false, unique = true)
    private String firmName;

    @ManyToMany(targetEntity = BankAccountEntity.class)
    private Set<BankAccountEntity> bankAccounts = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank", nullable = false)
    private BankEntity bank;

    public LegalClientEntity() {
    }

    public LegalClientEntity(String firmName, Set<BankAccountEntity> bankAccounts, BankEntity bank) {
        this.firmName = firmName;
        this.bankAccounts = bankAccounts;
        this.bank = bank;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public Set<BankAccountEntity> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccountEntity> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }
}