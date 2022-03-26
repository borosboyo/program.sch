package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.CircleEntity;
import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.OpeningRepository;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircleService {

    private ResortRepository resortRepository;

    private CircleRepository circleRepository;

    private OpeningRepository openingRepository;

    @Transactional
    public Page<CircleEntity> findAll(Pageable pageable) {
        return circleRepository.findAll(pageable);
    }

    @Transactional
    public List<CircleEntity> findAll() {
        return circleRepository.findAll();
    }

    @Transactional
    public void save(CircleEntity circleEntity) {
        circleRepository.save(circleEntity);
    }

    @Transactional
    public CircleEntity findByVirGroupId(Long id) {
        return circleRepository.findOneByVirGroupId(id);
    }

    @Transactional
    public void injectCirclesIntoDb() {
        if(resortRepository.findAll().isEmpty()){
            resortRepository.save(new Resort("Bulis Reszort"));
            resortRepository.save(new Resort("Egyéb"));
            resortRepository.save(new Resort("Energetikai Szakkollégium"));
            resortRepository.save(new Resort("Kollégiumi Felvételi és Érdekvédelmi Reszort"));
            resortRepository.save(new Resort("Kollégiumi Számítástechnikai kör"));
            resortRepository.save(new Resort("Kultúr Reszort"));
            resortRepository.save(new Resort("Schönherz Vállalati Kapcsolatok"));
            resortRepository.save(new Resort("Simonyi Károly Szakkollégium"));
            resortRepository.save(new Resort("Sport Reszort"));
            resortRepository.save(new Resort("Szent Schönherz Senior Lovagrend"));
            resortRepository.save(new Resort("Szolgáltató Reszort"));
        }
        if(circleRepository.findAll().isEmpty()) {
            //Sport
            circleRepository.save(new CircleEntity("Általános szertár", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new CircleEntity("DiákSportKör", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new CircleEntity("Csocsó kör", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new CircleEntity("Asztalitenisz", resortRepository.findByName("Bulis Reszort")));
            //SSSL
            circleRepository.save(new CircleEntity("Szent Schönherz Senior Lovagrend", resortRepository.findByName("Szent Schönherz Senior Lovagrend")));
            //SZOR
            circleRepository.save(new CircleEntity("Vödörkör", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("FoodEx", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Pulcsi és Foltmékör", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Szauna kör", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Dzsájrosz", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Vörös Kakas Fogadó", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("LángoSCH", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Edénykölcsönző", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Palacsintázó", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Americano", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("WTF", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new CircleEntity("Pizzásch", resortRepository.findByName("Szolgáltató Reszort")));
        }
    }

}
