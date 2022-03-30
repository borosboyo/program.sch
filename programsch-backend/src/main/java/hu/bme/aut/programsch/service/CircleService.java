package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.Circle;
import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.EventRepository;
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

    private final ResortRepository resortRepository;

    private final CircleRepository circleRepository;

    @Transactional
    public Page<Circle> findAll(Pageable pageable) {
        return circleRepository.findAll(pageable);
    }

    @Transactional
    public List<Circle> findAll() {
        injectCirclesIntoDb();
        return circleRepository.findAll();
    }

    @Transactional
    public void save(Circle circleEntity) {
        circleRepository.save(circleEntity);
    }

    @Transactional
    public Circle findByVirGroupId(Long id) {
        return circleRepository.findOneByVirGroupId(id);
    }


    @Transactional
    public void injectCirclesIntoDb() {
        if(resortRepository.findAll().isEmpty()){
            resortRepository.save(new Resort("Bulis Reszort"));
            resortRepository.save(new Resort("Egyéb"));
            resortRepository.save(new Resort("Energetikai Szakkollégium"));
            resortRepository.save(new Resort("Kollégiumi Felvételi és Érdekvédelmi Reszort"));
            resortRepository.save(new Resort("Kollégiumi Számítástechnikai Kör"));
            resortRepository.save(new Resort("Kultúr Reszort"));
            resortRepository.save(new Resort("Schönherz Vállalati Kapcsolatok"));
            resortRepository.save(new Resort("Simonyi Károly Szakkollégium"));
            resortRepository.save(new Resort("Sport Reszort"));
            resortRepository.save(new Resort("Szent Schönherz Senior Lovagrend"));
            resortRepository.save(new Resort("Szolgáltató Reszort"));
        }

        if(circleRepository.findAll().isEmpty()) {

            //Bulis
            circleRepository.save(new Circle("Dezső Buli", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("LevelUp", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Just Dance", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Parkett Klub", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("ClubCeption", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Homár", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("VIK Szakestély", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Lanosch", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Schörpong", resortRepository.findByName("Bulis Reszort")));

            //Egyéb
            circleRepository.save(new Circle("KB-Ref gárda", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("KB PR Csoport", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Kari Hallgatói Képviselet", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Felező bál", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("HK Munkacsoportok", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Csillagtúra", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Reszortvezetők Tanácsa", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Kollégiumi Bizottság", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Schönherz Qpa", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("SVIE", resortRepository.findByName("Egyéb")));
            circleRepository.save(new Circle("Egyéb", resortRepository.findByName("Egyéb")));

            //Energetikai Szakkollégium
            circleRepository.save(new Circle("Energetikai Szakkollégium", resortRepository.findByName("Energetikai Szakkollégium")));

            //Kollégiumi Felvételi és Érdekvédelmi Reszort
            circleRepository.save(new Circle("Szintképviselők Tanácsa", resortRepository.findByName("Kollégiumi Felvételi és Érdekvédelmi Reszort")));
            circleRepository.save(new Circle("Kollégiumi Felvételi Bizottság", resortRepository.findByName("Kollégiumi Felvételi és Érdekvédelmi Reszort")));

            //Kollégiumi Számítástechnikai Kör
            circleRepository.save(new Circle("Hallgatói Tudásbázis", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
            circleRepository.save(new Circle("SecurITeam", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
            circleRepository.save(new Circle("Sysadmin", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
            circleRepository.save(new Circle("NETeam", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
            circleRepository.save(new Circle("DevTeam", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));

            //Kultúr
            circleRepository.save(new Circle("La'Place Café", resortRepository.findByName("Kultúr Reszort")));
            circleRepository.save(new Circle("Játszóház", resortRepository.findByName("Kultúr Reszort")));
            circleRepository.save(new Circle("Impulzus", resortRepository.findByName("Kultúr Reszort")));
            circleRepository.save(new Circle("Bor Baráti Kör", resortRepository.findByName("Kultúr Reszort")));
            circleRepository.save(new Circle("Bűvész Kör", resortRepository.findByName("Kultúr Reszort")));
            circleRepository.save(new Circle("Local Heroes Szerepjátszó Kör", resortRepository.findByName("Kultúr Reszort")));

            //SVK
            circleRepository.save(new Circle("Schönherz Vállalati Kapcsolatok", resortRepository.findByName("Schönherz Vállalati Kapcsolatok")));

            //Simonyi
            circleRepository.save(new Circle("KIR fejlesztők és üzemeltetők", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("Budavári Schönherz Stúdió", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("Simonyi Konferencia", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("Schönherz Design Stúdió", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("HA5KFU", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("AC Studio & Live", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("Schönherz Elektronikai Műhely", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("SPOT", resortRepository.findByName("Simonyi Károly Szakkollégium")));
            circleRepository.save(new Circle("Lego kör", resortRepository.findByName("Simonyi Károly Szakkollégium")));

            //Sport
            circleRepository.save(new Circle("Általános szertár", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("DiákSportKör", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Csocsó kör", resortRepository.findByName("Bulis Reszort")));
            circleRepository.save(new Circle("Asztalitenisz", resortRepository.findByName("Bulis Reszort")));

            //SSSL
            circleRepository.save(new Circle("Szent Schönherz Senior Lovagrend", resortRepository.findByName("Szent Schönherz Senior Lovagrend")));

            //SZOR
            circleRepository.save(new Circle("Vödörkör", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("FoodEx", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Pulcsi és Foltmékör", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Szauna kör", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Dzsájrosz", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Vörös Kakas Fogadó", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("LángoSCH", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Edénykölcsönző", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Palacsintázó", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Americano", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("WTF", resortRepository.findByName("Szolgáltató Reszort")));
            circleRepository.save(new Circle("Pizzásch", resortRepository.findByName("Szolgáltató Reszort")));

        }


        Resort resort = resortRepository.findByName("Bulis Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Egyéb");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Energetikai Szakkollégium");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Kollégiumi Felvételi és Érdekvédelmi Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Kollégiumi Számítástechnikai kör");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Kultúr Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Schönherz Vállalati Kapcsolatok");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Simonyi Károly Szakkollégium");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Sport Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Szent Schönherz Senior Lovagrend");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);

        resort = resortRepository.findByName("Szolgáltató Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
    }

}
