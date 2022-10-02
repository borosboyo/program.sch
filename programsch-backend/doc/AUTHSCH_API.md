## Használat
* ``` /api/[service]/{scope}?access_token={access token} ```
* válasz: json
* a service közelező, a scope opcionális paraméter

## Mintapélda
* lekérés: ``` GET /api/profile/?access_token=41db432cdba9d5d0a3052cbae7129e6ba0809d89 ```
* válasz: ``` {"internal_id":"{9DA3D9BE-04CD-FD26-CB87-BAF34B0367F8}","displayName":"Teszt Bela","sn":"Teszt","givenName":"Bela","mail":"bela@tesztel.ek","linkedAccounts":{"bme":"2214@bme.hu","schacc":"belavagyok","vir":"belaateszter"},"eduPersonEntitlement":[{"id":16,"name":"Simonyi K\u00e1roly Szakkoll\u00e9gium","status":"tag"},{"id":47,"name":"Koll\u00e9giumi Sz\u00e1m\u00edt\u00e1stechnikai K\u00f6r","status":"tag"},{"id":363,"name":"Buksi","status":"tag"}]} ```
* a fenti példa az adott access tokenhez tartozó user összes olyan adatát visszaadja, ami a profile típus alá esik és az engedélyek alapján elérhető

## Jelenleg támogatott típusok és scope-k
* profile
    * ``` basic ``` - AuthSCH-s azonosító (varchar, maximum 24 karakter). Belépéskor a kiadásához nem szükséges a felhasználó jóváhagyása
    * ``` displayName ``` - név
    * ``` sn ``` - vezetéknév
    * ``` givenName ``` - keresztnév
    * ``` mail ``` - e-mail cím
    * ``` niifPersonOrgID ``` - neptun kód (csak abban az esetben, ha a felhasználónak be van kötve a BME címtár azonosítója is, egyébként null-t ad vissza). Fokozottan védett információ, ami azt jelenti, hogy alapból nem kérhető le (invalid scope hibával kerül visszatérésre az ezt tartalmazó engedélykérés), csak indokolt esetben, központi engedélyezés után használható (ehhez adj fel egy ticketet a [support.sch.bme.hu](https://support.sch.bme.hu) oldalon, amelyben leírod hogy mihez és miért van rá szükséged.
    * ``` linkedAccounts ``` - kapcsolt accountok, kulcs - érték párokban. Lehetséges kulcsok:
        * bme: szám@bme.hu
        * schacc: schacc username
        * vir: vir id (integer)
        * virUid: vir username
    * ``` eduPersonEntitlement ``` - körtagságok (itt az adott körnél a status csak egy értéket vehet fel, mégpedig a körvezető / tag / öregtag közül valamelyiket, ebben a prioritási sorrendben)
    * ``` roomNumber ``` - ~~felhasználó szobaszáma (ha kollégista, akkor a kollégium neve és a szobaszám található meg benne, ha nem kollégista, akkor pedig null-t ad vissza). Amennyiben a felhasználó nem rendelkezik SCH Accounttal, szintén null-t ad eredményül.~~ Határozatlan ideig nem elérhető jogi okokból
    * ``` mobile ``` - mobilszám a VIR-ből
    * ``` niifEduPersonAttendedCourse ``` - az adott félévben hallgatott tárgyak
    * ``` entrants ``` - közösségi belépők a VIR-ről, február és július között az őszi, egyébként (tehát augusztustól januárig) a tavaszi belépők
    * ``` admembership ``` - csoporttagságok a KSZK-s Active Directoryban
    * ``` bmeunitscope``` - egyetemi jogviszony, jelenlegi lehetséges értékek: BME, BME_VIK, BME_VIK_ACTIVE, BME_VIK_NEWBIE, BME_VBK, BME_VBK_ACTIVE, BME_VBK_NEWBIE
    * ``` permanentaddress ``` - Állandó lakcím

## Tokenek
* lásd: [OAuth kliens](oauth_client)

## Minta
https://heimdall.sch.bme.hu/authsch-client