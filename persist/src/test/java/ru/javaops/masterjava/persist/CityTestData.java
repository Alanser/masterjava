package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableMap;
import ru.javaops.masterjava.persist.dao.CityDao;
import ru.javaops.masterjava.persist.model.City;

import java.util.Map;

public class CityTestData {
    public static final City KIEV = new City("kiv", "Киев");
    public static final City SPB = new City("spb", "Санкт-Петербург");
    public static final City MOSCOW = new City("mow", "Москва");
    public static final City MINSK = new City("mnsk", "Минск");

    public static final Map<String, City> CITIES = ImmutableMap.of(
            KIEV.getRef(), KIEV,
            MINSK.getRef(), MINSK,
            MOSCOW.getRef(), MOSCOW,
            SPB.getRef(), SPB);

    public static void setUp() {
        CityDao cityDao = DBIProvider.getDao(CityDao.class);
        cityDao.clean();
        DBIProvider.getDBI().useTransaction((conn, status) -> {
            CITIES.values().forEach(cityDao::insert);
        });
    }
}
