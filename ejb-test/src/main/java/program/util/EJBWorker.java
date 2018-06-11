package program.util;

import javax.naming.Context;
import javax.naming.InitialContext;

public final class EJBWorker {

    private EJBWorker() {
    }

    public static Object lookupBean(String simpleName, String fullName) {
        try {
            Context context = new InitialContext();
            return context.lookup("java:global/webapp/" + simpleName + "!" + fullName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
