package program.data.ejb.bean;

import org.hibernate.Session;
import program.HibernateSessionFactory;
import program.data.ejb.entity.CashMachineEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CashMachineBean extends AbstractBean<CashMachineEntity> {

    public CashMachineBean() {
        super(CashMachineEntity.class);
    }

    public static CashMachineEntity findByInventoryCode(String inventoryCode) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        org.hibernate.Query query = session.getNamedQuery("CashMachineEntity.findByInventoryCode");
        query.setParameter("inventoryCode", inventoryCode);
        List<CashMachineEntity> resultList = query.list();
        session.close();
        if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
