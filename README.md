# Fish Fillets 2D Game Engine

An object-oriented 2D grid puzzle game engine built in Java, based on the classic puzzle game *Fish Fillets*. Developed for the Object-Oriented Programming (POO) curriculum at ISCTE-IUL (2025/2026).

---

## Tech Stack

* **Language:** Java
* **Architecture:** Object-Oriented Programming & Design Patterns
* **VCS:** Git

---

## Architectural Highlights

* **Class Hierarchy:** Abstract base classes (`GameObject`, `MobileObject`, `FixedObject`) encapsulating shared spatial logic and grid position state.
* **Interface Segregation:** Modular behavior contracts (`Interactable`, `Movable`, `Tickable`) decoupling movement rules, weight physics, and turn-based updates.
* **Observer Pattern:** Custom observer implementation (`Observer`, `Observed`) decoupling game simulation events from the graphical presentation layer.
* **Dynamic File Parsing:** Plain-text grid loaders to parse room layouts dynamically (`rooms/room*.txt`) and persist user progress.

---

## Project Structure

```text
FishFillets/
├── images/          # Game graphical assets
├── rooms/           # Text-based grid layout definitions
├── scores/          # Persistent high-score records
└── src/
    ├── objects/     # Game entities, abstract bases, and interfaces
    └── pt/iscte/poo/
        ├── game/    # GameEngine, Room, and score logic
        ├── gui/     # Graphical window and tile management
        ├── observer/# Observer pattern contracts
        └── utils/   # 2D coordinate & vector math (Point2D, Vector2D)
        ├── observer/# Observer pattern contracts
        └── utils/   # 2D coordinate & vector math (Point2D, Vector2D)
