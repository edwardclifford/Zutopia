# Zutopia
CS2103 Assignment 4

<h1>Introduction</h1>
In this project you will implement a simple, 2-D graphical, single-player arcade game and thereby gain experience in event-driven
programming, while simultaneously exercising good object-oriented design skills.
To get an immediate sense of what you will be developing, check out <a href="https://www.youtube.com/watch?v=EavxdmY50Bw">this video</a>.
<p>

To implement the graphical user interface in this project, we will be using the JavaFX library. (Note: you <b>must</b> use this library
in this project so that we can grade everyone's code fairly.) JavaFX is a large and somewhat complex library that requires understanding
of event-handling (not too difficult) and multiple coordinate systems (somewhat more difficult). To keep the project tractable, we are
giving you some code to help you get started in manipulating the graphics objects you will need to implement the game.<p>

<h1>Game description</h1>
<img src="https://www.cs.wpi.edu/~cs2103/b19/Project4/Zutopia.png" /><br>
<h2>Goal</h2>
In the game you will implement -- which we call <b>Zutopia</b> -- the player's task is to remove all of the 16 animals that initially
appear at the top of the game board (see image above). To remove an animal (note: we are not killing animals! -- the animals are rather
transported into a magical universe in which they can live much more happily), the player must cause the magical teleportation
device (which we call a "ball") to collide with the animal.

<h2>Game control</h2>
In order to "steer" the ball, the player can manipulate -- using the mouse --
a rectangular paddle. Whenever the ball collides with the paddle, it bounces off -- the speed of the ball is unchanged,
but the direction of the ball changes. Note that you <b>only</b> need to handle bouncing off the <b>top</b> and <b>bottom</b>
of the paddle; it is fine (for the sake of simplicity in a 1-week programming project) to ignore the possibility that the ball bounces off 
the left or right side of the paddle.

<h2>Game threats</h2>
As the number of animals that remain on the game board decreases, the speed of the ball accelerates; this
makes it harder  for the user to control the ball's trajectory using the paddle (since she/he has less time to move
the paddle to a desirable location). If the ball hits the <b>lower</b> wall <b>5 times</b>, then the
player loses the game (because the wall tells the magical teleportation device "ok you don't get to teleport the animals anymore"
and all the animals will be doomed to live on the game board forever and nobody wants that). 

<h2>Game completion</h2>
If the player causes the ball to collide with all of the animals and thereby teleports them to the aforementioned magical universe,
then she/he has won the game.

<h2>Replay</h2>
After either losing or winning the game, the user receives a message indicating her/his failure or success and is allowed to restart
the game.

<h1>Requirements</h1>
Your game <b>must</b> implement the following <b>features</b>:
<ol>
	<li>There must be at least <b>3 different kinds</b> of animals, and each animal type should
	be represented by a <b>different image</b>. If you prefer vegetables to animals, that is perfectly fine too.</li>
	<li>Game play should begin when the user clicks the mouse.</li>
	<li>Collision of the ball with an animal should remove the animal from the game board. The collision may optionally cause the ball to change direction.</li>
	<li>As the number of animals that remain on the board decreases, the speed of the ball should increase. (Make sure that the ball's speed resets at the start of each game.)</li>
	<li>Collision of the ball with a wall should cause the ball to bounce off with equal speed but an altered direction.</li>
	<li>Collision of the ball with the paddle should cause the ball to bounce off with equal speed but an altered direction.</li>
	<li>Collision of the ball with the <b>lower</b> wall too many (<b>5</b>) times should cause the user to lose the game immediately.</li>
	<li>Removing all the animals from the game board causes the user to win the game immediately.</li>
	<li>The user should be able to move the paddle by using the mouse.</li>
	<li>The ball must stay within the bounds of the game board at all times.</li>
	<li>The paddle must stay within the bounds of the game board at all times.</li>
</ol>

In addition, your code must fulfill the following criteria:
<ol>
	<li>You must implement the <tt>GameImpl</tt> class, which must implement the <tt>Game</tt> interface.</li>
	<li>You may <b>not</b> modify the <tt>Game</tt> interface.</li>
	<li>You may <b>not</b> modify the <tt>GameApp</tt> class.</li>
	<li>Your code should be organized in an intuitive manner that <b>eliminates code redundancy</b>. To this end, you should make use of
	multiple classes.</li>
	<li><b>Every method</b> within <b>every class</b> that you create <b>must</b> must have a <b>Javadoc</b> comment describing its <b>purpose</b>,
	the meaning of its <b>parameters</b>, and the meaning of its <b>return value</b>. An example of a Javadoc comment is shown below:
	<pre>
	/**
	* Updates the state of the game at each timestep. In particular, this method should
	* move the ball, check if the ball collided with any of the animals, walls, or the paddle, etc.
	* @param deltaNanoTime how much time (in nanoseconds) has transpired since the last update
	* @return the current game state
	*/
	public GameState runOneTimestep (long deltaNanoTime) {
		...
	}
	</pre>
	As shown above, Javadoc comments begin with <tt>/**</tt> and end with <tt>*/</tt>. They include one <tt>@param</tt>
	tag for each parameter and a <tt>@return</tt> tag (unless the method returns <tt>void</tt>).</li>
	<li><b>Within</b> each method, comments should be used <b>as necessary</b> to explain only the <b>non-trival</b> inner-workings
	of your code -- i.e., aspects of your code that are not completely obvious. (For instance, the fact that <tt>i++</tt> increments
	<tt>i</tt> is completely obvious.) Approximately one line of comments for every 10 lines of code is a rough guideline.</li>
	<li>As always, you must keep your code <b>neat</b> -- use whitespace consistently, and use consistent naming conventions
	for all variables.</li>
</ol>

<h2>Hints</h2>
<ul>
	<li>
Since GameApp is a "JavaFX" application and Eclipse does not (by default) recognize such applications,
you have to make a few configuration changes to your project in Eclipse; see <a href="http://stackoverflow.com/posts/32062263/revisions">this link</a>.</li>
	<li>You will likely need to import the following classes:
	<pre>
	import javafx.scene.media.*;            
	import javafx.scene.layout.*; 
	import javafx.scene.control.Label;
	import javafx.scene.image.*;
	</pre>
	</li>
	<li>To load and play an <tt>AudioClip</tt> (<b>not</b> required for this assignment), you can call:
	<pre>
	final AudioClip sound = new AudioClip(getClass().getClassLoader().getResource(soundFilename).toString());
	...
	sound.play();
	</pre>
	</li>
	<li>To load and display an image so that its <b>center</b> is at location (<tt>x</tt>,<tt>y</tt>), you can run the following code:
	<pre>
	final Image image = new Image(getClass().getResourceAsStream(getFilename()));
	imageLabel = new Label("", new ImageView(image));
	imageLabel.setLayoutX(x - image.getWidth()/2);
	imageLabel.setLayoutY(y - image.getHeight()/2);
	...
	// Add the image to the game board
	pane.getChildren().add(imageLabel);  // pane is of type Pane
	</pre>
	<li>As a simple way of determining whether a collision between the ball and the paddle has occurred,
	first compute the square "bounding box"
	that exactly surrounds the circle representing the ball. Then, compute whether any of the four
	corners (each of which has an (<em>x</em>,<em>y</em>) location) is contained within the rectangle representing
	the paddle. If so, then a collision has occurred; otherwise, no collision has occurred. If a
	collision has occurred, then the ball should bounce off the
	paddle with a <b>negated</b> velocity in the <em>y</em> direction.
	See image below where the ball is orange, its bounding
	box is dashed and black, and the paddle is blue:<br>
	<img width="400" src="https://www.cs.wpi.edu/~cs2103/b19/Project4/collision.svg"/>
	</li>
</ul>

<!--<h1>Grading</h1>
This project is worth <b>25 points total</b>:<br>
Completion of key game features: 15 points.<br>
Object-oriented design: 10 points.<br>-->

<h1>Code Review</h1>
In addition to writing your own code, you will also be reviewing other students' code.
We will randomly assign 2 project submissions
from your peers for you to read. You will not know who wrote the code, nor will the author(s) of the code know who you
(the reviewer) are.
Reading and critically reviewing code is an important skill, especially when working in a team environment (e.g., MQP,
internship, full-time job). It can allow you to (a) learn from people more skilled than you how to perform a programming
task more elegantly or efficiently; (b) give feedback to other people about how to make their code more understandable to others;
and (c) help less experienced programmers to hone their skills.<p>

At a high level, <b>your goal</b> in the code review part of Project 4 <b>is to be as helpful as possible
to the person/team whose code you are reviewing</b>.  If there is something about how the programmer implemented their
Zutopia game that was particularly elegant, clever, novel, etc., then feel free to praise them. If there is something
that you believe could be improved, then please suggest it. Whenever possible, a criticism should be accompanied
by a suggestion for how the code could be structured or written differently, or perhaps by a question that could help guide them
when revising their code.

<h2>Communicating respectfully</h2>
When writing your code reviews, you should strive to communicate what you perceive as the
strengths and weaknesses of the code in a <b>precise</b>, <b>direct</b>, and always <b>respectful</b> way.
Every valid criticism can be expressed respectfully and politely.  For example:
<ul>
<li><em>"The code makes zero sense: I have absolutely no idea what the purpose of the  <tt>findOthers</tt></em> method is."
can be rephrased as:<br> <em>"The  purpose of the <tt>findOthers</tt> method is unclear to me."</em></li>
<li><em>"The entire program logic is ridiculously complicated."</em> can be rephrased as:<br> <em>"I would like to suggest a different way of
structuring your code that might work better: Consider breaking up this method into several helper methods..."</em></li>
<li><em>"From start to finish the program makes no sense."</em> can be rephrased as:<br> <em>"There were several pieces of code that I
found confusing. One was in lines 57-64 of <tt>GameImpl.java</tt> in the <tt>getNeighbors</tt> method: was the goal to
iterate through the list in reverse order? Another was ..."</em></li>
<li><em>"When you know how to program as well as I can, you will realize that it is better to XYZ because it is much faster and more
elegant."</em> can be rephrased as:<br> <em>"You
might consider doing XYZ because it is much faster and more elegant."</em></li>
</ul>

<h2>Reading someone else's code</h2>
When reviewing the Project 4 submissions that were assigned to you, look at the code at both a macro- and micro-level:
<ul>
<li><b>Macro</b>: Is the core game functionality divided among the multiple classes in a sensible way?  Does each class represent
a <em>coherent</em> bundle of attributes and actions (rather than just a "grab-bag" of code)? Is there significant <em>redundancy</em>, i.e.,
identical or very similar code that is used multiple times but could be factored out?</li>
<li><b>Micro</b>: Do you see any bugs in the code? Are there any important corner cases that are ignored?
Is the <em>purpose</em> and <em>implementation</em>  of each method readily understandable? Are the methods commented
as necessary to improve readability? Are any methods much too complex or long?</li>
</ul>

<h2>Getting started on the review</h2>
You will receive two Zip files -- one for each of the submitted projects you will be reviewing. Each Zip filename will contain an ID number, e.g., <tt>submission_123.zip</tt> has ID 123.<p>

For each Zip file, do the following:
First look at <em>all</em> the classes (<tt>.java</tt> files) in one pass, and try to get an overall sense
of what each class and method does, and how the classes and methods relate to each other. Next, try to understand <em>each</em> 
method in <em>each</em> class. (This may require referring back to other classes and methods.) Think about the aspects of the implementation 
that were most challenging in your <em>own</em> submission for Project 4. How did the person/team whose code you are reviewing tackle these
challenges? Do you think they have a more elegant or efficient solution as your own? Do you think you have any ideas you could share with
the other students that might benefit their code?

<h2>Writing the review</h2>
For each submission (Zip file) you review, please write a review containing sections for each of the following.
<ol>
<li><b>Bugs</b>: Did you  find any bugs in the code you read? Explain them if you did.</li>
<li><b>Readability</b>: Were there any methods, or paragraphs fo code within methods, that you found difficult to understand?
If so, mention them, and either ask a clarifying question or perhaps suggest something that might make the code easier to follow.
Were any methods particularly well written? If so, say so.</li>
<li><b>Structure</b>:  Were there any classes or methods that could be reorganized to make them more elegant or efficient?</li>
<li><b>Style</b>: Do you have any suggestions on coding style or commenting?</li>
</ol>
 (If you have
nothing to say for one of the items above, then write "Nothing to add" for the section.)
You are not required  to have much to say for <em>all</em> of the items above, but you should definitely have something
interesting to say for at least 3 of them.

<h2>What to submit</h2>
You should submit a file called <tt>review_&lt;ID&gt;.txt</tt> (where <tt>ID</tt> is the ID embedded in the Zip file assigned to you) for
each of your  two  reviews.
Each review should contain <b>only text</b> (i.e., not Microsoft Word, not PDF).

<h1>Teamwork</h1>
You may work as a team on this project (for both the game itself and the reviews); the maximum team size is 2.

<h1>Design and Style</h1>
Your code  should be intuitively decomposed into methods and classes. Moreover, you need to <b>factor out common functionality</b> between different methods. For instance, much of the same code used to test for a collision between the ball and an animal (or vegetable) can also be used to test for a collision between the ball and the wall/paddle. A significant portion of your grade for this assignment will be based on your code design (in addition to correct functionality).<p>

In addition, your code must adhere to reasonable Java style. In particular, please adhere to the following guidelines:
<ul>
<li>Each class name should be a singular noun that can be easily pluralized.</li>
<li>Class names should be in <tt>CamelCase</tt>; variables should be in <tt>mixedCase</tt>.</li>
<li>Avoid "magic numbers" in your code (e.g., <tt>for (int i = 0; i < 999 /*magic number*/; i++)</tt>). Instead,
use <b>constants</b>, e.g., <tt>private static final int NUM_ELEPHANTS_IN_THE_ROOM = 999;</tt>, defined at the top of your class file.</li>

<li>Use whitespace consistently.</li>
<li>No method should exceed 50 lines of code (for a "reasonable" maximum line length, e.g., 100 characters). If your method is larger than
that, it's probably a sign it should be decomposed into a few helper methods.</li>
<li>Use comments to explain non-trivial aspects of code.</li>
<li>Use a <a href="http://www.oracle.com/technetwork/articles/java/index-137868.html">Javadoc</a>
comment to explain what each method does, what parameters it takes, and what it returns. Use
the <tt>/**...*/</tt> syntax along with <tt>@param</tt> and <tt>@return</tt> tags, as appropriate.</li>
<li>Use the <tt>final</tt> keyword whenever possible.</li>
<li>Use the <b>most restrictive</b> access modifiers (e.g., <tt>private</tt>, default, <tt>protected</tt>>, <tt>public</tt>),
for both variables and methods, that you can. Note that this does not mean you can never use non-<tt>private</tt> access; it
just means you should have a good reason for doing so.</li>
<li>Declare variables using the <b>weakest type</b> (e.g., an interface rather than a specific class implementation) you can;
ithen instantiate new objects according to the actual class you need. This will help to ensure <b>maximum flexibility</b> of your code.
For example, instead of<br>
<tt>final ArrayList&lt;String&gt; list = new ArrayList<String>();</tt><br>use<br>
<tt>final List&lt;String&gt; list = new ArrayList&lt;String&gt;();</tt><br>If, on the other hand, you have a good reason
for using the actual type of the object you instantiate (e.g., you need to access specific methods of
<tt>ArrayList</tt> that are not part  of the <tt>List</tt> interface), then it's fine to declare the variable with a stronger type.</li>
</ul>

<h1>Getting started</h1>
Download the <a href="https://web.cs.wpi.edu/~cs2103/b19/Project4/Project4.zip">Project4.zip</a> starter file.

<h1>What to Submit</h1>
Create a Zip file containing <tt>GameImpl.java</tt>, whatever other helper classes and/or interfaces you used, <b>and</b>
whatever image and/or sound files are needed to run your game.
Submit the Zip file you created to InstructAssist.
<b>Submission deadline</b>: Sunday, November 24, at 11:59pm EDT.

