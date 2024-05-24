from nba_api.stats.endpoints import leaguegamefinder, commonplayerinfo, playergamelogs, teaminfocommon
import mysql.connector
import csv
from time import sleep

con = mysql.connector.connect(user='root', password='mas', host='127.0.0.1', port=3336)
cur = con.cursor()

cur.execute("USE `nba_2023-24`")

logsPartits = playergamelogs.PlayerGameLogs(season_nullable="2023-24").get_normalized_dict()['PlayerGameLogs']
sleep(0.5)

idsJugadors = set()
idsEquips = set()

for log in logsPartits:
    idsEquips.add(log['TEAM_ID'])
    if log['TEAM_ID'] in idsEquips and log['TEAM_ID'] != 0:
        idsJugadors.add(log['PLAYER_ID'])


valorsEquips = []

for idEquip in idsEquips:
    infoEquip = teaminfocommon.TeamInfoCommon(team_id=idEquip, season_nullable="2023-24").get_normalized_dict()['TeamInfoCommon'][0]
    equipId = int(infoEquip['TEAM_ID'])
    ciutat = infoEquip['TEAM_CITY']
    nom = infoEquip['TEAM_NAME']
    acronim = infoEquip['TEAM_ABBREVIATION']
    divisio = infoEquip['TEAM_DIVISION']
    guanyades = int(infoEquip['W'])
    perdudes = int(infoEquip['L'])
    valorsEquips.append((equipId, ciutat, nom, acronim, divisio, guanyades, perdudes))
    sleep(0.4)

with open('equips.csv', 'w', newline='') as csvEquips:
    escritorEquips = csv.writer(csvEquips, delimiter=';')
    escritorEquips.writerow(['equip_id', 'ciutat', 'nom', 'acronim', 'divisio', 'guanyades', 'perdudes'])
    escritorEquips.writerows(valorsEquips)

valorsEstadistiques = []

for idJugador in idsJugadors:
    logsPartits = playergamelogs.PlayerGameLogs(season_nullable="2023-24", player_id_nullable=idJugador).get_normalized_dict()['PlayerGameLogs']

    for log in logsPartits:
        partitId = int(log['GAME_ID'])
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
        valorsEstadistiques.append((idJugador, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats, tirsLliuresTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs))
    
    sleep(0.4)

with open('estadistiques_jugadors.csv', 'w', newline='') as csvEstadistiques:
    escritorEstadistiques = csv.writer(csvEstadistiques, delimiter=';')
    escritorEstadistiques.writerow(["jugador_id", "partit_id", "minuts_jugats", "punts", "tirs_anotats", "tirs_tirats", "tirs_triples_anotats", "tirs_triples_tirats", "tirs_lliures_anotats", "tirs_lliures_tirats", "rebots_ofensius", "rebots_defensius", "assistencies", "robades", "bloqueigs"])
    escritorEstadistiques.writerows(valorsEstadistiques)
