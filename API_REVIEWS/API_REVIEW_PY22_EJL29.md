#API Review
NetID : py22, ejl29

##Part 1

1) 
>- make factories for front end for each guy element
>- create factories in the GUI class and add them all to a scene and pass scene to view
>- controller class that creates all the GUI and passes to view 
>- interface with the factories so that there is no duplicated code if parameters are diff for each gui element

2) 
>- instantiate all GUI elements in init class
>- all view needs is a scene element 
>- encapsulates all the implementation of GUI elements and initialization elements

3) 
>- bad input, empty args, wrong args, null args, white space args
>- arguments that do not match list of authorized commands
>- hints to tell you what is wrong with your input - try-catch exception tree 

4) 
>- creation of all gui elements is in one place 
>- guy elements are well encapsulated

##Part 2
4 use cases
How to deal with:
1. Turtle goes off the screen.

When the user enters a command that pushes the turtle off the screen, there should be an appropriate algorithm to keep the turtle on the screen. e.g. a toroidal movement. 
2. Multiple turtles
The user can load another turtle in the window and be able to move each turtle individually. 
3. Pen has different colours
The user can choose different colours for the pen in the GUI. 
4. Bad argument input
The user enters bad arguments into the text field and notifies the user of its bad input, with a hint: "Do you mean...?"

The feature that I am most excited to work on is the Colour Palette for the background colour and pen colour. This appears challenging but implementing this as an interface will allow the user to better interact with the GUI interface.

I am most worried about the extensions and how that impacts the GUI structure. However I think our design is flexible enough to accommodate any big changes that appear in the front-end.
