package hu.bme.aut.programsch.config.db;


import hu.bme.aut.programsch.model.Circle;
import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DbConfigService {

    private final CircleRepository circleRepository;

    private final ResortRepository resortRepository;

    @Transactional
    public void injectCirclesIntoDb() {
        createResorts();
        createCircles();
    }

    private void createResorts() {
        if (resortRepository.findAll().isEmpty()) {
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
    }

    private void createCircles() {
        if (circleRepository.findAll().isEmpty()) {
            createBulisCircles();
            createEgyebCircles();
            createEnergetikaiSzakkoliCircles();
            createKoferCircles();
            createKSZKCircles();
            createKulturCircles();
            createSVKCircles();
            createSimonyiCircles();
            createSportCircles();
            createSSSLCircles();
            createSZORCircles();
        }
    }

    private void createSZORCircles() {
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

        Resort resort = resortRepository.findByName("Szolgáltató Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSSSLCircles() {
        circleRepository.save(new Circle("Szent Schönherz Senior Lovagrend", resortRepository.findByName("Szent Schönherz Senior Lovagrend")));

        Resort resort = resortRepository.findByName("Szent Schönherz Senior Lovagrend");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSportCircles() {
        circleRepository.save(new Circle("Általános szertár", resortRepository.findByName("Sport Reszort")));
        circleRepository.save(new Circle("DiákSportKör", resortRepository.findByName("Sport Reszort")));
        circleRepository.save(new Circle("Csocsó kör", resortRepository.findByName("Sport Reszort")));
        circleRepository.save(new Circle("Asztalitenisz", resortRepository.findByName("Sport Reszort")));

        Resort resort = resortRepository.findByName("Sport Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSimonyiCircles() {
        circleRepository.save(new Circle("KIR fejlesztők és üzemeltetők", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("Budavári Schönherz Stúdió", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("Simonyi Konferencia", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("Schönherz Design Stúdió", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("HA5KFU", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("AC Studio & Live", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("Schönherz Elektronikai Műhely", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("SPOT", resortRepository.findByName("Simonyi Károly Szakkollégium")));
        circleRepository.save(new Circle("Lego kör", resortRepository.findByName("Simonyi Károly Szakkollégium")));

        Resort resort = resortRepository.findByName("Simonyi Károly Szakkollégium");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSVKCircles() {
        circleRepository.save(new Circle("Schönherz Vállalati Kapcsolatok", resortRepository.findByName("Schönherz Vállalati Kapcsolatok")));

        Resort resort = resortRepository.findByName("Schönherz Vállalati Kapcsolatok");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createKulturCircles() {
        circleRepository.save(new Circle("La'Place Café", resortRepository.findByName("Kultúr Reszort")));
        circleRepository.save(new Circle("Játszóház", resortRepository.findByName("Kultúr Reszort")));
        circleRepository.save(new Circle("Impulzus", resortRepository.findByName("Kultúr Reszort")));
        circleRepository.save(new Circle("Bor Baráti Kör", resortRepository.findByName("Kultúr Reszort")));
        circleRepository.save(new Circle("Bűvész Kör", resortRepository.findByName("Kultúr Reszort")));
        circleRepository.save(new Circle("Local Heroes Szerepjátszó Kör", resortRepository.findByName("Kultúr Reszort")));

        Resort resort = resortRepository.findByName("Kultúr Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createKSZKCircles() {
        circleRepository.save(new Circle("Hallgatói Tudásbázis", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
        circleRepository.save(new Circle("SecurITeam", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
        circleRepository.save(new Circle("Sysadmin", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
        circleRepository.save(new Circle("NETeam", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));
        circleRepository.save(new Circle("DevTeam", resortRepository.findByName("Kollégiumi Számítástechnikai Kör")));

        Resort resort = resortRepository.findByName("Kollégiumi Számítástechnikai Kör");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createKoferCircles() {
        circleRepository.save(new Circle("Szintképviselők Tanácsa", resortRepository.findByName("Kollégiumi Felvételi és Érdekvédelmi Reszort")));
        circleRepository.save(new Circle("Kollégiumi Felvételi Bizottság", resortRepository.findByName("Kollégiumi Felvételi és Érdekvédelmi Reszort")));

        Resort resort = resortRepository.findByName("Kollégiumi Felvételi és Érdekvédelmi Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createEnergetikaiSzakkoliCircles() {
        circleRepository.save(new Circle("Energetikai Szakkollégium", resortRepository.findByName("Energetikai Szakkollégium")));

        Resort resort = resortRepository.findByName("Energetikai Szakkollégium");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createEgyebCircles() {
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

        Resort resort = resortRepository.findByName("Egyéb");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createBulisCircles() {
        circleRepository.save(new Circle("Dezső Buli", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("LevelUp", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("Just Dance", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("Parkett Klub", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("ClubCeption", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("Homár", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("VIK Szakestély", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("Lanosch", resortRepository.findByName("Bulis Reszort")));
        circleRepository.save(new Circle("Schörpong", resortRepository.findByName("Bulis Reszort")));

        Resort resort = resortRepository.findByName("Bulis Reszort");
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }
}
