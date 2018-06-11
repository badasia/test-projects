package program.data.ejb.bean;

import org.hibernate.Query;
import org.hibernate.Session;
import program.HibernateSessionFactory;
import program.data.ejb.entity.BankEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BankBean extends AbstractBean<BankEntity> {

    public BankBean() {
        super(BankEntity.class);
    }

    public static BankEntity findByName(String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.getNamedQuery("BankEntity.findByName");
        query.setParameter("name", name);
        List<BankEntity> resultList = query.list();
        session.close();
        if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
