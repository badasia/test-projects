package program.data.ejb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cash_machine",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "inventory_code"}))
@NamedQueries({
        @NamedQuery(name = "CashMachineEntity.findByInventoryCode", query = "" +
                "SELECT entity " +
                "  FROM CashMachineEntity entity " +
                " WHERE entity.inventoryCode = :inventoryCode")})
public class CashMachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull
    @Column(name = "inventory_code", nullable = false, unique = true)
    private String inventoryCode;

    @NotNull
    @Column(name = "count_bills_100", nullable = false)
    private int countBills100;

    @NotNull
    @Column(name = "count_bills_500", nullable = false)
    private int countBills500;

    @NotNull
    @Column(name = "count_bills_1000", nullable = false)
    private int countBills1000;

    @NotNull
    @Column(name = "count_bills_5000", nullable = false)
    private int countBills5000;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank", nullable = false)
    private BankEntity bank;

    public CashMachineEntity() {
    }

    public CashMachineEntity(String inventoryCode, int countBills100, int countBills500, int countBills1000, int countBills5000, BankEntity bank) {
        this.inventoryCode = inventoryCode;
        this.countBills100 = countBills100;
        this.countBills500 = countBills500;
        this.countBills1000 = countBills1000;
        this.countBills5000 = countBills5000;
        this.bank = bank;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public int getCountBills100() {
        return countBills100;
    }

    public void setCountBills100(int countBills100) {
        this.countBills100 = countBills100;
    }

    public int getCountBills500() {
        return countBills500;
    }

    public void setCountBills500(int countBills500) {
        this.countBills500 = countBills500;
    }

    public int getCountBills1000() {
        return countBills1000;
    }

    public void setCountBills1000(int countBills1000) {
        this.countBills1000 = countBills1000;
    }

    public int getCountBills5000() {
        return countBills5000;
    }

    public void setCountBills5000(int countBills5000) {
        this.countBills5000 = countBills5000;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object obj) {
        CashMachineEntity cashMachineEntity = (CashMachineEntity) obj;
        if (cashMachineEntity.getId() == id &&
                cashMachineEntity.getInventoryCode().equals(inventoryCode) &&
                cashMachineEntity.getCountBills100() == countBills100 &&
                cashMachineEntity.getCountBills500() == countBills500 &&
                cashMachineEntity.getCountBills1000() == countBills1000 &&
                cashMachineEntity.getCountBills5000() == countBills5000 &&
                cashMachineEntity.getBank().getId() == bank.getId() &&
                cashMachineEntity.getBank().getName().equals(bank.getName())) {
            return true;
        } else {
            return false;
        }
    }
}