# Sports Centre - Front-End [PL/ENG]

*[PL]* Przed uruchomieniem tego programu należy uruchomić back-end zawarty w repozytorium, do którego prowadzi link zawarty w nagłówku poniżej według instrukcji zawartej w README.md tego back-end'owego projektu. Po uruchomieniu obu programów należy przekierować się na stronę tytułową za pomocą przeglądarki gdzie adres zakończony jest /sport/home, gdzie przykładowy pełny adres wygląda tak http://localhost:8081/sport/home. <br> 

*[ENG]* Before running this program, run the back-end contained in the repository, to which the link in the header below leads, according to the instructions contained in README.md of this back-end project. After running both programs, you should redirect to the home page using a browser where the address ends with /sport/home, where an example full address looks like this http://localhost:8081/sport/home.

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
Ekran startowy przedstawia formularz z danymi logowania do kont w przypadku użycia bazy danych zawartej w repozytorium back-end'u projektu (więcej o tym w back-end'owym README.md). Następnie widnieje przycisk przenoszący do adresu /sport/login, gdzie możliwe jest zalogowanie się lub utworzenie nowego konta. Kolejna widnieje prognoza pogody na jutro w miejscu ośrodka sportowego pobrana z zewnętrznego API (miejsce jest możliwe do ustawienia w pliku application.properties back-end'u). Ramka z pogodą jest odporna na błędy ze strony zewnętrznego API i w razie problemów niezależnych od nas pojawi się label informujący o tym, a pozostałe funkcjonalności strony będą nienaruszone. Następnie widnieją kolejne informacje z innego zewnętrznego API. Są to informacje na temat filmów w serwisie YouTube, które są przedstawione w postaci tabeli (Grid), gdzie możemy pozyskać informacje takie jak np. liczba polubień filmu czy tytuł. Po kliknięciu w daną pozycję zosstajemy przeniesieni do serwisu YouTube, do filmu, w który kliknęliśmy. W razie jakichkolwiek problemów z którymś z API niezależne od nas błędy są obsługiwane i co najwyżej może pojawić się któryś z komunikatów lub oba jednocześnie:<br><br>
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
Rejestracji dokonujemy po kliknięciu w przycisk "Register" w panelu logowania. Podczas rejestracji tworzymy trzy rekordy w bazie danych, po jednym na każdą z encji account, user oraz card. Wraz ze zmianą wartości "Choose account role:" pojawia się lub znika pole tekstowe "Admin creating key", jest to obowiązkowe pole w przypadku tworzenia konta administratora i tylko osoba znająca klucz może stworzyć użytkownika o takich uprawnieniach. Ten klucz jest zawarty w pliku application.properties back-end'u, który pobierany jest ze zmiennej środowiskowej systemu. Następne są klasyczne pola tekstowe. Pole tekstowe do wpisania nazwy użytkownika posiada walidację, czy aby wpisana nazwa nie jest już zajęta informując o tym niewielkim komunikatem. Kolejne jest pole do wpisania daty. Datę wybiera się z wyskakującego okienka, co widać na gifie poniżej i niemożliwe jest wpisanie tej daty ręcznie, dzięki czemu jest gwarancja poprawności formatu wprowadzonej daty. W formularzu widnieją również pola typu Select, gdzie możemy wybrać tylko jedną z możliwych opcji oraz również zablokowane jest wpisanie własnej wartości. Następne są pola typu CheckBox, które nie są obowiązkowe i zaznaczamy je tylko spełniając przypisane do nich kryterium. Kolejne są pola tekstowe na hasła, które są wykropkowane i niemożliwe do zobaczenia z perspektywy osoby obok patrzącej w ekran. Na samym dole jest przycisk, który jest odblokowany tylko w przypadku poprawnego wypełnienia wszystkich pól w formularzu rejestracyjnym poza checkbox'ami, które są opcjonalne.

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
Administrator może przeglądać dane o wszystkich użytkownikach oraz może ich filtrować po numerze id za pomocą pola tekstowego na górze. Po kliknięciu w daną pozycję wysuwa się z prawej strony panel do edycji danych klikniętej pozycji w tabeli. Na szczególną uwagę zasługuje pole typu Select zawierający id kart, ponieważ pokazuje tylko id kart, które są wolne. Przycisk "Save" posiada walidację, czy pola tekstowe mają jakąś wartość. Po kliknięciu "Add new user" zostaje przedstawiony nam ten sam panel, tylko tym razem służy on do stworzenia nowego użytkownika. Użytkownicy mogą zostać usunięci przy pomocy przycisku "Delete", a przypisana do nich karta staje się kartą bez właściciela i można ją przypisać do nowej osoby.

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
### Home page
![Home page](https://user-images.githubusercontent.com/84147482/200859935-946e46e6-14bb-4e1d-bafd-7e35c342aa90.gif)
<br>Address: http://[domena]/sport/home || e.g. http://localhost:8081/sport/home <br>
The start page is available all the time while the page is running, regardless of whether the user is logged in and what is his role (USER/ADMIN).<br>
The start screen presents a form with account login data when is used the database contained in the project's back-end repository (more about it in the back-end README.md). Then there is a button that takes you to /sport/login, where you can log in or create a new account. The next one is the weather forecast for tomorrow at the location of the sports center downloaded from an external API (the location can be set in the back-end's application.properties file). The weather frame is resistant to errors from the external API and in the event of problems beyond our control, a label will appear informing about it, and the other functionalities of the website will be unaffected. There is more information from another external API. This is information about videos on YouTube, which are presented in the form of a table (Grid), where we can obtain information such as the number of video likes or title. After clicking on a given item, we are transferred to YouTube, to the video we clicked on. In case of any problems with any of the API, errors independent of us are handled and at most one or both messages may appear at the same time:<br><br>
![image](https://user-images.githubusercontent.com/84147482/201088779-021914b6-398a-4191-9d08-ce180c43c255.png)

### Login
![Login](https://user-images.githubusercontent.com/84147482/200863223-58877834-da4e-4e46-98ce-64186b818bf0.gif)
<br>Address: http://[domena]/sport/login || e.g. http://localhost:8081/sport/login <br>
We are transferred to the login page after clicking the button visible on the title page. The page contains a form with login data in case of using my database contained in the back-end repository. The entered data is validated and in the case of a correct login, we are transferred to /sport/user or /sport/admin, depending on the role of the just logged in user. Without logging in, the addresses shown are not available, as shown in the second header below. The text field with the password is dotted with the option to disable the hiding of the content typed there <br>
In the event of a failure to log in, we will be informed by notifications in the lower left corner of the page. Depending on the situation, there may be two messages informing us about it: <br>
![image](https://user-images.githubusercontent.com/84147482/201091608-9f8fd85d-4883-48f2-aa01-6c2d749ce7fb.png)
![image](https://user-images.githubusercontent.com/84147482/201091664-5779084a-7bad-41a9-8b0b-9cf24f737c0c.png)

### Registration
![Register](https://user-images.githubusercontent.com/84147482/200883038-ae5d9ec9-3d24-4c83-8159-8644f2a15682.gif)
<br>Address: http://[domena]/sport/registration || e.g. http://localhost:8081/sport/registration <br>
Registration is done after clicking the "Register" button in the login panel. During registration, we create three records in the database, one for each of the account, user and card entities. Along with changing the "Choose account role:" value, the "Admin creating key" text box appears or disappears, it is a mandatory field when creating an administrator account and only the person who knows the key can create a user with such permissions. This key is contained in the back-end's application.properties file, which is retrieved from the system environment variable. Classic text boxes are next. The text field for entering the username has a validation that the entered name is not already taken, informing about it with a small message. The next field is to enter the date. The date is selected from a pop-up window, as you can see in the gif above, and it is impossible to enter this date manually, so the correctness of the entered date format is guaranteed. The form also includes Select fields, where we can choose only one of the possible options and it is also blocked to enter your own value. Next are the CheckBoxes, which are not mandatory and we mark them only if they meet the criteria assigned to them. Next are the text fields for passwords, which are dotted and impossible to see from the perspective of the person next to you looking at the screen. At the very bottom there is a button that is unlocked only if all fields in the registration form are filled in correctly, except for checkboxes, which are optional.

### Attempt to bypass the login process
![Try avoid login process](https://user-images.githubusercontent.com/84147482/200862559-ba1a16c4-0cbe-4d01-9f6a-495d2324f5d8.gif)
<br> The program blocks the possibility of omitting the procedure of logging in to the account by entering the addresses of the pages to which we are transferred after a correct login.

### User panel after logging in
#### View and edit your own data
![User data edit](https://user-images.githubusercontent.com/84147482/200884267-67d42475-159e-4d33-bf46-65687cdb2c7e.gif)
<br>Address: http://[domena]/sport/user/user || e.g. http://localhost:8081/sport/user/user <br>
This panel is available only after logging in as a non-admin user. The panel allows you to view and edit only your own data. After clicking "Edit data", fields filled with our data are displayed to us, which we can edit. The button has an automatic lock in the event that we delete the content of one of the fields and do not enter anything there. After clicking the button at the very bottom of the "Edit" panel, we get a view of our old and new data, so we can compare them (Prototype Java Pattern on the back-end side). After clicking "Ok" we return to the same view when we entered the tab for editing our own data.

#### View your own invoices
![User invoices](https://user-images.githubusercontent.com/84147482/200884779-acd43210-2322-4870-b120-d259079f7c74.gif)
<br>Address: http://[domena]/sport/user/invoice || e.g. http://localhost:8081/sport/user/invoice <br>
The panel allows you to view information about your own invoices.

#### Make an order
![User order](https://user-images.githubusercontent.com/84147482/200885620-5527b732-9ce6-4018-95da-440151f5eae8.gif)
<br>Address: http://[domena]/sport/user/order || e.g. http://localhost:8081/sport/user/order <br>
The panel allows you to place an order in a supplement store by checking the CheckBoxes with the products you want to buy. At least one product must be selected for the order button to be unlocked. After clicking "Order" we will be shown a summary of the order with the products we ordered and the price for everything (Decorator Java Pattern on the back-end side).

#### Card password change
![User change card password](https://user-images.githubusercontent.com/84147482/200887404-f9af4caa-20a8-48cd-a1eb-42fb8fe4c2cd.gif)
<br>Address: http://[domena]/sport/user/card || e.g. http://localhost:8081/sport/user/card <br>
The panel allows you to change the password to the card by entering the old password, the new password and repeating the new password. The content in the text boxes is in the form of dots covering what we type. In the event of failure, e.g. due to entering the wrong old password or entering divergent new passwords, we are informed with appropriate messages depending on the mistake made, which can be seen in the gif. In case of success, a message also appears in the lower left corner of the panel.

#### Logout
![User logout](https://user-images.githubusercontent.com/84147482/200887907-b6553f20-e400-45e4-9750-c38354f0af92.gif)
<br>Address: http://[domena]/sport/logout || e.g. http://localhost:8081/sport/user/logout <br>
The user is logged out, access to the user's functions is blocked as it was before logging in. We are redirected to the home page.

### Admin panel after login
#### View, edit, delete and create new user cards
![Admin card](https://user-images.githubusercontent.com/84147482/200890894-2a30d16f-893e-4c44-b9a3-052c3a7b1127.gif)
<br>Address: http://[domena]/sport/admin/card || e.g. http://localhost:8081/sport/admin/card <br>
Admin can view information about all user cards. The text field located in the upper part of the panel is used to filter cards by their ID number. The administrator has the ability to modify the available cards and create new ones. In this panel, it is not possible to assign a user to a card, the "Users" tab is used for this, where we assign a card to a user. The "Save" button has the function of automatic locking and unlocking depending on whether the card password field is empty. The card can be removed by the administrator and if it is a card owned by any user, then his card field is assigned a null value.

#### Viewing, editing, deleting and creating new users
![Admin user](https://user-images.githubusercontent.com/84147482/200892552-b918436d-7b06-4029-ac75-fc440bbfe6a4.gif)
<br>Address: http://[domena]/sport/admin/user || e.g. http://localhost:8081/sport/admin/user <br>
The administrator can view data about all users and can filter them by id number using the text field at the top. After clicking on a given item, a panel for editing data of the clicked item in the table appears on the right side. Particularly noteworthy is the Select type field containing card ids, because it shows only the ids of cards that are free. The "Save" button has a validation if the text fields have any value. After clicking "Add new user" we are presented with the same panel, only this time it is used to create a new user. Users can be deleted using the "Delete" button, and the card assigned to them becomes an ownerless card and can be assigned to a new person.

#### View, edit, delete and create new invoices
![Admin invoices](https://user-images.githubusercontent.com/84147482/200893652-97319e92-6508-401e-98de-d1ee21a21764.gif)
<br>Address: http://[domena]/sport/admin/invoice || e.g. http://localhost:8081/sport/admin/invoice <br>
The administrator can manage user invoices in the same way as in the case of managing users and cards. The differences are that we can filter invoices not by invoice id, but by user id. Another difference is two additional buttons in the editing panel "Set PAID" and "Set NOT PAID", which are blocked when creating a new invoice. They are used to quickly change the invoice status in case one of them has been paid.

#### Review, delete and place orders
![Admin orders](https://user-images.githubusercontent.com/84147482/200894417-ae6c3552-b933-4896-be1c-d62e7e0715a3.gif)
<br>Address: http://[domena]/sport/admin/order || e.g. http://localhost:8081/sport/admin/order <br>
The admin can view all the orders that have been placed. It can filter them by user id. He can delete them by clicking on the order, and then in the sliding panel, click "Delete". The administrator can also place his own order by checking the appropriate check boxes in the sliding panel on the right after clicking "Create order". After placing an order, there is a notification in the lower left corner about the products in the order and the cost. The admin cannot edit the orders, he can only delete and place his own orders.

#### Log out of the administrator account
![Admin logout](https://user-images.githubusercontent.com/84147482/200894805-5c4adf15-6128-48f4-8f37-e42e140814e3.gif)
<br>Address: http://[domena]/sport/logout || e.g. http://localhost:8081/sport/user/logout <br>
Just like in the case of a regular non-administrator user. The user is logged out, access to the user's functions is blocked as it was before logging in. We are redirected to the home page.
