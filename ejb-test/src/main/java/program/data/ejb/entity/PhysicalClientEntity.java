package program.data.ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "physical_client",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
@NamedQueries({
        @NamedQuery(name = "PhysicalClientEntity.findByFullName", query = "" +
                "SELECT entity " +
                "  FROM PhysicalClientEntity entity " +
                " WHERE entity.surname = :surname" +
                "   AND entity.name = :name" +
                "   AND entity.patronymic = :patronymic")})
public class PhysicalClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @ManyToMany(targetEntity = BankAccountEntity.class)
    private Set<BankAccountEntity> bankAccounts = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank", nullable = false)
    private BankEntity bank;

    public PhysicalClientEntity() {
    }

    public PhysicalClientEntity(String surname, String name, String patronymic, Set<BankAccountEntity> bankAccounts, BankEntity bank) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.bankAccounts = bankAccounts;
        this.bank = bank;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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