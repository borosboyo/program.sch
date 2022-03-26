import React from 'react';
import AppNavbar from "./AppNavbar";

export class EditFilters extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
    }

    render() {
        return (
            <div>
                <AppNavbar/>
                <div className="container">
                    <div className="row">
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Bulis Reszort</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="46"
                                   className="list-group-item list-group-item-danger">
                                    Dezső Buli
                                </a><a href="#" data-toggle="filter-toggle" data-circle="61"
                                       className="list-group-item list-group-item-danger">
                                Bulis Reszort
                            </a><a href="#" data-toggle="filter-toggle" data-circle="63"
                                   className="list-group-item list-group-item-danger">
                                Just Dance
                            </a><a href="#" data-toggle="filter-toggle" data-circle="65"
                                   className="list-group-item list-group-item-danger">
                                Parkett Klub
                            </a><a href="#" data-toggle="filter-toggle" data-circle="77"
                                   className="list-group-item list-group-item-danger">
                                ClubCeption
                            </a><a href="#" data-toggle="filter-toggle" data-circle="87"
                                   className="list-group-item list-group-item-danger">
                                Homár
                            </a><a href="#" data-toggle="filter-toggle" data-circle="98"
                                   className="list-group-item list-group-item-danger">
                                VIK Szakestély
                            </a><a href="#" data-toggle="filter-toggle" data-circle="109"
                                   className="list-group-item list-group-item-danger">
                                Lanosch
                            </a><a href="#" data-toggle="filter-toggle" data-circle="152"
                                   className="list-group-item list-group-item-danger">
                                Schörpong
                            </a><a href="#" data-toggle="filter-toggle" data-circle="153"
                                   className="list-group-item list-group-item-danger">
                                LevelUp
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Egyéb</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="5"
                                   className="list-group-item list-group-item-danger">
                                    KB-Ref gárda
                                </a><a href="#" data-toggle="filter-toggle" data-circle="10"
                                       className="list-group-item list-group-item-danger">
                                KB PR Csoport
                            </a><a href="#" data-toggle="filter-toggle" data-circle="15"
                                   className="list-group-item list-group-item-danger">
                                Kari Hallgatói Képviselet
                            </a><a href="#" data-toggle="filter-toggle" data-circle="16"
                                   className="list-group-item list-group-item-danger">
                                Felező bál
                            </a><a href="#" data-toggle="filter-toggle" data-circle="19"
                                   className="list-group-item list-group-item-danger">
                                HK Munkacsoportok
                            </a><a href="#" data-toggle="filter-toggle" data-circle="28"
                                   className="list-group-item list-group-item-danger">
                                Csillagtúra
                            </a><a href="#" data-toggle="filter-toggle" data-circle="29"
                                   className="list-group-item list-group-item-danger">
                                Reszortvezetők Tanácsa
                            </a><a href="#" data-toggle="filter-toggle" data-circle="37"
                                   className="list-group-item list-group-item-danger">
                                Kollégiumi Bizottság
                            </a><a href="#" data-toggle="filter-toggle" data-circle="39"
                                   className="list-group-item list-group-item-danger">
                                Schönherz Qpa
                            </a><a href="#" data-toggle="filter-toggle" data-circle="112"
                                   className="list-group-item list-group-item-danger">
                                SVIE
                            </a><a href="#" data-toggle="filter-toggle" data-circle="134"
                                   className="list-group-item list-group-item-danger">
                                Egyéb
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Energetikai Szakkollégium</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="120"
                                   className="list-group-item list-group-item-danger">
                                    Energetikai Szakkollégium
                                </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Kollégiumi Felvételi és Érdekvédelmi Reszort</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="2"
                                   className="list-group-item list-group-item-danger">
                                    Szintképviselők Tanácsa
                                </a><a href="#" data-toggle="filter-toggle" data-circle="42"
                                       className="list-group-item list-group-item-danger">
                                Kollégiumi Felvételi Bizottság
                            </a><a href="#" data-toggle="filter-toggle" data-circle="57"
                                   className="list-group-item list-group-item-danger">
                                Kollégiumi Felvételi és Érdekvédelmi Reszort
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Kollégiumi Számítástechnikai Kör</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="6"
                                   className="list-group-item list-group-item-danger">
                                    Hallgatói Tudásbázis
                                </a><a href="#" data-toggle="filter-toggle" data-circle="69"
                                       className="list-group-item list-group-item-danger">
                                Kollégiumi Számítástechnikai Kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="75"
                                   className="list-group-item list-group-item-danger">
                                Sysadmin
                            </a><a href="#" data-toggle="filter-toggle" data-circle="76"
                                   className="list-group-item list-group-item-danger">
                                NETeam
                            </a><a href="#" data-toggle="filter-toggle" data-circle="86"
                                   className="list-group-item list-group-item-danger">
                                DevTeam
                            </a><a href="#" data-toggle="filter-toggle" data-circle="116"
                                   className="list-group-item list-group-item-danger">
                                SecurITeam
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Kultúr Reszort</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="20"
                                   className="list-group-item list-group-item-danger">
                                    La&#039;Place Café
                                </a><a href="#" data-toggle="filter-toggle" data-circle="22"
                                       className="list-group-item list-group-item-danger">
                                Játszóház
                            </a><a href="#" data-toggle="filter-toggle" data-circle="40"
                                   className="list-group-item list-group-item-danger">
                                Impulzus
                            </a><a href="#" data-toggle="filter-toggle" data-circle="47"
                                   className="list-group-item list-group-item-danger">
                                Bor Baráti Kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="54"
                                   className="list-group-item list-group-item-danger">
                                Kultúr Reszort
                            </a><a href="#" data-toggle="filter-toggle" data-circle="89"
                                   className="list-group-item list-group-item-danger">
                                Local Heroes Szerepjátszó Kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="142"
                                   className="list-group-item list-group-item-danger">
                                Bűvész Kör
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Schönherz Vállalati Kapcsolatok</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="14"
                                   className="list-group-item list-group-item-danger">
                                    Schönherz Vállalati Kapcsolatok
                                </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Simonyi Károly Szakkolégium</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="48"
                                   className="list-group-item list-group-item-danger">
                                    Simonyi Károly Szakkollégium
                                </a><a href="#" data-toggle="filter-toggle" data-circle="49"
                                       className="list-group-item list-group-item-danger">
                                KIR fejlesztők és üzemeltetők
                            </a><a href="#" data-toggle="filter-toggle" data-circle="53"
                                   className="list-group-item list-group-item-danger">
                                Budavári Schönherz Stúdió
                            </a><a href="#" data-toggle="filter-toggle" data-circle="56"
                                   className="list-group-item list-group-item-danger">
                                Simonyi Konferencia
                            </a><a href="#" data-toggle="filter-toggle" data-circle="66"
                                   className="list-group-item list-group-item-danger">
                                Schönherz Design Stúdió
                            </a><a href="#" data-toggle="filter-toggle" data-circle="91"
                                   className="list-group-item list-group-item-danger">
                                HA5KFU
                            </a><a href="#" data-toggle="filter-toggle" data-circle="100"
                                   className="list-group-item list-group-item-danger">
                                AC Studio &amp; Live
                            </a><a href="#" data-toggle="filter-toggle" data-circle="104"
                                   className="list-group-item list-group-item-danger">
                                Schönherz Elektronikai Műhely
                            </a><a href="#" data-toggle="filter-toggle" data-circle="108"
                                   className="list-group-item list-group-item-danger">
                                SPOT
                            </a><a href="#" data-toggle="filter-toggle" data-circle="114"
                                   className="list-group-item list-group-item-danger">
                                Lego kör
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Sport Reszort</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="11"
                                   className="list-group-item list-group-item-danger">
                                    Általános szertár
                                </a><a href="#" data-toggle="filter-toggle" data-circle="27"
                                       className="list-group-item list-group-item-danger">
                                Sport Reszort
                            </a><a href="#" data-toggle="filter-toggle" data-circle="31"
                                   className="list-group-item list-group-item-danger">
                                DiákSportKör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="81"
                                   className="list-group-item list-group-item-danger">
                                Csocsó kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="82"
                                   className="list-group-item list-group-item-danger">
                                Asztalitenisz
                            </a><a href="#" data-toggle="filter-toggle" data-circle="97"
                                   className="list-group-item list-group-item-danger">
                                Kézilabda kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="115"
                                   className="list-group-item list-group-item-danger">
                                Röplabda kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="154"
                                   className="list-group-item list-group-item-danger">
                                Kerékpár kör
                            </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Szent Schönherz Senior Lovagrend</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="1"
                                   className="list-group-item list-group-item-danger">
                                    Szent Schönherz Senior Lovagrend
                                </a></div>
                        </div>
                        <div className="col-md-4">
                            <div className="list-group">
                                <li className="list-group-item">
                                    <h4 className="list-group-item-heading">
                                        <b>Szolgáltató Reszort</b>
                                    </h4>
                                </li>
                                <a href="#" data-toggle="filter-toggle" data-circle="3"
                                   className="list-group-item list-group-item-danger">
                                    Vödörkör
                                </a><a href="#" data-toggle="filter-toggle" data-circle="4"
                                       className="list-group-item list-group-item-danger">
                                Szolgáltató Reszort
                            </a><a href="#" data-toggle="filter-toggle" data-circle="17"
                                   className="list-group-item list-group-item-danger">
                                FoodEx
                            </a><a href="#" data-toggle="filter-toggle" data-circle="18"
                                   className="list-group-item list-group-item-danger">
                                Pulcsi és Foltmékör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="35"
                                   className="list-group-item list-group-item-danger">
                                Szauna kör
                            </a><a href="#" data-toggle="filter-toggle" data-circle="36"
                                   className="list-group-item list-group-item-danger">
                                Dzsájrosz
                            </a><a href="#" data-toggle="filter-toggle" data-circle="51"
                                   className="list-group-item list-group-item-danger">
                                Vörös Kakas Fogadó
                            </a><a href="#" data-toggle="filter-toggle" data-circle="64"
                                   className="list-group-item list-group-item-danger">
                                LángoSCH
                            </a><a href="#" data-toggle="filter-toggle" data-circle="68"
                                   className="list-group-item list-group-item-danger">
                                Edénykölcsönző
                            </a><a href="#" data-toggle="filter-toggle" data-circle="83"
                                   className="list-group-item list-group-item-danger">
                                Palacsintázó
                            </a><a href="#" data-toggle="filter-toggle" data-circle="84"
                                   className="list-group-item list-group-item-danger">
                                Americano
                            </a><a href="#" data-toggle="filter-toggle" data-circle="106"
                                   className="list-group-item list-group-item-danger">
                                WTF
                            </a><a href="#" data-toggle="filter-toggle" data-circle="107"
                                   className="list-group-item list-group-item-danger">
                                Pizzásch
                            </a></div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
