# crypt

Het plan is om hier een applicatie te bouwen die ondersteuning biedt bij de handel in crpytocurrencies.

## Huidige staat

We zijn net begonnen. De applicatie zelf stelt nog niet zoveel voor, maar het project begint opgestart te raken. We hebben:

* Maven werkt
* We hebben ontdekt dat Poloniex een .cvs bestand met trade history aanbiedt, Excel hebben
we daarom niet meer nodig, we parsen direct de .cvs en schrijven met behulp van Hibernate
naar een MySql database
* Een tweede tabel in de database bevat de huidige handelsposities. Werkt ook via een .cvs,
maar dit bestand moeten we even zelf maken.

## Te bouwen functionaliteit

* Meer query mogelijkheden
* Een GUI
* Een client die kan communiceren met de Poloniex API