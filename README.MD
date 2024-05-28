# Pràctica 6.1 - BDD relacionals amb JDBC (NBA) Santi i Alba

Les dades de la nostra base de dades han sigut generades mitjançant endpoints amb Python.

-> Connexio amb la base de dades <-

En la classe "Connexió" recomanem a l'hora d'afegir la base de dades, modificar els valors següents perquè la connexió sigui més ràpida, ja que el programa comprova diferents connexions i aquesta és la primera en executar-se.

(private static final String URL = "url";
private static final String USUARI = "usuari";
private static final String PASSWORD = "contrasenya";)

## MÈTODE CRUD, DAO.

La nostra interfície DAO implementa 5 mètodes que s'han d'implementar en cadascuna de les classes DAO, els mètodes que conté són: 

inserir -> per afegir nous registres, 

actualitzar -> per actualitzar dades errònies o modificar dades existents,

esborrar -> per eliminar dades innecessàries, 

cercar -> per cercar dades concretes mitjançant consultes, 

count -> per contar el número de registres.

## CLASSES

En total el nostre projecte conte 15 classes diferents, utilitzem el format Model, Vista, Controlador i aquestes són totes les classes que nosaltres utilitzem:

### CLASSES NORMALS:

Estadística jugador, jugador, partit, equip i estadística jugador històric contenen cadascun el seu propi constructor amb les seves variables pròpies i els setters i getters per poder agafar i modificar la informació.

###  CLASSES DAO:

Cadascuna de les classes DAO conte els diferents mètodes obligatoris marcats a la interfície DAO, a més també conte diferents mètodes personalitzats necessaris per poder fer els diferents exercicis.

### CONNEXIO:

A la classe Connexió existeix una única funció que retorna una connexió la qual utilitzem per connectar-nos a les diferents bases de dades. Aquesta funció té un bulce "for" amb el qual anem comprovant totes les connexions de la llista.

### DAO:

La classe DAO implementa els mètodes CRUD, inserir, actualitzar, esborrar, cercar i contar.

## EXERCICIS

### 1. LLISTAR TOTS ELS JUGADORS D'UN EQUIP

En aquest exercici hem fet un ArrayList de jugadors on guardem tots els jugadors de l'equip introduït per l'usuari i els mostrem.

### 2. CALCULAR LA MITJANA DE PUNTS, REBOTS, ASSISTÈNCIES, ... D'UN JUGADOR

En aquest exercici fem una sentència SQL on fem la mitjana de tots els camps demanats. Un cop fetsguardem els noms i els valors en un LinkedHashMap per poder mostrar-los ordenadament més tard.

### 3. LLISTAR TOTS ELS PARTITS JUGATS PER UN EQUIP AMB EL SEU RESULTAT

A l'exercici 3 fem una "llista" que conté un "set" que ella mateixa conte un "Map.Entry".
D'aquesta forma guardem tot el conjunt en la "llista", els dos noms dels partits amb la seva puntuació en el "set", i el nom del partit amb la seva puntuació en el "Map.Entry".

Tot això el fem per poder obtenir correctament tots els resultats amb la seva puntuació de l'equip inserit per l'usuari.

### 4. INSERIR UN NOU JUGADOR A UN EQUIP

A l'exercici d'inserir un jugador donem l'oportunitat a l'usuari d'escollir el nom del nou jugador a inserir i l'equip, però les dades obligatòries següents les generem amb una funció que genera completament un nou jugador aleatori
(evidentment donem el nom, el cognom i el nom de l'equip inserit per l'usuari en aquesta mateixa funció).

Si el jugador a inserir ja existeix però en un altre equip, donem l'oportunitat de fer un traspàs, llavors en aquest punt utilitzem l'exercici 5.

### 5. TRASPASSAR UN JUGADOR A UN ALTRE EQUIP

L'exercici 5 no té molt misteri, tal com hem dit a l'exercici 4 el podem utilitzar en diferents exercicis. El que fem aquí és modificar les dades antigues per les noves i actualitzar els valors.

### 6. ACTUALITZAR LES DADES DE JUGADORS O EQUIPS DESPRÉS D'UN PARTIT

A l'exercici 6 mitjançant un csv i extraient les dades mitjançant endpoints modifiquem els valors estadístics de diferents jugadors en un partit.

En aquest utilitzem principalment BufferedReader i list per poder guardar i llegir els diferents valors extrets dels fitxers.

### 7. MODIFICAR LES ESTADÍSTIQUES D'UN JUGADOR

A l'exercici 7 demanem el nom d'un jugador i l'ID d'un partit per poder modificar dades estadístiques. Un cop posada les dades principals correctament ens sortirà un segon menú on podrem escollir quines dades volem modificar, si sortim del menú sense haver modificat cap dada aquest no s'actualitzarà.

El que hem fet ha sigut recollir les dades principals d'aquest partit i guardar-les en un objecte el qual anàvem modificant segons el que escollís l'usuari.

### 8. RETIRAR (ELIMINAR) UN JUGADOR

A l'exercici 8 hem de moure un jugador a elecció de l'usuari a una taula nova creada d'històrics, de la taula jugadors agafem el nom i el cognom del jugador a fer el traspàs de la taula estadístic agafem tots els partits relacionats amb aquest jugador. Un cop fet tot això inserim les noves dades a la nova taula.

Utilitzem llistes (Array List) per poder guardar les dades i inserir-les en la nova taula.

### 9. CANVIAR NOM FRANQUÍCIA D'UN EQUIP

En aquest exercici demanem un equip i una nova franquícia, ja que volem fer un canvi de ciutat en el nom del nostre equip. L'usuari s'encarrega d'introduir les dues dades i un cop introduïdes les modifiquem i les actualitzem amb mètodes d'equipDAO.
