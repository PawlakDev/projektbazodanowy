# Dzienniczek treningowy
### Projekt implementuje dzienniczek treningowy dla sportowców, w którym można kontrolować odbyte treningi. *in progress*

## Podstawowe funkcjonalności
Użytkownik po logowaniu lub rejestracji ma możliwość dodania nowego treningu, modyfikowanie odbytego treningu, usuwanie treningu z bazy oraz wyświetlanie treningów.
Po wybraniu opcji dodawania nowego treningu należy wybrać odpowiedni typ treningu (np. bieg, siłownia), a następnie wypełnic opis treningu (np. kilometry, czas trwania).
Można wyświetlać wszystkie treningi, wyświetlać trening po wyniku wyszukiwania (po danych polach np. wszystkie treningi typu bieg).

## Dodatkowe funkcjonalności
* Użytkownik zalogowany jako trener ma dostęp do danych użytkowników zapisanych jako zawodnik, istnieje również wyszukiwarka ze względu na pola użytkownika (zawodnika)
* Wyświetlanie danych w arkuszu excel
* Łączenie się i ściąganie danych z zegarków Garmin
* Statystyki (wykresy, zsumowane dane itp) z treningów

## Technologie
* Java
* PostgreSQL
* Hibernate
* Swing
