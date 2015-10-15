##SLogo API Review
####Susan Lang (sml59) and Jack Gillette (jeg56)

We are both working on the Turtle, kind of in between the front and back end. We talked a lot about how to make our designs modular. For example, we debated whether the view-related elements of a Turtle, such as its speed, color, or image, should be in the same class as the object holding its state regarding position and orientation. I am thinking I might put these in a separate class. One way or another, we agreed that it's important to remember to keep the Turtle's actual state and the state of its representation on the front end separate, because for instance you might want to change the speed at which the representation on the front end is moving. I was also interested in how Jack is using a client to go between the commands and the Turtle. He brought up the question what if the parser asks the Turtle to do something it doesn't know how to do? That error could be handled within the mediator between the Turtle and the parser, so that the Turtle will never be asked to do something it cannot.

###Part 1
1. *What about your API/design is intended to be flexible?*
    I want to be able to treat the view and the state of the turtle totally separately.
2. *How is your API/design encapsulating your implementation decisions?*
    Separating the turtle's basic commands that have to do with its state from its representation in the GUI.
3. *What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?*
    If the Turtle is asked to do something it cannot, it needs to throw an error. I kind of like the way Jack is handling it with a client, I might copy that.
4. *Why do you think your API/design is good (also define what your measure of good is)?*
   My measure of good right now is concise, modular, and most importantly, extensible - extensibility is my main focus.
   

###Part 2
1. *Demo what you currently have running (it could be anything, but it should definitely be something).*
    Neither of us have anything running per se, but we talked about what we are starting with - I'm starting with the Turtle class, figuring out what methods are within it and what needs to be elsewhere. I tried to work this out in the design phase but for me at least, I never really know until I'm actually trying to code it whether it's going to work.
    
2. *Come up with at least four use cases for your part (it is absolutely fine if they are useful for both teams).*
* The turtle is asked to move forward 50.
* The turtle is asked to perform a command it doesn't know how to do.
* The turtle needs to move across the screen with some sort of animation.
* The turtle's image is changed.
3. *What feature/design problem are you most excited to work on?*
    I'm excited to figure out how to represent the Turtle's motion, even if it might be a little complicated, I think (hope) it's going to be really cool.
    
4. *What feature/design problem are you most worried about working on?*
    I'm worried about interfacing between commands and the turtle - I feel like we're going to run into difficulties here.