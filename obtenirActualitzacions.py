from nba_api.stats.endpoints import leaguegamefinder, playergamelogs
import mysql.connector
import csv
from time import sleep

con = mysql.connector.connect(user='root', password='mas', database="nba_2023-24", host='127.0.0.1', port=3306)
cur = con.cursor()

cur.execute("SELECT jugador_id FROM jugadors")
jugadors = [x[0] for x in cur.fetchall()]

cur.execute("SELECT equip_id FROM equips")
equips = [x[0] for x in cur.fetchall()]

valorsPartits = []

for equipId in equips:
    partits = leaguegamefinder.LeagueGameFinder(season_nullable="2023-24", team_id_nullable=equipId).get_normalized_dict()['LeagueGameFinderResults']

    for partit in partits:
        partitId = int(partit['GAME_ID'])
        equipId = int(partit['TEAM_ID'])
        dataPartit = str(partit['GAME_DATE'])
        matx = str(partit['MATCHUP'])
        resultat = str(partit['WL'])
        if resultat not in ["W", "L"]:
            continue
        valorsPartits.append((partitId, equipId, dataPartit, matx, resultat))
    sleep(0.4)

with open('partits.csv', 'w', newline='') as csvPartits:
    escritorPartits = csv.writer(csvPartits, delimiter=';')
    escritorPartits.writerow(['partit_id', 'equip_id', 'data_partit', 'matx', 'resultat'])
    escritorPartits.writerows(valorsPartits)

valorsEstadistiques = []

for idJugador in jugadors:
    logsPartits = playergamelogs.PlayerGameLogs(season_nullable="2023-24", player_id_nullable=idJugador).get_normalized_dict()['PlayerGameLogs']

    for log in logsPartits:
        partitId = int(log['GAME_ID'])
        equipId = int(log["TEAM_ID"])
        minutsJugats = round(log['MIN'], 2)
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
        valorsEstadistiques.append((idJugador, equipId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats,tirsLliuresTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs))
    
    sleep(0.4)

with open('estadistiques_jugadors.csv', 'w', newline='') as csvEstadistiques:
    escritorEstadistiques = csv.writer(csvEstadistiques, delimiter=';')
    escritorEstadistiques.writerow(["jugador_id", "equip_id", "partit_id", "minuts_jugats", "punts", "tirs_anotats", "tirs_tirats", "tirs_triples_anotats", "tirs_triples_tirats", "tirs_lliures_anotats", "tirs_lliures_tirats", "rebots_ofensius", "rebots_defensius", "assistencies", "robades", "bloqueigs"])
    escritorEstadistiques.writerows(valorsEstadistiques)
