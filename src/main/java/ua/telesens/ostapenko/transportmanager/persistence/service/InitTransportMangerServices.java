package ua.telesens.ostapenko.transportmanager.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.telesens.ostapenko.transportmanager.persistence.model.Route;
import ua.telesens.ostapenko.transportmanager.persistence.model.Station;
import ua.telesens.ostapenko.transportmanager.persistence.repository.RouteRepository;
import ua.telesens.ostapenko.transportmanager.persistence.repository.StationRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.*;

/**
 * @author root
 * @since 28.01.16
 */
@Transactional
@Service
public class InitTransportMangerServices {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationRepository stationRepository;

    @PostConstruct
    public void init() {
        String[] names = new String[]{
                "ст.м.Имени А.С.Масельського",
                "завод Южкабель",
                "ул.Лодзинская",
                "пер.Енакиевский",
                "Автобаза",
                "проезд Велозаводский",
                "ЖБК - 9",
                "ст.м.Тракторный завод",
                "ул. 2 - й Пятилетки",
                "пр.Косиора",
                "ул.Тухачевского",
                "пр.Орджоникидзе",
                "ул.Пирогова",
                "пр.Фрунзе",
                "ул.Краснопартизанская",
                "ул.Сусанина",
                "ул.Днестровская",
                "602-й микрорайон",
        };
        List<Station> stations = new ArrayList<>();
        int count = names.length;
        for (int i = 0; i < count - 1; i++) {
            Station station = new Station(names[i], randInt(50, 999));
            stations.add(station);
            stationRepository.save(station);
        }

        Random random = new Random();
        for (int i = 0; i < stations.size(); i++) {
            String number = String.valueOf(i + random.nextInt(300));
            if (Objects.isNull(routeRepository.findByNumber(number))) {
                Route route = Route.builder()
                        .number(number)
                        .circular(random.nextBoolean())
                        .starting(LocalTime.of(6, 0))
                        .ending(LocalTime.of(20, 0))
                        .duration(LocalTime.of(0, randInt(5, 50)))
                        .price(5.10)
                        .races(randInt(3, 20))
                        .avgTransport(randInt(200, 1000))
                        .stations(getStationList(stations))
                        .build();
                routeRepository.save(route);
            }
        }
    }

    private List<Station> getStationList(List<Station> stations) {
        List<Station> buff = new ArrayList<>(stations);
        Collections.shuffle(buff);
        int size = randInt(3, 8);
        List<Station> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(buff.get(i));
        }
        return result;
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        return rand.nextInt((max - min) + 1) + min;
    }
}
