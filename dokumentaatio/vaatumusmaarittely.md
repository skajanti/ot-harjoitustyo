# Matopeli

### Sovelluksen tarkoitus
Sovelluksen tarkoitus on simuloida snake 1 -pelin toimintoa.

### Toiminnallisuudet
* Snake 1 -pelin perustoiminnallisuudet: käärme liikkuu, kasvaa hedelmiä syödessä ja kuolee jos osuu itseensä tai seinään.
* Vaikeusvaihtoeto, joka määrää pelin nopeuden.
* ~~Highscore-taulukko tallennettuna sql:llä ja nähtävä verkosta.~~ 

### Perustoimintojen suunnitelma
Peli toteutetaan kaksiuloitteisella taulukolla. Kun käärme tulee ruutuun, alkaa ruutu laskemaan siirtoja. Kun siirtojen määrä on suurempi kun käärmeen pituus, käärme poistuu ruudulta.

### Laajennusideoita
* Snake 2 -pelin ominaisuuksia: tason reunaan osuessa siirtyy tason toiselle puolelle. Eri tasoja, joissa on tason keskellä seiniä.
* Sisäänkirjautuminen
* Myrkylliset hedelmät
* Grafiikat
* Highsocret
