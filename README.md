**3. `FishFillets` Repository Setup**
Replace the brief default note with an architectural summary:

```markdown
# Fish Fillets 2D Game Engine 🎮

An object-oriented 2D grid puzzle game engine built in Java, based on the classic puzzle game *Fish Fillets*.

## Tech Stack
- Java
- Object-Oriented Programming & Design Patterns
- Git

## Key Architectural Patterns
- **Inheritance Hierarchy:** Abstract base classes (`GameObject`, `MobileObject`, `FixedObject`) encapsulating shared spatial logic.
- **Interface Segregation:** Modular behavior interfaces (`Interactable`, `Movable`, `Tickable`) decoupling physics and turn-based resolution.
- **Observer Pattern:** Decouples game simulation ticks from GUI rendering components.
- **Dynamic File Parsing:** Reads plain-text grid representations to construct active levels and persists high scores to disk.
