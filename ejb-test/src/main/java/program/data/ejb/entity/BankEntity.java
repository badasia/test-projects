package program.data.ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "bank",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "name"}))
@NamedQueries({
        @NamedQuery(name = "BankEntity.findByName", query = "" +
                "SELECT entity " +
                "  FROM BankEntity entity " +
                " WHERE entity.name = :name")})
public class BankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<CashMachineEntity> cashMachines;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<BankAccountEntity> bankAccounts;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<DebitCardEntity> debitCards;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<LegalClientEntity> legalClients;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<PhysicalClientEntity> physicalClients;

    public BankEntity() {
    }

    public BankEntity(String name, List<CashMachineEntity> cashMachines, List<BankAccountEntity> bankAccounts, List<DebitCardEntity> debitCards, List<LegalClientEntity> legalClients, List<PhysicalClientEntity> physicalClients) {
        this.name = name;
        this.cashMachines = cashMachines;
        this.bankAccounts = bankAccounts;
        this.debitCards = debitCards;
        this.legalClients = legalClients;
        this.physicalClients = physicalClients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CashMachineEntity> getCashMachines() {
        return cashMachines;
    }

    public void setCashMachines(List<CashMachineEntity> cashMachines) {
        this.cashMachines = cashMachines;
    }

    public List<BankAccountEntity> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountEntity> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<DebitCardEntity> getDebitCards() {
        return debitCards;
    }

    public void setDebitCards(List<DebitCardEntity> debitCards) {
        this.debitCards = debitCards;
    }

    public List<LegalClientEntity> getLegalClients() {
        return legalClients;
    }

    public void setLegalClients(List<LegalClientEntity> legalClients) {
        this.legalClients = legalClients;
    }

    public List<PhysicalClientEntity> getPhysicalClients() {
        return physicalClients;
    }

    public void setPhysicalClients(List<PhysicalClientEntity> physicalClients) {
        this.physicalClients = physicalClients;
    }

}