FLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Ball.java \
		  Game.java \
		  GameApp.java \
		  GameImpl.java \
		  Paddle.java \
		  Tile.java \
		  GameObject.java \

MAIN = GameApp

default: classes

classes: $(CLASSES:.java=.class)

run: classes 
	$(JVM) $(MAIN) 

clean:
	$(RM) *.class
