from nba_api.stats.endpoints import leaguegamefinder,commonplayerinfo,playergamelogs,teaminfocommon
import mysql.connector
from time import sleep

con = mysql.connector.connect(user='root', password='mas',host='127.0.0.1', port=3306)
cur = con.cursor()

cur.execute("CREATE DATABASE IF NOT EXISTS `nba_2023-24`")
cur.execute("USE `nba_2023-24`")
con.autocommit = True

logsPartits = playergamelogs.PlayerGameLogs(season_nullable="2023-24").get_normalized_dict()['PlayerGameLogs']
sleep(0.5)
    
idsJugadors = set()
idsEquips = set()

for log in logsPartits:
    idsEquips.add(log['TEAM_ID'])
    if log['TEAM_ID'] in idsEquips and log['TEAM_ID'] != 0:
        idsJugadors.add(log['PLAYER_ID'])

cur.execute("""CREATE TABLE IF NOT EXISTS equips (  equip_id INT UNSIGNED NOT NULL,
                                                    ciutat VARCHAR(50) NOT NULL,
                                                    nom VARCHAR(50) NOT NULL, 
                                                    acronim CHAR(3) NOT NULL, 
                                                    divisio VARCHAR(50) NOT NULL, 
                                                    guanyades TINYINT(3) UNSIGNED NOT NULL DEFAULT 0, 
                                                    perdudes TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
                                                    CONSTRAINT pk_equips PRIMARY KEY (equip_id)) """)

cur.execute("SELECT COUNT(*) FROM equips")
contEquips = cur.fetchone()[0]

if contEquips <= 0:
    print("Introduint valors a la taula 'equips'")
    
    insertEquips = "INSERT INTO equips (equip_id, ciutat, nom, acronim, divisio, guanyades, perdudes) VALUES (%s,%s,%s,%s,%s,%s,%s)"
    valorsEquips = []

    for idEquip in idsEquips:
        infoEquip = teaminfocommon.TeamInfoCommon(team_id=idEquip,season_nullable="2023-24").get_normalized_dict()['TeamInfoCommon'][0]
        equipId = int(infoEquip['TEAM_ID'])
        ciutat = infoEquip['TEAM_CITY']
        nom = infoEquip['TEAM_NAME']
        acronim = infoEquip['TEAM_ABBREVIATION']
        divisio = infoEquip['TEAM_DIVISION']
        guanyades = int(infoEquip['W'])
        perdudes = int(infoEquip['L'])
        try:
            cur.execute(insertEquips, (equipId, ciutat, nom,
                        acronim, divisio, guanyades, perdudes))
        except Exception as e:
            print(e)
            print((equipId, ciutat, nom, acronim, divisio, guanyades, perdudes))
            exit()
        
        sleep(0.4)
else:
    print("Ya hi ha valors a la taula 'equips'")

cur.execute("""CREATE TABLE IF NOT EXISTS jugadors (jugador_id INT UNSIGNED NOT NULL, 
                                                    nom VARCHAR(150) NOT NULL,
                                                    cognom VARCHAR(150) NOT NULL,
                                                    data_naixement DATE,
                                                    alcada DECIMAL(5,2) UNSIGNED,
                                                    pes DECIMAL(5,2) UNSIGNED,
                                                    dorsal CHAR(2) NOT NULL,
                                                    posicio VARCHAR(25) NOT NULL,
                                                    equip_id INT UNSIGNED NOT NULL,
                                                    CONSTRAINT pk_jugadors PRIMARY KEY (jugador_id),
                                                    CONSTRAINT fk_jugadors_equips FOREIGN KEY (equip_id) REFERENCES equips(equip_id))""")

cur.execute("SELECT COUNT(*) FROM jugadors")
contJugadors = cur.fetchone()[0]

if contJugadors <= 0:
    print("Introduint valors a la taula 'jugadors'")
    
    insertJugadors = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    perEliminar = []

    for idJugador in idsJugadors:
            infoJugador = commonplayerinfo.CommonPlayerInfo(player_id=idJugador).get_normalized_dict()['CommonPlayerInfo'][0]
            nom = infoJugador['FIRST_NAME']
            cognom = infoJugador['LAST_NAME']
            dataNaixement = infoJugador['BIRTHDATE'].split("T")[0]
            altura = round((int(infoJugador['HEIGHT'].split("-")[0]) * 30.48) + (int(infoJugador['HEIGHT'].split("-")[1]) * 2.54),2)
            pes = round(int(infoJugador['WEIGHT']) / 2.205,2)
            dorsal = infoJugador['JERSEY']
            posicio = infoJugador['POSITION']
            equipId = int(infoJugador['TEAM_ID'])
            if equipId not in idsEquips:
                perEliminar.append(idJugador)
                continue
            try:
                cur.execute(insertJugadors, (idJugador, nom, cognom,dataNaixement, altura, pes, dorsal, posicio, equipId))
            except Exception as e:
                print(e)
                print((idJugador, nom, cognom, dataNaixement,altura, pes, dorsal, posicio, equipId))
                exit()
                
            sleep(0.4)
            
        
else:
    print("Ya hi ha valors a la taula 'jugadors'") 


cur.execute("""CREATE TABLE IF NOT EXISTS partits ( partit_id INT UNSIGNED NOT NULL,
                                                    equip_id INT UNSIGNED NOT NULL,
                                                    data_partit DATE NOT NULL,
                                                    matx CHAR(12) NOT NULL,
                                                    resultat CHAR(1),
                                                    CONSTRAINT pk_partits PRIMARY KEY (partit_id,equip_id),
                                                    CONSTRAINT fk_partits_equips FOREIGN KEY (equip_id) REFERENCES equips(equip_id))""")

cur.execute("SELECT COUNT(*) FROM partits")
contPartits = cur.fetchone()[0]

if contPartits <= 0:
    print("Introduint valors a la taula 'partits'")
    
    insertPartits = "INSERT INTO partits (partit_id, equip_id, data_partit, matx, resultat) VALUES (%s,%s,%s,%s,%s)"
    
    for equipId in idsEquips:
        partits = leaguegamefinder.LeagueGameFinder(season_nullable="2023-24", team_id_nullable=equipId).get_normalized_dict()['LeagueGameFinderResults']
            
        for partit in partits:
            partitId = int(partit['GAME_ID'])
            equipId = int(partit['TEAM_ID'])
            dataPartit = partit['GAME_DATE']
            matx = partit['MATCHUP']
            resultat = partit['WL']
            if resultat not in ["L","W"]:
                continue
            try:
                cur.execute(insertPartits, (partitId, equipId,dataPartit, matx, resultat))
            except Exception as e:
                print(e)
                print((partitId, equipId, dataPartit, matx, resultat))
                exit()
                
        sleep(0.4)
            

else:
    print("Ya hi ha valors a la taula 'partits'")
        
        
cur.execute("""CREATE TABLE IF NOT EXISTS estadistiques_jugadors (  jugador_id INT UNSIGNED NOT NULL,
                                                                    equip_id INT UNSIGNED NOT NULL,
                                                                    partit_id INT UNSIGNED NOT NULL,
                                                                    minuts_jugats DECIMAL(4,2) UNSIGNED NOT NULL,
                                                                    punts TINYINT(3) UNSIGNED NOT NULL,
                                                                    tirs_anotats TINYINT(2) UNSIGNED NOT NULL,
                                                                    tirs_tirats TINYINT(2) UNSIGNED NOT NULL,
                                                                    tirs_triples_anotats TINYINT(2) UNSIGNED NOT NULL,
                                                                    tirs_triples_tirats TINYINT(2) UNSIGNED NOT NULL,
                                                                    tirs_lliures_anotats TINYINT(2) UNSIGNED NOT NULL,
                                                                    tirs_lliures_tirats TINYINT(2) UNSIGNED NOT NULL,
                                                                    rebots_ofensius TINYINT(2) UNSIGNED NOT NULL,
                                                                    rebots_defensius TINYINT(2) UNSIGNED NOT NULL,
                                                                    assistencies TINYINT(2) UNSIGNED NOT NULL,
                                                                    robades TINYINT(2) UNSIGNED NOT NULL,
                                                                    bloqueigs TINYINT(2) UNSIGNED NOT NULL,
                                                                    CONSTRAINT pk_estadistiques_jugadors PRIMARY KEY (jugador_id,equip_id,partit_id),
                                                                    CONSTRAINT fk_estadistiques_jugadors_jugadors FOREIGN KEY (jugador_id) REFERENCES jugadors(jugador_id),
                                                                    CONSTRAINT fk_estadistiques_jugadors_equips FOREIGN KEY (equip_id) REFERENCES equips(equip_id))""")

cur.execute("SELECT COUNT(*) FROM estadistiques_jugadors")
contEstadistiques = cur.fetchone()[0]

if contEstadistiques <= 0:
    print("Introduint valors a la taula 'estadistiques_jugadors'")
    
    insertEstadistiques = "INSERT INTO estadistiques_jugadors (jugador_id, equip_id, partit_id, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    valorsEstadistiques = []
    
    for jugador in perEliminar:
        idsJugadors.discard(jugador)
    
    for idJugador in idsJugadors:
        logsPartits = playergamelogs.PlayerGameLogs(season_nullable="2023-24",player_id_nullable=idJugador).get_normalized_dict()['PlayerGameLogs']

        for log in logsPartits:
            partitId = int(log['GAME_ID'])
            equipId = int(log["TEAM_ID"])
            minutsJugats = round(log['MIN'],2)
            punts = int(log['PTS'])
            tirsAnotats = int(log['FGM'])
            tirsTirats = int(log['FGA'])
            tirsTriplesAnotats = int(log['FG3M'])
            tirsTriplesTirats = int(log['FG3A'])
            tirsLliuresAnotats = int(log['FTM'])
            tirsLliuresTirats = int(log['FTA'])
            rebotsOfensius = int(log['OREB'])
            rebotsDefensius = int(log['DREB'])
            assistencies = int(log['AST'])
            robades = int(log['STL'])
            bloqueigs = int(log['BLK'])
            try:
                cur.execute(insertEstadistiques, (idJugador, equipId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats,
                                tirsLliuresTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs))
                con.commit()
            except Exception as e:
                print(e)
                print((idJugador, equipId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats, tirsLliuresTirats,
                      tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs))
                exit()
        
        sleep(0.4)
else:
    print("Ya hi ha valors a la taula 'estadistiques_jugadors'")

cur.execute("""CREATE TABLE IF NOT EXISTS estadistiques_jugadors_historics (    jugador_id INT UNSIGNED NOT NULL,
                                                                                nom VARCHAR(150) NOT NULL,
                                                                                cognom VARCHAR(150) NOT NULL,
                                                                                equip_id INT UNSIGNED NOT NULL,
                                                                                partit_id INT UNSIGNED NOT NULL,
                                                                                minuts_jugats DECIMAL(4,2) UNSIGNED NOT NULL,
                                                                                punts TINYINT(3) UNSIGNED NOT NULL,
                                                                                tirs_anotats TINYINT(2) UNSIGNED NOT NULL,
                                                                                tirs_tirats TINYINT(2) UNSIGNED NOT NULL,
                                                                                tirs_triples_anotats TINYINT(2) UNSIGNED NOT NULL,
                                                                                tirs_triples_tirats TINYINT(2) UNSIGNED NOT NULL,
                                                                                tirs_lliures_anotats TINYINT(2) UNSIGNED NOT NULL,
                                                                                tirs_lliures_tirats TINYINT(2) UNSIGNED NOT NULL,
                                                                                rebots_ofensius TINYINT(2) UNSIGNED NOT NULL,
                                                                                rebots_defensius TINYINT(2) UNSIGNED NOT NULL,
                                                                                assistencies TINYINT(2) UNSIGNED NOT NULL,
                                                                                robades TINYINT(2) UNSIGNED NOT NULL,
                                                                                bloqueigs TINYINT(2) UNSIGNED NOT NULL)""")

con.close()