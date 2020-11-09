---
layout: page
title: My Fitness Buddy User Guide
---
 ![logo](images/logo.png)  
(Contributed by Hope)  
  
Welcome to My Fitness Buddy User Guide!  

Just enlisted under PES ‘B’ Pending and worried about the next few months of Basic Military Training (BMT)?  
Worry no more, My Fitness Buddy is here to accompany you through BMT and help you keep track of your weight loss journey!  
This user guide serves to provide you with the necessary instructions on how to set up and use our application. With that said, let’s get started!    

Choose a topic from the [Table of Contents](#table-of-contents) below.

<div style="page-break-after: always;"></div>

## Table of Contents
* Table of Contents
{:toc}  

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 1. Introduction
(Contributed by Hope)  
### 1.1 Application Overview
My Fitness Buddy `v1.4` is a desktop application that helps you to track your overall change in fitness level by allowing you to add daily records of your weight and calorie input/output.  
My Fitness Buddy also allows users to create a profile and can generate visual charts based on these records so that you can monitor your daily progress and help you achieve your goals.  
This application is optimized for use through a *Command Line Interface (CLI)*, meaning that you operate the application by typing commands into a command box.

### 1.2 Target Audience
My Fitness Buddy is designed for PES ‘B’ Pending (BP) soldiers who have just enlisted and are starting their initial 10 weeks of fitness training in BMT. Our features revolve around providing a visual and convenient way to keep track of factors that affect weight loss.  

We have also implemented a commander's version that would allow commanders to have an overview of all recruits.
### 1.3 Rationale
 Before this application was developed, PES BP soldiers had weekly weighing and had to record down their weights on a form kept by their commanders. This method was not only time consuming, it was also ineffective in tracking the soldiers' overall fitness levels. For instance, if a soldier's weight did not decrease over the weeks, he would be unable to pinpoint the reason why this is so.   
 My Fitness Buddy was developed so that soldiers would be able to see the fluctuations in their fitness level through factors such as calorie intake from consumption, calorie output from exercise, and daily weight records. Those records would be available by day and can even be viewed as  a graph.  
 We hope that through the use of this application, soldiers can take charge of their own fitness and achieve the results they strive for. 

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 2. About
(Contributed by Hope)  
### 2.1 Using this User Guide
This User Guide has been structured such that users can easily find and understand what they need.  
[Section 2](#2-about) contains useful tips and information on reading this document.  
[Section 3](#3-getting-started) consists of instructions to set up this application  
[Section 4](#4-features) includes the details of the 4 main features of My Fitness Buddy which are
* Profile page
* List of days
* Calorie input and output
* Statistics
* Features for commanders

### 2.2 Symbols and Syntax

The table below explains the general symbols and syntax used throughout the document.

Symbol/syntax | Meaning
--------|------------------
`command` | This indicates a command to be entered into the input box. More information can be found at [Command Format](#23-command-format).
:bulb: | This indicates a tip.
:warning: | This indicates a warning.

### 2.3 Command Format
(Contributed by Ethan)

Commands are used to tell My Fitness Buddy to perform specific tasks.  
All commands in the following sections follow the same format.
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
	* Example: in `add d/DATE w/WEIGHT`, `DATE` and `WEIGHT` are parameters to be replaced by the user. 
	 e.g. `add d/2020-10-25 w/70`
	
--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 3. Getting Started
### 3.1 Downloading and opening the application
(Contributed by Jun Hui)

Thank you for using My Fitness Buddy! Here's a quick start guide to get you started.

1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest *MyFitnessBuddy_`v1.4`*.jar [here](https://github.com/AY2021S1-CS2103T-W11-3/tp/releases)

1. Copy the file to the folder you want to use as the *home folder* for My Fitness Buddy. 
 
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
![GUI](images/starting_screen.png)          
         
1. If the app doesn’t start right away, try opening a command terminal in the folder and enter  
`java -jar MyFitnessBuddy_v1.4.jar`

1. You can type a command in the command box and press <kbd>Enter</kbd> to execute it.<br>

    
### 3.2 General Usage
(Contributed by Hope)  
When you open our application for the first time, the application starts up with some sample data that allows users to test the features. Here
are some general usage scenarios of our application that you can try when getting started
* From sample data :
1. `login 1` : Login to the first profile in the list
1. `add d/2020-10-26 w/70` : Create a daily record with the specified date and weight.
1. `calorie d/2020-10-26 tp/in t/1200 f/laksa c/290` : Adds a calorie input entry with the specified date.
1. `stats v/all` : View both calorie and weight charts in a pop-up window.
1. `login 2` : Login to the second profile in the list  

* After `clear` command :
1. `create n/John id/1201 h/170 w/68` : Create a new profile with the specified information
1. `add d/2020-10-26 w/70` : Create a daily record with the specified date and weight.
1. `calorie d/2020-10-26 tp/out t/1230 e/Run c/100` : Adds a calorie input entry with the specified date.
1. `stats v/all` View both calorie and weight charts in a pop-up window.

Head to [Features](#4-features) below to learn more details of each command. 

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 4. Features
(Contributed by Jun Hui)
<div markdown="span" class="alert alert-primary">

:warning: **WARNING:** Commands from section [#4.1.2](#411-creating-a-new-profile--create) to
                       [#4.4.1](#441-view-all-calorie-inputs-and-outputs-of-a-day-view) REQUIRES you to `login` to a profile first.
                       <br>You can login to a profile using `login INDEX`, where `INDEX` is the index number of the profile in the Profile List Panel.

</div>

![login_example](images/login_example.png)


### 4.1 Profile commands
(Contributed by Jia Xin)

Commands in this section are useful for allowing multiple users to use this app. 
Multiple users can create their own profiles and add their own records to it.  

#### 4.1.1 Creating a new Person : `create`
Create a new profile for a new Person.

Format: `create n/NAME id/ID_NUM h/HEIGHT w/STARTING_WEIGHT`
- `HEIGHT` should be in CM.
- `STARTING_WEIGHT` should be in KG.

_:warning: For valid ID_NUM, the first two digits must be from 1 - 4, 
while the last two digits must be from 1 - 20. A valid ID_NUM has only four digits._

Examples:  
`create n/Johnny id/1220 h/170 w/68` 

Meaning: `create a new profile with name Johnny, ID number 1220, height 170cm and starting weight of 68kg`

![CreateProfile](images/CreateProfile.png)

#### 4.1.2 Updating an existing profile: `update`
Updates the currently selected profile.

Format: `update [optional] n/NAME id/ID_NUM h/HEIGHT w/STARTING_WEIGHT`

_:warning: Must update at least one of the field and it cannot be the same as the current one._
_:warning: While there can be 2 persons of the same name, ID changed should be unique and not the same as any other person's ID_

Examples:
`update n/Johnny`

Meaning:`update the name of current profile that you are looking to Johnny`

![UpdateProfile](images/UpdateProfile.png)

#### 4.1.3 Viewing another profile: `login`
Changes the profile that you are currently looking at to another profile.

Format: `login INDEX`

Examples:
`login 3`

Meaning:`changes the current profile that you are looking at to the profile in the profile list at index 3`

![LoginProfile](images/LoginProfile.png)

### 4.2 Day commands
(Contributed by Jia Xin)

Commands in this section are useful in tracking of daily weight records. You should use these commands to log your weight for 
a new day before adding calorie related information and edit your weight if you made a mistake. 

#### 4.2.1 Adding a daily weight record : `add d/DATE w/WEIGHT`
Adds a new daily weight record.

Format: `add d/DATE w/WEIGHT`
- `DATE` has to be in the YYYY-MM-DD format
- `WEIGHT` should be in KG

Example:  
`add d/2020-11-09 w/78` 

Meaning:`adds a new day, 09 November 2020, with a weight of 78kg`

_:warning: Always add a day before adding a calorie information._

![AddDay](images/AddDay.png)

#### 4.2.2 Editing a daily weight record : `edit INDEX w/WEIGHT`
Edits a daily weight record.

Format: `edit INDEX [optional] w/WEIGHT d/DATE`
- `WEIGHT` should be in KG
- `DATE` should be in the YYYY-MM-DD format

_:tip:TIP You can choose to update both `DATE` and `WEIGHT` in any order but at least one field needs to be filled_

Example:  
`edit 3 w/77` 

Meaning: `edits a day at index 3 with a new weight of 77kg`

![EditDay](images/EditDay.png)

#### 4.2.3 Deleting a daily record: `delete INDEX`
Deletes a daily record at the index specified.

Format: `delete INDEX`

Example:
`delete 2`

Meaning: `deletes a day at index 2`

![DeleteDay](images/DeleteDay.png)

### 4.3 Calorie Level Data Entry Commands
(Contributed by Wa Wai)

Commands in this section are useful in keeping track of the various calorie content.
You can use them to log different kinds of calorie content and their relevant information.

There are two main types of calorie that you can keep of : Input and Output
Calorie.

Each of them consist of three main components as summarized below.

INPUT CALORIE | OUTPUT CALORIE
--------------|---------------
tp/in         |tp/out
t/            |t/
f/            |e/
c/            |c/

The table below summarizes the various prefix relevant for this section
and their corresponding meaning.

Prefix | Meaning
--------|------------------
**tp/** | `type of calorie`
**t/** | `time`
**f/** | `food`
**e/** | `exercise`
**c/** | `calorie count`
**i/** | `index`
**d/** | `date`


#### 4.3.1 Adding Calorie
##### 4.3.1.1 Adding a calorie input : `calorie tp/in`

Add calorie input to the calorie tracker.

Format: `calorie tp/in d/DATE t/TIME f/FOOD c/CALORIE COUNT`

Examples:`calorie d/2020-10-26 tp/in t/1200 f/laksa c/290` 

Meaning:`The user ate laksa with the calorie count of 290kcal at 12pm on the date October 26 2020` 

_:bulb:TIP: If the `DATE` field is left empty, the calorie input will be added to the current date entry._

_:warning: The calorie count for the input calorie and the total input calorie for a particular day cannot 
exceed 2,147,483,647._

![Add_input](images/calorieImages/Add_Input.png)

##### 4.3.1.2 Adding a calorie output : `calorie tp/out`
Add calorie output to the calorie tracker.

Format: `calorie tp/out d/DATE t/TIME e/EXERCISE c/CALORIE COUNT`

Examples:`calorie d/2020-10-26 tp/in t/1200 e/running c/400` 

Meaning:`The user ran on October 26 2020 at 12pm and has expanded 400kcal` 

_:bulb:TIP: If the `DATE` field is left empty, the calorie output will be added to the current date entry._

_:warning: The calorie count for the output calorie and the total output calorie for a particular day cannot 
exceed 2,147,483,647._

![Add_input](images/calorieImages/Add_Output.png)

Examples:`remove 3 tp/out i/3` 
#### 4.3.2 Removing Calorie
##### 4.3.2.1 Removing a calorie output : `remove tp/out`

Removes a wrong calorie Output entry from the calorie tracker.

Format: `remove tp/out d/DATE i/INDEX`

Examples:`remove tp/in d/2020-10-26 i/3` 

Meaning:`Remove a calorie output from October 26 2020 calorie log, which is the 3rd calorie output in the list of
Output calories for that day.` 

_:bulb:TIP: Instead of using d/DATE to specify the date of the log in which the calorie belongs too, the index of the date
can be used instead_

_:warning: The index to indicate both the day and calorie must be a positive integer and it cannot exceed the respective 
number of records for each category_

`Before removing`
![remove_output_before](images/calorieImages/remove_output_before.png)
`After removing`
![remove_output_after](images/calorieImages/remove_output_after.png)

##### 4.3.2.2 Removing a calorie input : `remove tp/in`

Removes a wrong calorie input entry from the calorie tracker.

Format: `remove tp/in d/DATE i/INDEX`

Examples:`remove tp/in d/2020-10-26 i/3` 

Meaning:`Remove a calorie input from October 26 2020 calorie log, which is the 3rd calorie input in the list of
Input calories for that day.` 

_:bulb:TIP: Instead of using d/DATE to specify the date of the log in which the calorie belongs too, the index of the date
can be used instead_

_:warning: The index to indicate both the day and calorie must be a positive integer and it cannot exceed the respective 
number of records for each category_

The process of removing a calorie input is similar to removing calorie output. Refer to section 4.3.2.1 for a pictorial
representation.

Examples:`remove 3 tp/out i/3` 

#### 4.3.3 Change Calorie
##### 4.3.3.1 Changing a calorie input : `change tp/in`
Change a calorie input entry with details recorded wrongly

Format: `change d/DATE tp/in i/INDEX [Updated Details]`

Examples:`change d/2020-10-26 tp/in i/2 c/123` 

Meaning:`change the calorie count of an input calorie in the log from October 26 2020 with the index of 2 in the
input list to 123` 

`Before changing`
![change_before](images/calorieImages/change_before.png)
`After changing`
![change_after](images/calorieImages/change_after.png)


_:bulb:TIP: Instead of using d/DATE to specify the date of the log, in which the calorie belongs too, the index of the date
can be used instead_

_:warning: The index to indicate both the day and calorie must be a positive integer and it cannot exceed the respective 
number of records for each category._

_:warning: Calorie Count must be a positive integer and cannot exceed 2,147,483,647._

Examples:`change 3 tp/out i/3 c/123`

##### 4.3.3.2 Changing a calorie output : `change tp/out`

Change a calorie out entry with details recorded wrongly

Format: `change d/DATE tp/in i/INDEX [Updated Details]`

Examples:`change d/2020-10-26 tp/in i/2 c/123` 

Meaning:`change the calorie count of an Output calorie in the October 26 2020 log ,with the index of 2 in the
output list to 123` 

The process of changing a calorie output is similar to changing a calorie input. Refer to section 4.3.23.1 for a pictorial
representation.

_:bulb:TIP: Instead of using d/DATE to specify the date of the log in which the calorie belongs too, the index of the date
can be used instead_

_:warning: The index to indicate both the day and calorie must be a positive integer and it cannot exceed the respective 
number of records for each category_

_:warning: Calorie Count must be a positive integer and cannot exceed 2,147,483,647._

### 4.4 Data Visualization
(Contributed by Jun Hui)

Commands in this section are useful to view the graphical representations of the
daily weights and calorie input/output records generated by My Fitness Buddy.

These features help you to monitor your daily progress easily and allows you to understand
certain trends so that you can make better choices during your weight loss journey!

#### 4.4.1 View all calorie inputs and outputs of a day: `view`
(Contributed by Ethan)

Shows a list of all entries of calorie inputs and calorie outputs of a particular day.
Updates the status bar above to show you the date of the day you are viewing. 

Format: `view INDEX`

Main screen before a view command is used:

![day_before_view](images/dayBeforeView.png)

Main screen after a view command is used:

![day_after_view](images/dayAfterView.png)

_:warning: Calorie lists will not show anything if you have not added any calorie
inputs or outputs for that particular day you are viewing. The status bar will still 
update._ 

_:bulb:TIP: Calorie lists can be viewed alternatively by double clicking on a particular day card._

#### 4.4.2 View Statistics: `stats`
(Contributed by Jun Hui)

Shows the charts generated from the daily entries of weight and calorie input/output in a pop-up window.

Format: `stats v/CHART_TO_BE_VIEWED`

You can select which charts to view:

Prefix/Parameter | Meaning
-----------------|------------------
**v/all**        | `Shows both weight and calorie charts`
**v/calorie**    | `Shows calorie chart only`
**v/weight**     | `Shows weight chart only`

Example: `stats v/all`

This command will display both charts in their respective pop-up windows.

_:bulb:TIP: You can hover your mouse over the data point to see the actual value_

Weight Chart:
![weight_stats_chart](images/weight_stats_chart.png)
Calorie Chart:
![calorie_stats_chart](images/calorie_stats_chart.png)

_:bulb:TIP: Charts can be viewed alternatively by going to the menu bar, click on `Statistics` and select the 
 chart that you want to view._

![chart_menu_bar](images/chart_menu_bar.png)

#### 4.4.3 Personal related data visualisation:
(Contributed by Wa Wai)

On top of the various data analytical tools to visualize the various statistic over the training period, we also
have a number of indicators and diagrams to help the user get a better grasp on their current fitness level
##### 4.4.3.1 Current BMI
![BMI](images/calorieImages/BMI.png)

In the current profile, we have a small BMI label which will indicate the current BMI level of the user. 
It will update accordingly to the user's latest weight entry and give them their most updated BMI reading.

##### 4.4.3.1 Calorie Budget
![calorie_budget](images/calorieImages/calorie_budget.png)

As shown in the diagram , each day entry will indicate the calorie budget for the day. (The amount of calorie the
users can consume before gaining weight) It will update accordingly when various calorie entries are recorded to remind
the users how much calorie they can afford to eat for that particular day.

![positive_calorie_budget](images/calorieImages/positive_calorie_budget.png)

When there is calorie budget surplus, which means that the users can still afford to consume calorie without gaining
weight, a green tick will be shown at the top right corner of the day entry.

![negative_calorie_budget](images/calorieImages/negative_calorie_budget.png)

However when there is negative calorie budget, the users cannot afford to consume calorie as they are gaining
weight, a red cross will be shown at the top right corner of the day entry.


##### 4.4.3.1 Progress Bar

The progress bar indicates how close the users are towards the healthy Bmi range. When the user lose weight and
have a healthier bmi, the blue bar will move towards the right. When the progress bar is completely filled,it
indicates that the user has reached the healthy BMI range.

![progress_bar](images/calorieImages/progress_bar.png)

As shown from the diagram, the progress bar changes accordingly when the user's weight changes.


### 4.5 General commands
(Contributed by Jun Hui)

#### 4.5.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


#### 4.5.2 Clearing all entries : `clear`

|              :warning: :warning: WARNING :warning: :warning:              |
|:--------------------------------------------------------------------------|
| This command will clear all data in My Fitness Buddy, even the saved file!|


Clears all saved entries in MyFitnessBuddy.

Format: `clear`


#### 4.5.3 Exiting the program : `exit`
Exits the program.

Format: `exit`

### 4.6 Additional Features for BMT Commanders
(Contributed by Jun Hui)

Commands in this section are useful for BMT commanders to view the overall progress of 
the recruits.

These features help you to keep track the overall progress of the recruits with the help of
graphical representations, so you and other commanders can have a better insight to your training
effectiveness.

#### 4.6.1 View overall BMI progress of the recruits: `stats v/commander`
Shows a pie chart that classifies all the recruits (with their profiles existing in the app) into different BMI categories.

Format: `stats v/commander`

Pie Chart:
![overall_progress_chart](images/overall_progress_chart.png)

#### 4.6.2 Find recruits that exceeded certain BMI threshold: `find bmi/`
Filters the list in Profile List Panel to show profiles that exceeded the specified BMI threshold.

Format: `find bmi/BMI_VALUE`

Example: `find bmi/27`

Meaning: Filters the list to show profiles that with more than or equal to BMI of 27.

![find_bmi](images/find_bmi.png)

_:bulb:TIP: Use `list` command to show all profiles after executing `find bmi/` command._

### 4.7 Saving the data
(Contributed by Ethan)

My Fitness Buddy data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
## 5. FAQ
(Contributed by Ethan)

### 5.1 General

**Q**: Why is the app not running?<br>
**A**: Ensure JDK 11 or above is installed. 

**Q**: I forgot how to use the app. Where can I find help?<br>
**A**: Enter help into the command box and a link to the user guide will be provided.

**Q**: Where is my data stored?<br>
**A**: Your data is stored locally in your computer. It will be in the My Fitness Buddy JSON file
that will be created when you first launch the app. 

**Q**: Do I have to use a save command for my data to be safely backed up in my computer?<br>
**A**: No, all your data is automatically backed up in your computer. There is no need to manually save 
your data.

### 5.2 Transferring Data

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the
 data of your previous My Fitness Buddy home folder. 

### 5.3 Viewing calorie lists

**Q**: Why is my calorie lists not showing when I use a view command?<br>
**A**: Make sure that you have added calories for that day. If the calorie lists are empty,
the lists will not show anything. The status bar will still update informing you that you are 
viewing that particular day. 
--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
## 6. Command summary
(Contributed by Ethan)

Action | Format
--------|------------------
**Create profile** | `create n/NAME id/ID_NUM h/HEIGHT w/STARTING_WEIGHT` 
**Update profile** | `update n/NAME id/ID_NUM h/HEIGHT w/STARTING_WEIGHT`
**Login into a profile** | `login INDEX`
**Add day record** | `add d/DATE w/WEIGHT` 
**Edit day record** | `edit INDEX w/WEIGHT d/DATE`
**Delete day record** | `delete INDEX`
**Add input** | `calorie tp/in t/TIME f/FOOD c/CALORIE_COUNT`
**Add output** | `calorie tp/out t/TIME d/DURATION c/CALORIE_BURNT`
**Remove input** | `remove tp/in d/DATE i/INDEX` 
**Remove output** | `remove tp/out d/DATE i/INDEX` 
**Change input** | `change tp/in t/TIME f/FOOD c/CALORIE_COUNT`
**Change output** | `change tp/out t/TIME d/DURATION c/CALORIE_BURNT` 
**View calories of a day** | `view INDEX`
**View statistics** | `stats v/CHART_TO_BE_VIEWED`
**View overall BMI progress of recruits for commanders** | `stats v/commander`
**Find specific recruits that exceeded BMI threshold** | `find bmi/BMI_VALUE`
**Clear entries** | `clear`
**Help** | `help`
**Exit** | `exit`





