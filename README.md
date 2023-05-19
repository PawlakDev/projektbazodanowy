# Dzienniczek treningowy
Projekt na zajęcia "projekt bazodanowy" na studia. *projekt jeszcze in progress*. 
Projekt implementuje dzienniczek treningowy dla wioślarzy, w celu kontrolowania swoich aktywności.

## Specyfikacja
Projekt implementuje dzienniczek treningowy dla sportowców, w który po logowaniu (lub rejestracji), umóżliwia zapisywanie, modyfikowanie i przegladanie odbytych treningów.

### Podstawowe funkcjonalnosci 
Po logowaniu użytkownik ma możliwość dodania nowego treningu, modyfikacje, usuwanie i wyświetlanie.
Podczas dodawania treningu użytkownik wypełnia dane na temat treningu: Najpierw typ treningu (trening biegowy, siłownia, itp..), następnie inne waże informacje na temat treningu (przebyte kilometry, czas, opis itp).
Modyfikować można wybranie informacje na temat treningu. Można również usuwać treningi.
Jest możliwość wyświetlenia wszystkich zapisanych treningów, jak również wyszukiwanie po wybranych polach (np typie treningów lub dacie).
Użytkownik zalogowany jako trener ma dostęp do treningów wszystkich zawodników, a użytkownik zalogowany jako zawodnik ma dostęp tylko do swoich treningów.


### Dodatkowe funkcjonalnosci
* Mozliwość wyswietlania danych w arkuszu Excel
* Ściągnięcie danych o treningach z zegarków Garmin (API)
* Podstwowa analiza odbytych treningów (wykresy i statystyki, łączny czas treningow, najczęstrza odbywana aktywność itp)

## Technologie
* Java
* Hibernate
* PostgreSQL
* Maven