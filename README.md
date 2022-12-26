Backend application for SimSprintSeries website.

# Local environment setup
> **_IMPORTANT:_**
> 
> $PASSWORD is your secure password for your local DB
> 
> $GARAGE_BASE_DIR is base directory of garare repository, for example /Users/Ryszard/garage

1. Install, start and initialize [MariaDB](https://mariadb.com/get-started-with-mariadb/):
    - MacOS:
      1. ``` brew install mariadb ```
      2. ``` brew services restart mariadb ```
      3. ``` sudo $(brew --prefix mariadb)/bin/mysqladmin -u root password $PASSWORD ```
      4. ``` mysql < $GARAGE_BASE_DIR/src/main/resources/static/initial.sql ```
    - Windows:
      1. ```TODO```
    - Linux: 
      1. ```TODO```
2. Copy ```src/main/resources/local_application.properties``` to your ```src/main/resources/application.properties``` and replace $PASSWORD with your db password set previously
3. Verify the server starts properly
   - MacOS
      1. ``` cd $GARAGE_BASE_DIR```
      2. ``` mvn spring-boot:run```
   - Windows:
     1. ```TODO```
   - Linux:
     1. ```TODO```
