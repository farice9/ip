# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

# Progress Log - AY20/21 Y2S1

## Week 2
- Added duke.Duke's skeleton greeting function
- Kept the original duke.Duke logo print code in the commit
- Pushed to github

## Week 3
### Level 1
- Implemented greet, echo and exit functions to the bot
- The bot will repeat what the user inputs and exit when user says "bye"

### Level 2
- Implemented adding commands and list commands function
- The bot can store user's input and prints them out when requested

### Level 3
- Introduced a new tasks.Task class
- Implemented a mark tasks as done function
- Listing now shows if the task is done/not done
- Added extra checks so that commands in upper-case are accepted as well
- Added extra checks to ensure the "done" parameter inserted by user is legible
