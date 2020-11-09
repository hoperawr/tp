---
layout: page
title: Jun Hui's Project Portfolio Page
---

## Project: My Fitness Buddy

My Fitness Buddy is a desktop application that helps PES B pending recruits keep track of their
overall change in fitness level by allowing them to add daily records of their weight and calorie input/output.  

Given below are my contributions to the project.

* **New Feature**: Added `stats` command that allows the user to see the data visualizations
  * What it does: The users can just execute the command and the daily weight and/or calorie input/output
  charts will appear.
  * Justification: Initially the charts are implemented in such a way that to access the charts, the user must select it
  from the menu bar. This feature targets the CLI-focused aspect.

* **New Feature**: Data visualization of daily weight and calorie input/output
  * What it does: Allows the user to see the daily weight, calorie input/output trends
  with auto-generated charts.
  * Justification: This feature improves the product significantly because it helps the user to monitor their
  progress and understand certain trends pertaining to their daily weight and calorie input/output without analysing
  every single entry they have entered into the app.
  * Highlights: This enhancement requires analysis on how the required data is handled in the Model component and the
  implementation was challenging as it required further knowledge on how to implement charts using JavaFX by referring
  to the JavaFX documentation. It also has to make sure the charts are updated simultaneously whenever there is data modification.
  * Credits: Reused code with modifications from @jewelsea on GitHub for a UX enhancement, where the data values
  will appear when mouse cursor hovers over the data point in the charts.
  
* **New Feature**: Data visualization of the overall BMI progress of the recruits
  * What it does: Allows the user (commanders) to see the overall BMI progress of the recruits in a generated pie chart.
  * Justification: This feature is an add-on for commanders for them to see the overall progress of the recruits,
  which they have their profiles saved in the app. It generates a pie chart where recruits are classified into different
  BMI categories according to the BMI values on their profiles.

* **New Feature**: Added Profile Panels in the GUI that allows the user the see the details of
their profile such as weight, height, BMI and ID.
  * What it does: Allows the user to see the current login profile, as well as other profiles stored in the app.
  * Justification: Gives the user a visual indication of the current profile that is logged in, as well as helping
  the user by displaying a list of profiles saved in the app so that the user can log in to the correct profile. 
  * Highlights:  This enhancement requires analysis on how the required data is handled in the Model component and to
  make sure details are updated simultaneously when the user modifies any data pertaining to profile.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=junhui-phoon&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Setup the team's GitHub organisation and repo.
  * Git Repo Maintainer - Handled most of the merging of pull requests; in particular, pull
  requests that affects the main code base of My Fitness Buddy.
  * Managed releases `v1.3.trial` - `v1.4` (3 releases) on GitHub.
  * Various Bug Fixes (Pull Requests: [\#119](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/119), [\#219](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/219),
  [\#161](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/161), [\#124](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/124), [\#234](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/234),
  [\#246](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/246), [\#263](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/263)
  [\#267](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/267), [\#268](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/268))
  * General housekeeping tasks - Edited AboutUs, README, index.md, GitHub Workflows (Pull Requests: [\#36](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/36), [\#35](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/35),
     [\#23](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/23), [\#240](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/240))
  

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull request: [\#54](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/54))
  * Rearranged the GUI elements (Command Box, Result Display Box etc.) (Pull request: [\#54](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/54))
  * Refactored Phone class to Weight Class (Pull request: [\#50](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/50))
  * Changed the GUI icon (Pull request: [\#154](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/154))
  * Removed unused parameters for `add` command (Pull request: [\#73](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/73))
  * Modified Sample Data Util (Pull Request: [\#246](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/246))
  
* **Community**:
  * Assisted with team members' problems pertaining to Git and GitHub 
  * PRs reviewed (with non-trivial comments): 
  [\#218](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/218),
  [\#213](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/213),
  [\#212](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/212),
  [\#211](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/211),
  [\#151](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/151),
  [\#145](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/145),
  [\#143](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/143),
  [\#139](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/139),
  [\#138](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/138),
  [\#135](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/135),
  [\#133](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/133),
  [\#128](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/121),
  [\#127](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/127),
  [\#122](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/122),
  [\#121](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/121),
  [\#101](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/101),
  [\#94](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/94),
  [\#47](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/47),
  [\#38](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/38)
  
* **Documentation**:
  * User Guide:
    * Edited sections: (Pull Requests: [\#99](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/99),
                         [\#148](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/148) , [\#152](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/152),
                         [\#158](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/158), [\#240](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/240))
        * Getting Started
        * Data Visualisation
        * General Commands
  * Developer Guide:
    * Edited sections: (Pull Requests: [\#99](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/99/files), [\#240](https://github.com/AY2021S1-CS2103T-W11-3/tp/pull/240)) 
        * UI Components
        * Feature: Add a new daily record
        * Feature: View Daily Weight and Calorie Statistics
