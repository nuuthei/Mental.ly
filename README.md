SUUNNITELMA: SuomiSää appi

Lyhyt kuvaus:

Suomen sää on appi, joka piirtää kuvan Sumesta säätietoineen. Käytännössä tämä tarkoittaa kaupunkien säätilaa visualisoituna lämpötila-arvojen, aurinkojen, pilvien ja sadepisaroiden muodossa. Kaupungit ovat Helsinki, Turku, Tampere, Oulu, Rovaniemi, Kuopio ja Inari.

Toiminnot:

* Piirrä kartta.
* Aseta sääsymbolit kartalle. Hakee symbolit automaattisesti API-kutsun avulla. Ikoneina käytetään OpenWeather API:n tarjoamia .png -kuvia, joita ei tarvitse sisällyttää erillisinä tiedostoina sovelluksen lähdekansioon.
* Sijoita ikonit kartalle oikeisiin paikkoihin.
* Päivitä sääsymbolit tietyin aikavälein

Mitä tietoa tarvitaan rajapinnasta:

  Current weather
  .initialResponse
  .main
    .temp

tämä on tekstiä