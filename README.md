# Paint-Brush-Project

[![Java](https://img.shields.io/badge/Language-Java-007396.svg)](https://www.oracle.com/java/)
[![OOP](https://img.shields.io/badge/Design-OOP-blue.svg)](#)
[![GUI](https://img.shields.io/badge/Interface-Swing-orange.svg)](#)
[![Status](https://img.shields.io/badge/Status-Done-brightgreen.svg)](#)

<img width="1048" height="691" alt="image" src="https://github.com/user-attachments/assets/8d9698bc-1423-4ba0-a04b-d98d36cfebe2" />

A Java-based desktop drawing application designed with object-oriented principles. The project aims to provide a clean, extensible painting environment where shapes, tools, and user interactions are Elegant, modular, and interactive — Paint-Brush-Project is a small but expressive painting program demonstrating how clean design yields flexible GUI applications.

---

## Highlights (at a glance)

- Modular shape system (Line, Rectangle, Ellipse, Freehand, etc.)
- Real-time mouse interaction for drawing and manipulation
- Clear separation between model, view, and controller logic
- Usability-focused controls and intuitive UX for quick sketching
- Extensible architecture enabling new tools and shapes with minimal changes

---

## Why this project exists

This repository was created to showcase clean OOP design for GUI applications and to provide a compact, well-structured example of building an interactive drawing tool. It is useful as:
- A learning resource for event-driven GUI programming in Java
- A starting point for building richer graphic editors
- A demonstration of modular architecture and testable components

---

## High-level architecture & design principles

- Single Responsibility: each class focuses on a single aspect (shape, rendering, event handling, UI).
- Encapsulation: drawing primitives encapsulate their own state and rendering logic.
- Polymorphism: shape/tool interfaces let the app treat different shapes uniformly.
- MVC-inspired separation: UI (view), input handling (controller), and shape data (model) are logically separated to improve maintainability and testability.
- Extensibility: adding a new shape or tool requires implementing a small interface and registering it with the tool manager.

---

## How the project was created — step by step

This section documents the practical process that produced the application, focusing on design and development activities rather than code details.

1. Project goals and scope
   - Defined a compact scope: a lightweight paint app with a focused set of core tools (selection, line, rectangle, ellipse, freehand, color picker, stroke size).
   - Emphasized modularity and clear responsibilities to make future expansion straightforward.

2. UI sketching and UX considerations
   - Sketched the main window layout: toolbar (top/side), canvas (center), status bar (bottom).
   - Chose affordances for common actions: click-and-drag to draw, modifier keys for constrained shapes, and undo/redo support planned.
   - Selected a minimal, clear palette of controls to avoid clutter.

3. Defining the core abstractions
   - Designed a small set of interfaces/abstract classes: Shape (model), Tool (input handler), Renderer (draws shapes onto the canvas), and Document (shape container).
   - Defined lifecycle events for input (press, drag, release) so tools remain stateless and reusable.

4. Implementing the drawing canvas
   - Built a custom canvas component that:
     - Maintains a backing model (list of shapes)
     - Handles repainting efficiently
     - Provides coordinate transforms and pixel-accurate rendering
   - Implemented a double-buffering strategy (if applicable) to reduce flicker and produce smooth interaction.

5. Mouse/event handling and tools
   - Implemented a tool manager to switch between drawing modes.
   - Each tool converts raw mouse events into meaningful actions (start shape, update shape, finalize shape).
   - Ensured tools are decoupled from rendering details to keep behavior modular.

6. Shapes and rendering
   - Created shape classes that know how to:
     - Store their own geometry and style (color, stroke)
     - Render themselves on a Graphics2D surface
     - Provide simple operations (move, resize, hit-test)
   - Used Java2D primitives for crisp rendering and stroke controls.

7. Persistence & document management (conceptual)
   - Designed the document layer to allow saving/loading of drawings (serialization or export).
   - Considered file formats and simple export options (e.g., PNG export of the canvas) for sharing.

8. Polishing and UX refinements
   - Added tooltips, keyboard shortcuts, and a status bar with helpful hints.
   - Tuned cursor feedback and selection handles for a more responsive feel.
   - Chose default colors and sensible stroke options for immediate usability.

9. Testing and iteration
   - Performed manual interaction testing across typical use-cases (drawing, undo/redo, shape edits).
   - Iterated on control placement and behavior based on usability checks.

10. Packaging and documentation
    - Prepared a README (this file) with instructions and architectural notes.
    - Included example images/screenshots to demonstrate the main UI and features.

---

## Key features (expanded)

- Drawing primitives: line, rectangle, ellipse, and freehand
- Tool system: switchable tools that react to mouse events
- Style control: color selection and stroke width adjustments
- Hit-testing: select and manipulate existing shapes
- Smooth drawing: responsive drag updates for a natural drawing feel
- Extensible: add new tools and shapes with minimal changes to core code

---

## Quick usage guide

1. Launch the application (built as a standard Java application).
2. Select a tool from the toolbar (e.g., Rectangle, Freehand).
3. Choose color and stroke width.
4. Click and drag on the canvas to draw.
5. Use selection tool to move or resize shapes.
6. Export canvas or save your document (if available in UI).

(Exact menu names and shortcuts are available in the application’s help menu or in-app tooltips.)

---

## Development notes (non-technical overview)

- The codebase is organized so designers and developers can quickly find where UI, business logic, and rendering live.
- New contributors should look for the tool registration point and the shape interface to add features.
- The focus was kept on clarity and educational value rather than feature bloat.

---

## Contributing

Contributions are welcome. Suggested workflow:
- Open an issue describing the idea or bug.
- Fork the repository, implement your changes in a feature branch, and open a pull request.
- Keep changes focused and document user-facing behavior in the PR description.

Please follow the existing design patterns and keep new features modular.

---

## License & credits

- This project is provided as-is for educational purposes. Please check the repository for the explicit license file or contact the maintainer for licensing details.
- Created by the repository owner and contributors — thank you to everyone who helped with design and testing.

---

Thank you for exploring Paint-Brush-Project. 
