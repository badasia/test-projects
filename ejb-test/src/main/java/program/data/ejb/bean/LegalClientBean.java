package program.data.ejb.bean;

import org.hibernate.Session;
import program.HibernateSessionFactory;
import program.data.ejb.entity.LegalClientEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class LegalClientBean extends AbstractBean<LegalClientEntity> {

    public LegalClientBean() {
        super(LegalClientEntity.class);
    }

    public static LegalClientEntity findByFirmName(String firmName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        org.hibernate.Query query = session.getNamedQuery("LegalClientEntity.findByFirmName");
        query.setParameter("firmName", firmName);
        List<LegalClientEntity> resultList = query.list();
        session.close();
        if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

}
