# Sports Centre - Front-End [PL/ENG]

*[PL]* Przed uruchomieniem tego programu należy uruchomić back-end zawarty w repozytorium, do którego prowadzi link zawarty w nagłówku poniżej według instrukcji zawartej w README.md tego back-end'owego projektu. <br>

*[ENG]* Before running this program, run the back-end contained in the repository, to which the link in the header below leads, according to the instructions contained in README.md of this back-end project.

## - Repozytorium back-end z opisem back-end'u programu w README.md<br>- Back-End repository with description of the back-end of the program in README.md [PL/ENG]
https://github.com/plecicki/sports-centre
## Table of contents / Spis treści
* [Opis front-end'u projektu oraz zobrazowanie za pomocą gifów [PL]](#opis)
* [Description of the front-end of the project and image using gifs [ENG]](#description)
## Opis front-end'u projektu oraz zobrazowanie za pomocą gifów [PL]<a name="opis"></a>
### Strona startowa
![Home page](https://user-images.githubusercontent.com/84147482/200859935-946e46e6-14bb-4e1d-bafd-7e35c342aa90.gif)
<br>Adres: http://[domena]/sport/home || np. http://localhost:8081/sport/home <br>
Strona startowa jest dostępna cały czas w trakcie działania strony niezależnie od tego, czy użytkownik jest zalogowany i jaka jest jego rola (USER/ADMIN).<br>
Ekran startowy przedstawia formularz z danymi logowania do kont w przypadku użycia bazy danych zawartej w repozytorium bac-end'u projektu (więcej o tym w back-end'owym README.md). Następnie widnieje przycisk przenoszący do adresu /sport/login, gdzie możliwe jest zalogowanie się lub utworzenie nowego konta. Kolejna widnieje prognoza pogody na jutro w miejscu ośrodka sportowego pobrana z zewnętrznego API (miejsce jest możliwe do ustawienia w pliku application.properties back-end'u). Ramka z pogodą jest odporna na błędy ze strony zewnętrznego API i w razie problemów niezależnych od nas pojawi się label informujący o tym, a pozostałe funkcjonalności strony będą nienaruszone. Następnie widnieją kolejne informacje z innego zewnętrznego API. Są to informacje na temat filmów w serwisie YouTube, które są przedstawione w postaci tabeli (Grid), gdzie możemy pozyskać informacje takie jak np. liczba polubień filmu czy tytuł. W razie jakichkolwiek problemów z którymś z API niezależne od nas błędy są obsługiwane i co najwyżej może pojawić się któryś z komunikatów lub oba jednocześnie:<br><br>
![image](https://user-images.githubusercontent.com/84147482/201088779-021914b6-398a-4191-9d08-ce180c43c255.png)

### Logowanie
![Login](https://user-images.githubusercontent.com/84147482/200863223-58877834-da4e-4e46-98ce-64186b818bf0.gif)
<br>Adres: http://[domena]/sport/login || np. http://localhost:8081/sport/login <br>
Na stronę logowania zostajemy przeniesieni po kliknięciu przycisku widocznego na stronie tytułowej. Strona zawiera formularz z danymi logowania w przypadku użycia mojej bazy danych zawartej w repozytorium back-end'u. Wpisane dane są walidowane i w przypadku poprawnego zalogowania zostajemy przeniesieni do /sport/user lub /sport/admin w zależności, jaką rolę posiada właśnie zalogowany użytkownik. Bez zalogowania się przedstawione adresy nie są dostępne, co jest zobrazowane w drugim nagłówku poniżej. Pole tekstowe z hasłem jest wykropkowane z możliwością wyłączania krycia treści tam wpisanej <br>
W przypadku niepowodzenia przy logowaniu zostaniemy poinformowani powiadomieniami w lewym dolnym rogu strony. W zależności od sytuacji mogą wystąpić dwa komunikaty informujące o będzie: <br>
![image](https://user-images.githubusercontent.com/84147482/201091608-9f8fd85d-4883-48f2-aa01-6c2d749ce7fb.png)
![image](https://user-images.githubusercontent.com/84147482/201091664-5779084a-7bad-41a9-8b0b-9cf24f737c0c.png)

### Rejestracja
![Register](https://user-images.githubusercontent.com/84147482/200883038-ae5d9ec9-3d24-4c83-8159-8644f2a15682.gif)
<br>Adres: http://[domena]/sport/registration || np. http://localhost:8081/sport/registration <br>
Rejestracji dokonujemy po kliknięciu w przycisk "Register" w panelu logowania. Podczas rejestracji tworzymy trzy rekordy w bazie danych, po jednym na każdą z encji account, user oraz card. Wraz ze zmianą wartości "Choose account role:" pojawia się lub znika pole tekstowe "Admin creating key", jest to obowiązkowe pole w przypadku tworzenia konta administratora i tylko osoba znająca klucz może stworzyć użytkownika o takich uprawnieniach. Ten klucz jest zawarty w pliku application.properties back-end'u, który pobierany jest ze zmiennej środowiskowej systemu. Następne są klasyczne pola tekstowe. Pole tekstowe do wpisania nazwy użytkownika posiada walidację, czy aby wpisana nazwa nie jest już zajęta informując o tym niewielkim komunikatem. Kolejne jest pole do wpisania daty. Datę wybiera się z wyskakującego okienka, co widać na gifie poniżej i niemożliwe jest wpisanie tej daty ręcznie, dzięki czemu jest gwarancja poprawności formatu wprowadzonej daty. W formularzu widnoją również pola typu Select, gdzie możemy wybrać tylko jedną z możliwych opcji oraz również zablokowane jest wpisanie własnej wartości. Następne są pola typu CheckBox, które nie są obowiązkowe i zaznaczamy je tylko spełniając przypisane do nich kryterium. Kolejne są pola tekstowe na hasła, które są wykropkowane i niemożliwe do zobaczenia z perspektywy osoby obok patrzącej w ekran. Na samym dole jest przycisk, który jest odblokowany tylko w przypadku poprawnego wypełnienia wszystkich pól w formularzu rejestracyjnym poza checkbox'ami, które są opcjonalne.

### Próba pominięcia procesu logowania
![Try avoid login process](https://user-images.githubusercontent.com/84147482/200862559-ba1a16c4-0cbe-4d01-9f6a-495d2324f5d8.gif)
<br> Program blokuje możliwość pominięcia procedury logowania do konta poprzez wpisanie na sztywno adresów stron, do których jesteśmy przenoszeni po poprawnym zalogowaniu się.
### Panel użytkownika po zalogowaniu
#### Przegląd i edycja własnych danych
![User data edit](https://user-images.githubusercontent.com/84147482/200884267-67d42475-159e-4d33-bf46-65687cdb2c7e.gif)
#### Przegląd własnych faktur
![User invoices](https://user-images.githubusercontent.com/84147482/200884779-acd43210-2322-4870-b120-d259079f7c74.gif)
#### Składanie zamówienia
![User order](https://user-images.githubusercontent.com/84147482/200885620-5527b732-9ce6-4018-95da-440151f5eae8.gif)
#### Zmiana hasła do karty
![User change card password](https://user-images.githubusercontent.com/84147482/200887404-f9af4caa-20a8-48cd-a1eb-42fb8fe4c2cd.gif)
#### Wylogowanie się
![User logout](https://user-images.githubusercontent.com/84147482/200887907-b6553f20-e400-45e4-9750-c38354f0af92.gif)
### Panel administratora po zalogowaniu
#### Przegląd, edycja, usuwanie oraz tworzenie nowych kart użytkowników
![Admin card](https://user-images.githubusercontent.com/84147482/200890894-2a30d16f-893e-4c44-b9a3-052c3a7b1127.gif)
#### Przegląd, edycja, usuwanie oraz tworzenie nowych użytkowników
![Admin user](https://user-images.githubusercontent.com/84147482/200892552-b918436d-7b06-4029-ac75-fc440bbfe6a4.gif)
#### Przegląd, edycja, usuwanie oraz tworzenie nowych faktur
![Admin invoices](https://user-images.githubusercontent.com/84147482/200893652-97319e92-6508-401e-98de-d1ee21a21764.gif)
#### Przegląd, usuwanie oraz składanie zamówień
![Admin orders](https://user-images.githubusercontent.com/84147482/200894417-ae6c3552-b933-4896-be1c-d62e7e0715a3.gif)
#### Wylogowanie się z konta administratora
![Admin logout](https://user-images.githubusercontent.com/84147482/200894805-5c4adf15-6128-48f4-8f37-e42e140814e3.gif)

## Description of the front-end of the project and image using gifs [ENG]<a name="description"></a>
