package ifelse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 2020
 */
public class FactoryHandlers {
    static final ConcurrentHashMap<String,PeopleMethodInterface> map = new ConcurrentHashMap<>();
    static{
        map.put("man",new Man());
        map.put("woman",new Woman());
    }

    public static ConcurrentHashMap getMap() {
        return map;
    }
    static PeopleMethodInterface getPeopleMethodInterfaceObject(String param){
        return  map.get(param);
    }
}
