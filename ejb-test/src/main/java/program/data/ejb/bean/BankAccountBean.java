package program.data.ejb.bean;

import org.hibernate.Session;
import program.HibernateSessionFactory;
import program.data.ejb.entity.BankAccountEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BankAccountBean extends AbstractBean<BankAccountEntity> {

    public BankAccountBean() {
        super(BankAccountEntity.class);
    }

    public static BankAccountEntity findByAccountNumber(String accountNumber) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        org.hibernate.Query query = session.getNamedQuery("BankAccountEntity.findByAccountNumber");
        query.setParameter("accountNumber", accountNumber);
        List<BankAccountEntity> resultList = query.list();
        session.close();
        if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

}
