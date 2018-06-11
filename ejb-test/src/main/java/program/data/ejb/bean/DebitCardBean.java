package program.data.ejb.bean;

import org.hibernate.Query;
import org.hibernate.Session;
import program.HibernateSessionFactory;
import program.data.ejb.entity.DebitCardEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DebitCardBean extends AbstractBean<DebitCardEntity> {

    public DebitCardBean() {
        super(DebitCardEntity.class);
    }

    public static DebitCardEntity findByCardNumber(String cardNumber) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.getNamedQuery("DebitCardEntity.findByCardNumber");
        query.setParameter("cardNumber", cardNumber);
        List<DebitCardEntity> resultList = query.list();
        session.close();
        if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
