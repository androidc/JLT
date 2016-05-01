package ishop;

import java.sql.Connection;
import java.util.ArrayList;

public interface Loadable {
    
    public ArrayList loadData(Connection connection);
}
