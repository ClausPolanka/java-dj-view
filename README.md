# Project Description
This project shows an example of how to implement the MVC pattern with Java Swing. The code is based on the corresponding example from the Head First Design Patterns book.

## To Dos
- [ ] Make implicit controller's view dependency explicit.
- [ ] Add JavaFX view and use existing controller and model.
- [ ] Write Unit-Tests for controllers and models.
- [ ] Try to combine observers into one observer passing wanted information by args.
- [ ] Document experiences into seperate section of this file.

## Short summary about MVC

- [ ] TODO

### Controller

- [ ] Translate into english

Der Controller ist eine Strategie (Strategy-Pattern) des Views.

![Controller](/img/controller.png "Controller")

Er ist zum einen dafür verantwortlich, wann welche Controls (z.B.: Buttons) aktiviert oder deaktiviert werden. Außerdem werden alle im View ausgelösten Aktionen (z.B.: Button-Klicks) an den Controller weiter delegiert. Der Controller führt dann die entsprechenden  Änderungen des Models (z.B.: Zustandsänderungen) durch. Sobald sich der Zustand ändert, kann das Model seine Beobachter darüber benachrichtigen. Eine Methode im Beispiel des Buchs, das beide Aufgaben zeigt ist folgende. Im View gibt es ein Menü mit dem Eintrag Start. Sobald darauf geklickt wird, wird diese start()-Methode des Controller ausgeführt.

![Controller Source](/img/controller-src.png "Controller Source")

### View

- [ ] Translate into english.

Der View registriert sich als Beobachter (Observer-Pattern) beim Model. Dafür hat der View eine Abhängigkeit auf das Interface des Models, welches die Methoden zum Registrieren und Deregistrieren anbietet. Der View implementiert diejenigen Observer-Typen als die er sich beim Model registrieren möchte. Der View hat ausschließlich die Aufgabe des Layouts und darüber wie sich seine Controls zusammenbauen lassen. Alles andere, wird vom Controller übernommen.

![View](/img/view.png "View")

Die Registrierung wird im Beispiel des Buchs in der UI Klasse in dessen Konstruktor vorgenommen. Dabei wird im Konstruktor neben dem Controller auch noch das Model Interface als explizite Abhängigkeit deklariert. Das Observer Interface kann nach den Model-Properties, über die das UI bezüglich Änderungen vom Model notifiziert werden möchte, benannt werden. Sobald sich das entsprechende Model-Property im Model ändert, wird die notify() Methode vom Model aufgerufen, und das UI somit darüber informiert. Dieses kann nun seine Controls entsprechend anpassen.

### Model

- [ ] Translate

Das Model implementiert im Buch ein Model-Interface, dass sowohl vom View als auch vom Controller verwendet wird. Das Model selbst, hat keine Abhängigkeiten auf auf die jeweils anderen. Abhängigkeiten bestehen nur auf die Observer-Interfaces. 

![Model](/img/model1.png "Model")

Der Controller und das Model gehören stark zusammen. Möchte ein Model einen bestimmten View wiederverwenden, muss eine neue Controller-Implementierung als Strategie des Views erstellt werden, das die Zustandsänderungen des neuen Models durchführt.

Im Buch wird ein neues Model + Interface hinzugefügt. Dabei wird angenommen, dass der View (der wiederverwendet werden soll) nicht verändert werden darf. Somit muss ein Adapter für das neue Model erstellt werden.

![Model](/img/model2.png "Model")

Dadurch kann der bestehende View für das neue Model wiederverwendet werden. Der neue Controller muss das bestehende Controller-Interface implementieren, da der View nicht angepasst werden kann. Im folgenden Bild wird der komplette Zusammenhang (ohne alten ModelImpl + alten ControllerImpl) dargestellt.

![Model](/img/model3.png "Model")

## Experiences and Open Questions

- [ ] Translate to english

- [ ] Im Buch registriert sich der View im eigenen Konstruktor beim Model als Beobachter. Dadurch braucht der View eine Abhängigkeit auf das Model. Um keine Direkte Abhängigkeit vom View auf das Model zu haben, entkoppelt man das durch ein Model-Interface. Könnte man das nicht eigentlich im Composition Root machen und somit die Abhängigkeit entfernen? 

![Question 1](/img/q1.png "Question 1")

- [ ] Der View registriert sich als Observer beim Model und muss daher die entsprechenden Observer-Interfaces implementieren. Diese bestehen nur aus Methoden, die keine Argumente besitzen. Somit benötigt der View eine Abhängigkeit auf das Model um Zugriff auf die Zustandsänderungen des Models zu erhalten. Zwar ist die Notifizierung von Zuständsänerungen durch das Observerpattern entkoppelt, jedoch wird erneut das Model-Interface benötigt. Könnte man nicht einfach die neuen Zustände als Argumente der Observer-Methoden mitgeben? Dadurch würde der View die Abhängigkeit auf das Model-Interface nicht mehr benötigen.

![Question 2](/img/q2.png "Question 2")

Im Controller eine fixe Abhängigkeit auf den View und nicht auf z.B.: ein View-Interface deklariert. Somit sind der Controller und der View sehr stark gekoppelt. Die Instanziierung des Views findet im Konstruktor des Controllers statt, wobei der Controller sich selbst als Abhängigkeit (jedoch entkoppelt über ein Interface) dem View übergibt. Könnte das nicht wiederum zum einen über ein View-Interface entkoppelt werden, damit man z.B.: statt dem Swing eine JavaFX Kompenete daraus machen könnte. Außerdem könnte die Instanziierung doch im Composition-Root stattfinden. Dadurch würde der Controller testbar werden, was er zur Zeit nicht wäre.

![Question 3](/img/q3.png "Question 3")

- [ ] Könnte die Erzeugung der Views und Controls nicht beim Instanziieren direkt erfolgen. Dann bräuchte man diese beiden public Methode nicht mehr.

![Question 4](/img/q4.png "Question 4")