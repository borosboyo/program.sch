package hu.bme.aut.programsch.config.db;


import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.domain.Resort;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DbConfigService {

    private final CircleRepository circleRepository;

    private final ResortRepository resortRepository;

    private static final String BULIS_RESORT = "Bulis Reszort";
    private static final String EGYEB_RESORT = "Egyéb";
    private static final String ENERGETIC_RESORT = "Energetikai Szakkollégium";
    private static final String KOFER_RESORT = "Kollégiumi Felvételi és Érdekvédelmi Reszort";
    private static final String KSZK_RESORT = "Kollégiumi Számítástechnikai Kör";
    private static final String KULTUR_RESORT = "Kultúr Reszort";
    private static final String SVK_RESORT = "Schönherz Vállalati Kapcsolatok";
    private static final String SIMONYI_RESORT = "Simonyi Károly Szakkollégium";
    private static final String SPORT_RESORT = "Sport Reszort";
    private static final String SSSL_RESORT = "Szent Schönherz Senior Lovagrend";
    private static final String SZOR_RESORT = "Szolgáltató Reszort";

    @Transactional
    public void injectCirclesIntoDb() {
        createResorts();
        createCircles();
    }

    private void createResorts() {
        if (resortRepository.findAll().isEmpty()) {
            resortRepository.save(new Resort(BULIS_RESORT));
            resortRepository.save(new Resort(EGYEB_RESORT));
            resortRepository.save(new Resort(ENERGETIC_RESORT));
            resortRepository.save(new Resort(KOFER_RESORT));
            resortRepository.save(new Resort(KSZK_RESORT));
            resortRepository.save(new Resort(KULTUR_RESORT));
            resortRepository.save(new Resort(SVK_RESORT));
            resortRepository.save(new Resort(SIMONYI_RESORT));
            resortRepository.save(new Resort(SPORT_RESORT));
            resortRepository.save(new Resort(SSSL_RESORT));
            resortRepository.save(new Resort(SZOR_RESORT));
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
        circleRepository.save(new Circle("Vödörkör", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("FoodEx", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Pulcsi és Foltmékör", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Szauna kör", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Dzsájrosz", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Vörös Kakas Fogadó", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("LángoSCH", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Edénykölcsönző", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Palacsintázó", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Americano", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("WTF", resortRepository.findByName(SZOR_RESORT)));
        circleRepository.save(new Circle("Pizzásch", resortRepository.findByName(SZOR_RESORT)));

        Resort resort = resortRepository.findByName(SZOR_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSSSLCircles() {
        circleRepository.save(new Circle(SSSL_RESORT, resortRepository.findByName(SSSL_RESORT)));

        Resort resort = resortRepository.findByName(SSSL_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSportCircles() {
        circleRepository.save(new Circle("Általános szertár", resortRepository.findByName(SPORT_RESORT)));
        circleRepository.save(new Circle("DiákSportKör", resortRepository.findByName(SPORT_RESORT)));
        circleRepository.save(new Circle("Csocsó kör", resortRepository.findByName(SPORT_RESORT)));
        circleRepository.save(new Circle("Asztalitenisz", resortRepository.findByName(SPORT_RESORT)));

        Resort resort = resortRepository.findByName(SPORT_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSimonyiCircles() {
        circleRepository.save(new Circle("KIR fejlesztők és üzemeltetők", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("Budavári Schönherz Stúdió", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("Simonyi Konferencia", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("Schönherz Design Stúdió", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("HA5KFU", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("AC Studio & Live", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("Schönherz Elektronikai Műhely", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("SPOT", resortRepository.findByName(SIMONYI_RESORT)));
        circleRepository.save(new Circle("Lego kör", resortRepository.findByName(SIMONYI_RESORT)));

        Resort resort = resortRepository.findByName(SIMONYI_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createSVKCircles() {
        circleRepository.save(new Circle(SVK_RESORT, resortRepository.findByName(SVK_RESORT)));

        Resort resort = resortRepository.findByName(SVK_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createKulturCircles() {
        circleRepository.save(new Circle("La'Place Café", resortRepository.findByName(KULTUR_RESORT)));
        circleRepository.save(new Circle("Játszóház", resortRepository.findByName(KULTUR_RESORT)));
        circleRepository.save(new Circle("Impulzus", resortRepository.findByName(KULTUR_RESORT)));
        circleRepository.save(new Circle("Bor Baráti Kör", resortRepository.findByName(KULTUR_RESORT)));
        circleRepository.save(new Circle("Bűvész Kör", resortRepository.findByName(KULTUR_RESORT)));
        circleRepository.save(new Circle("Local Heroes Szerepjátszó Kör", resortRepository.findByName(KULTUR_RESORT)));

        Resort resort = resortRepository.findByName(KULTUR_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createKSZKCircles() {
        circleRepository.save(new Circle("Hallgatói Tudásbázis", resortRepository.findByName(KSZK_RESORT)));
        circleRepository.save(new Circle("SecurITeam", resortRepository.findByName(KSZK_RESORT)));
        circleRepository.save(new Circle("Sysadmin", resortRepository.findByName(KSZK_RESORT)));
        circleRepository.save(new Circle("NETeam", resortRepository.findByName(KSZK_RESORT)));
        circleRepository.save(new Circle("DevTeam", resortRepository.findByName(KSZK_RESORT)));

        Resort resort = resortRepository.findByName(KSZK_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createKoferCircles() {
        circleRepository.save(new Circle("Szintképviselők Tanácsa", resortRepository.findByName(KOFER_RESORT)));
        circleRepository.save(new Circle("Kollégiumi Felvételi Bizottság", resortRepository.findByName(KOFER_RESORT)));

        Resort resort = resortRepository.findByName(KOFER_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createEnergetikaiSzakkoliCircles() {
        circleRepository.save(new Circle(ENERGETIC_RESORT, resortRepository.findByName(ENERGETIC_RESORT)));

        Resort resort = resortRepository.findByName(ENERGETIC_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createEgyebCircles() {
        circleRepository.save(new Circle("KB-Ref gárda", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("KB PR Csoport", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("Kari Hallgatói Képviselet", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("Felező bál", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("HK Munkacsoportok", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("Csillagtúra", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("Reszortvezetők Tanácsa", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("Kollégiumi Bizottság", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("Schönherz Qpa", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle("SVIE", resortRepository.findByName(EGYEB_RESORT)));
        circleRepository.save(new Circle(EGYEB_RESORT, resortRepository.findByName(EGYEB_RESORT)));

        Resort resort = resortRepository.findByName(EGYEB_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }

    private void createBulisCircles() {
        circleRepository.save(new Circle("Dezső Buli", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("LevelUp", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("Just Dance", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("Parkett Klub", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("ClubCeption", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("Homár", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("VIK Szakestély", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("Lanosch", resortRepository.findByName(BULIS_RESORT)));
        circleRepository.save(new Circle("Schörpong", resortRepository.findByName(BULIS_RESORT)));

        Resort resort = resortRepository.findByName(BULIS_RESORT);
        resort.setCircles(circleRepository.findAllByResort(resort));
        resortRepository.save(resort);
    }
}
