# JEE-Server : Statistiques

## Configurer ses variables d'environnements sur IntelliJ

- Ouvrir la fenêtre 'Edit Run/Debug Configuration'.
- Se rendre dans la configuration du launcher Spring boot.
- Dans l'onglet 'Configuration' ajouter les variables d'environnements suivantes

```txt
AUTHENTIFICATION_DATABASE=admin
DB_USERNAME=root
DB_PASSWORD=c---------------s
DB_NAME=jee-server
DB_PORT=27010
DB_HOST=51.178.18.199
WEB_APP_PORT=8080
```

## Configuration variables d'environnement Linux

- Copier le **load_env.sh.sample** en **load_env.sh**
- Remplir avec les information le fichier **load_env.sh**

```bash
source load_env.sh
bash generate_database_user_file.sh
```

## Netoyage de l'environnement

```bash
source clear_env.sh
```

## Lancement

### Lancement d'une base de données MongoDB

```bash
docker run --name local_mongo -p ${DB_PORT}:${DB_PORT} -e MONGO_INITDB_DATABASE=${AUTHENTIFICATION_DATABASE} -e MONGO_INITDB_ROOT_USERNAME=${DB_ROOT_USERNAME} -e MONGO_INITDB_ROOT_PASSWORD=${DB_ROOT_PASSWORD} --volume "${full_path}/database_user.js:/docker-entrypoint-initdb.d/database_user.js:ro" -it mongo

```

### Pour lancer le serveur avec Docker

```console
docker build . --tag jee-server
docker run -d -p ${WEB_APP_PORT}:${WEB_APP_PORT} --name="jee-server" jee-server
```

### Pour lancer le docker-compose

```bash
docker-compose build
docker-compose run -d
```

### Pour lancer le serveur avec Maven

```console
mvn spring-boot:run
```

### Pour lancer le serveur avec Java

```console
mvn package
java -jar target/server-version.jar
```

## Description

Serveur JEE permettant de gérer ses dépenses et rentrées d’argent personnelles et de générer des statistiques.

<https://github.com/PALA-Organization/JEE-Server>

## Fonctionnalités

*Historique de l'ensemble des évènements de l'application (authentification utilisateur, scan de ticket, consultation des comptes par un utilisateur)*

## APIs

### 1. Comptes

- Ajout de transactions à la main de dépenses & rentrées d'argent
- Affichage avec tri par montant, date, période, filtre émetteur/récepteur

### 2. Stats

- Génération de statistiques sur un compte sur une période voulue (mois, jours, semaines) et Optionnel -> Export CSV
- *Pour l'api stats, permettre de remonter le volume d'affaire (totalité des montants à un instant T des comptes, T étant paramétrable via l'appel de l'api)*

### 3. User

- Authentification
- Création de compte basique

### 4. Santé de l’OCR (API Supplémentaire)

*Ajouter une api permettant de connaître l'état de santé du système distant utilisé (posez-vous donc la question de ce que votre application doit faire lorsque l'OCR n'est pas disponible)*

### 5. OCR (API Externe)

Scan des tickets de caisse pour ne pas avoir à rentrer à la main

## Outils

- MongoDB (NoSQL)
- AWS avec Load Balancing (load Balancing Apache)
- CI/CD : GitHub Action pour build/test/deploy
- Docker

## Working flow

[Git flow](https://danielkummer.github.io/git-flow-cheatsheet/index.fr_FR.html)
