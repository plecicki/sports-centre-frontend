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
<br>Adres: http://[domena]/sport/user/user || np. http://localhost:8081/sport/user/user <br>
Ten panel jest dostępny tylko po zalogowaniu użytkownika niebędącego adminem. Panel umożliwia podgląd oraz edycję tylko własnych danych. Po kliknięciu "Edit data" zostają nam wyświetlone pola wypełnione naszymi danymi, które możemy edytować. Przycisk posiada automatyczną blokadę w przypadku, gdybyśmy wykasowali zawartość któregoś z pól i nic tam nie wpisali. Po kliknięciu przycisku na samym dole panelu edycji "Edit" otrzymujemy widok naszych starych oraz nowych danych, dzięki czemu możemy je porównać (Wzorzec Prototyp po stronie back-end'u). Po kliknięciu "Ok" wracamy do widoku, który zastaliśmy wchodząc w zakładkę edycji własnych danych.

#### Przegląd własnych faktur
![User invoices](https://user-images.githubusercontent.com/84147482/200884779-acd43210-2322-4870-b120-d259079f7c74.gif)
<br>Adres: http://[domena]/sport/user/invoice || np. http://localhost:8081/sport/user/invoice <br>
Panel umożliwia przeglądanie informacji o własnych fakturach.

#### Składanie zamówienia
![User order](https://user-images.githubusercontent.com/84147482/200885620-5527b732-9ce6-4018-95da-440151f5eae8.gif)
<br>Adres: http://[domena]/sport/user/order || np. http://localhost:8081/sport/user/order <br>
Panel umożliwia składanie zamówienia w sklepie z suplementami zaznaczając CheckBoxy z produktami, które chcemy kupić. Musi być zaznaczony minimum jeden produkt, żeby przycisk do złożenia zamówienia był odblokowany. Po kliknięciu "Order" zostaje nam wyświetlone podsumowanie zamówienia z produktami które zamówiliśmy oraz ceną za wszystko (Wzorzec Dekorator po stronie back-end'u).

#### Zmiana hasła do karty
![User change card password](https://user-images.githubusercontent.com/84147482/200887404-f9af4caa-20a8-48cd-a1eb-42fb8fe4c2cd.gif)
<br>Adres: http://[domena]/sport/user/card || np. http://localhost:8081/sport/user/card <br>
Panel umożliwia zmianę hasła do karty wpisując stare hasło, nowe hasło oraz ponawiając nowe hasło. Treść w polach tekstowych jest w postaci kropek zakrywających to co wpisujemy. W przypadku niepowodzenia np. z powodu wpisania złego starego hasła lub wpisania rozbieżnych nowych haseł zostajemy poinformowani odpowiednimi komunikatami w zależności od popełnionego błędu, co widać na gif'ie. W przypadku sukcesu również pojawia się komunikat w lewym dolnym rogu panelu.

#### Wylogowanie się
![User logout](https://user-images.githubusercontent.com/84147482/200887907-b6553f20-e400-45e4-9750-c38354f0af92.gif)
<br>Adres: http://[domena]/sport/logout || np. http://localhost:8081/sport/user/logout <br>
Użytkownik zostaje wylogowany, dostęp do funkcji użytkownika zostaje zablokowany tak jak to było przed zalogowaniem się. Zostajemy przeniesieni do strony startowej.

### Panel administratora po zalogowaniu
#### Przegląd, edycja, usuwanie oraz tworzenie nowych kart użytkowników
![Admin card](https://user-images.githubusercontent.com/84147482/200890894-2a30d16f-893e-4c44-b9a3-052c3a7b1127.gif)
<br>Adres: http://[domena]/sport/admin/card || np. http://localhost:8081/sport/admin/card <br>
Administrator może przeglądać informacje o wszystkich kartach użytkowników. Pole tekstowe umieszczone w górnej części panelu służy do filtrowania kart po ich numerze id. Administrator posiada możliwość modyfikacji dostępnych kart oraz tworzenie nowych. W tym panelu nie ma możliwości przypisania użytkownika do karty, do tego służy zakładka "Users", gdzie przypisujemy kartę do użytkownika. Przycisk "Save" posiada funkcję automatycznego blokowania się i odblokowywania w zależności, czy pole z hasłem karty jest puste. Karta może zostać usunięta przez administratora i jeśli jest to karta posiadana przez jakiegoś użytkownika, wtedy jego pole z kartą ma przypisywaną wartość null.

#### Przegląd, edycja, usuwanie oraz tworzenie nowych użytkowników
![Admin user](https://user-images.githubusercontent.com/84147482/200892552-b918436d-7b06-4029-ac75-fc440bbfe6a4.gif)
<br>Adres: http://[domena]/sport/admin/user || np. http://localhost:8081/sport/admin/user <br>
Administrator może przeglądać dane o wszystkich użytkownikach oraz może ich filtrować po numerze id za pomocą pola tekstowego na górze. Po kliknięciu w daną pozycję wysuwa się z prawej strony panel do edycji danych klikniętej pozycji w tabeli. Na szczególną uwagę zasługuje pole typu Select zawierający id cart, ponieważ pokazuje tylko id kard, które są wolne. Przycisk "Save" posiada walidację, czy pola tekstowe mają jakąś wartość. Po kliknięciu "Add new user" zostaje przedstawiony nam ten sam panel, tylko tym razem służy on do stworzenia nowego użytkownika. Użytkownicy mogą zostać usunięci przy pomocy przycisku "Delete", a przypisana do nich karta staje się kartą bez właściciela i można ją przypisać do nowej osoby.

#### Przegląd, edycja, usuwanie oraz tworzenie nowych faktur
![Admin invoices](https://user-images.githubusercontent.com/84147482/200893652-97319e92-6508-401e-98de-d1ee21a21764.gif)
<br>Adres: http://[domena]/sport/admin/invoice || np. http://localhost:8081/sport/admin/invoice <br>
Administrator może zarządzać fakturami użytkowników analogicznie jak to ma miejsce w przypadku zarządzania użytkownikami i kartami. Różnice są takie, że możemy filtrować faktury nie po id faktury, a po id użytkownika. Kolejną różnicą są dwa dodatkowe przyciski w panelu edycji "Set PAID" oraz "Set NOT PAID", które są zablokowane przy tworzeniu nowej faktury. Służą one do szybkiej zmiany statusu faktury w razie, jakby któraś z nich została opłacona.

#### Przegląd, usuwanie oraz składanie zamówień
![Admin orders](https://user-images.githubusercontent.com/84147482/200894417-ae6c3552-b933-4896-be1c-d62e7e0715a3.gif)
Administrator może przeglądać wszystkie zamówienia, które zostały złożone. Może je filtrować po id użytkowników. Może je usuwać klikając w zamówienie, a następnie w wysuwającym się panelu kliknąć "Delete". Administrator może rónież złożyć własne zamówienie zaznaczając odpowiednie check boxy w wysuwającym się panelu po prawej stronie po kliknięciu "Create order". Po złożeniu zamówienia pojawia się powiadomienie w lewym dolnym rogu o produktach w zamówieniu i koszcie. Administrator nie może edytować zamówień, może tylko usuwać i składać własne zamówienia.

#### Wylogowanie się z konta administratora
![Admin logout](https://user-images.githubusercontent.com/84147482/200894805-5c4adf15-6128-48f4-8f37-e42e140814e3.gif)
<br>Adres: http://[domena]/sport/logout || np. http://localhost:8081/sport/user/logout <br>
Analogicznie jak to miało miejsce w przypadku zwykłego użytkownika niebędącego administratorem. Użytkownik zostaje wylogowany, dostęp do funkcji użytkownika zostaje zablokowany tak jak to było przed zalogowaniem się. Zostajemy przeniesieni do strony startowej.

## Description of the front-end of the project and image using gifs [ENG]<a name="description"></a>
