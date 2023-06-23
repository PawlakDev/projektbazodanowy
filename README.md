# Dzienniczek treningowy
### Projekt implementuje dzienniczek treningowy dla sportowców, w którym można kontrolować odbyte treningi.

## Funkcjonalności
**Rejestracja** składa się z dwóch okien. Na pierwszym oknie użytkownik wybiera login i hasło. Podczas nieprawidłowego loginu lub hasła wyświetla się komunikat. (przy próbie dodania loginu, który już znajduje się w bazie lub gdy np hasło = "haslo"). 
W kolejnym oknie użytkownik uzupełnia swój email, imie, nazwisko, rokm urodzenia oraz wybiera opcje zawodnik/trener. </br>
**Logowanie** składa się z dwóch pól tekstowych: login, hasło. </br></br>
Użytkownik po logowaniu lub rejestracji ma możliwość dodania nowego treningu, wyświetlenie wszystkich odbytych treningów oraz wyświetlenie statystyk (dla zawodników) lub wyświetlenie informacji o zawodnikach (dla trenerów)</br>
Po wybraniu opcji **dodawania nowego treningu** należy wybrać odpowiedni typ treningu (bieg, siłownia, gry zespołowe itp), a następnie wypełnić opis treningu (kilometry, czas trwania, opis). </br>
Po wybraniu opcji **wyświetlania treningów** listują się wszystkie treningi odbyte przez użytkownika z możliwością **sortowania treningów, usuwania i modyfikacji poszczególnych treningów** oraz **wyświetlania ich opisu**. </br>
Użytkownik zalogowany jako zawodnik ma możliwość wyboru opcji **wyświetlania statystyk** z odbytych treningów. Statystyki składają się z wykresu zliczającego sume odbytych kilometrów w tydzień dla 5 ostatnich tygodniach, podobnie suma minut spędzonych na treningi oraz wyświetlanie najlepszych treningów: najszybszy bieg, najwiecej kilometrów podczas treningu, najdłuższy trening, najczęstrzy typ treningu. </br>
Użytkownik zalogowany jako trener ma możliwość **wyświetlenia listy zawodników** wraz z ich imieniem, nazwiskiem, emailem i rokiem urodzenia.

## Technologie
* Java
* PostgreSQL
* Hibernate
* Swing

### Pomysły
* dodać Garmin API
* ulepszyć panel trenera