## Használat előtt regisztrálni kell az alkalmazást a https://auth.sch.bme.hu/console/index oldalon

## Tudnivalók
* az authentikáció az OAuth2 -re épül
* ha az általad használt nyelvhez létezik már library, akkor célszerű inkább azt használni
* ekkor:
    * authorize endpoint: https://auth.sch.bme.hu/site/login (site, nem oauth2!)
    * resource endpoint: https://auth.sch.bme.hu/oauth2/resource (csak esetleges token teszteléshez, az api máshol érhető el)
    * token endpoint: https://auth.sch.bme.hu/oauth2/token

## Tokenek típusai
* access token:
    * belépéskor kapja a felhasználó
    * az API használatához szükséges
    * érvényesség: 1 óra.
* refresh token:
    * szintén belépéskor kapja a felhasználó
    * segítségével új access token kérhető
    * érvényesség: 180 nap
    * amennyiben a kliens újra belépteti a felhasználót, úgy a korábbi refresh token törlődik és új kerül kiállításra!
    * __a felhasználó visszavonhatja a tokent a 180 nap letelte előtt, ennek kezelésére az alkalmazást fel kell készíteni!__

## Access token beszerzése
Az authentikáció és az authorizáció több, jól definiált lépésre bontható. Fontos, hogy mindegyik lekérés POST metódust használ!
A felhasználót át kell irányítani a login oldalra, ahol megtörténik a belépés és az engedélykérés

    `https://auth.sch.bme.hu/site/login?response_type=code&client_id=<kliens azonosító>&state=<felhasználóra jellemző egyedi azonosító>&scope=<jogosultságok>`. 
Itt némi magyarázat:

    * A kliens azonosító megegyezik az oldal által használt felhasználónévvel, ez a fenti példában 'testclient'.
    * A felhasználóra jellemző egyedi azonosító azt a célt szolgálja, hogy az oauth folyamatába ne férkőzhessen harmadik fél. Olyan értéket kell választani, amely a felhasználóra jellemző és az engedélyezési folyamat végén megvizsgálható hogy egyezik-e az az eredetivel. Ilyen érték lehet például a felhasználó IP címe, vagy a böngészője user-agentje, természetesen hashelve.
    * A jogosultságok tartalmától függ, hogy később a weboldal milyen felhasználói adatokhoz fér hozzá. Az itt megadott értékeket a felhasználónak jóvá kell hagynia, így csak a weboldal működéséhez feltétlen szükséges jogosultságokat érdemes kérni.
    * A jogosultságok listája az [API](api) leírásnál érhető el. Az egyes jogosultságokat + (plusz) jellel kell elválasztani.

* Ha a felhasználó bejelentkezett és jóváhagyta a kért jogosultságot, akkor a szerver visszairányítja a weboldalra, mellékelve GET paraméterként az authorization code-t (code nevű paraméter)
* Ezután már csak egy lépés van hátra, meg kell hívni a token endpointot a kapott kóddal: `curl -u testclient:testpass https://auth.sch.bme.hu/oauth2/token -d 'grant_type=authorization_code&code=YOUR_CODE'`
* Erre válaszul megkapjuk az access tokent az alábbi formában:
  `{"access_token":"6f05ad622a3d32a5a81aee5d73a5826adb8cbf63","expires_in":3600,"token_type":"bearer","scope":<jogosultságok>,"refresh_token":"e8abb8a1640ccef4b4d9b6162d59cd50a2e3f4de"}`

## Access token frissítése
* Lehetőség van a nálunk levő access tokent frissíteni
* Ez a gyakorlatban azt jelenti, hogy a lejárt access token helyett kapunk egy újat
* A művelet nem igényli a felhasználó jelenlétét / jóváhagyását, amennyiben rendelkezünk érvényes refresh tokennel
* Példa

`curl -u testclient:testpass "https://auth.sch.bme.hu/oauth2/token" -d 'grant_type=refresh_token&refresh_token=c54adcfdb1d99d10be3be3b77ec32a2e402ef7e3'`
* Válasz

`{"access_token":"0e9d02499fe06762ecaafb9cfbb506676631dcfd","expires_in":3600,"token_type":"bearer","scope":<jogosultságok>}`