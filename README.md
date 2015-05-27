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

- [ ] Insert diagram.

Er ist zum einen dafür verantwortlich, wann welche Controls (z.B.: Buttons) aktiviert oder deaktiviert werden. Außerdem werden alle im View ausgelösten Aktionen (z.B.: Button-Klicks) an den Controller weiter delegiert. Der Controller führt dann die entsprechenden  Änderungen des Models (z.B.: Zustandsänderungen) durch. Sobald sich der Zustand ändert, kann das Model seine Beobachter darüber benachrichtigen. Eine Methode im Beispiel des Buchs, das beide Aufgaben zeigt ist folgende. Im View gibt es ein Menü mit dem Eintrag Start. Sobald darauf geklickt wird, wird diese start()-Methode des Controller ausgeführt.

- [ ] Insert diagram.

### View

- [ ] TODO

### Model

- [ ] TODO

## Experiences and Open Questions

- [ ] TODO
