---
layout: page
title: Ng Jia Xin's Project Portfolio Page
---

## Project: My Fitness Buddy

My Fitness Buddy is a desktop application that helps PES B pending recruits keep track of their
 overall change in fitness level by allowing them to add daily records of their weight and calorie input/output. 

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the ability to create a new person object.
  * What it does: allows the user to create a new person with an empty records and a profile.
  * Justification: This feature improves the product significantly because a user can now have a unique set of data that belongs to him/her only. 
  * Highlights: This enhancement affects the number of users our product can accommodate, and the ability to display multiple user information on the same interface. The implementation was tedious as it required new methods to check for unique persons.

* **New Feature**: Added the ability to create a new profile.
  * What it does: allows the user to create a new profile that stores the relevant name, unique identity number, height and target weight of a user.
  * Justification: This feature improves the product significantly because a user is now identifiable by the unique identity number and able to store relevant information. 
  * Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation too was challenging as it required new commands to allow users to edit their profile and methods to check for unique person.

* **New Feature**: Added the ability to add calorie input.
  * What it does: allows the user to add a calorie input to the daily record.
  * Justification: This feature improves the product significantly because a user can document the information on calories consumed daily.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added a UniquePersonList that allows the user to navigate the list of existing users in the application.
  * What it does: allows multiple persons' profiles to be displayed in the interface so that Commanders can benefit from this product as well.
  * Justification: This feature improves the scope of the product because it enabled another group of target audience to use it.
  * Highlights: This enhancement created more methods to check for unique person before adding more profiles. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jiaax)

* **Project management**:
  * Documentation reviewing and check for consistency

* **Enhancements to existing features**:
  * Unique identification of person using ID [\#226](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/226)
  * Consolidation of total calorie input for easier daily reference [\#59](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/59)
  * Refactoring of AddressBook to MyFitnessBuddy [\#113](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/113)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add`, `edit`, `delete` and `update` 
    [\#150](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/150) 
    [\#244](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/244)
    * Did cosmetic tweaks to existing documentation of features `create`: 
    [\#117](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/117)
  * Developer Guide:
    * Added implementation details of the `create`, `update` and `login` feature. 

* **Community**:
  * Merged numerous documentation PRs 
  * Documented bugs for other teams during PE Dry run
  * Reported bugs and suggestions for other teams in the class 

