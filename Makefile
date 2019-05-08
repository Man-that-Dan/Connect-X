


# makkefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)
# define a variable for the Java Virtual Machine (JVM)

JFLAGS = -g
JC = javac
JVM= java -classpath connectX/src/
FILE=



.SUFFIXES: .java .class




default:
	$(JC) $(JFLAGS) $ connectX/src/*.java



CLASSES = \
	connectX/src/Gameboard.java \
	connectX/src/Connect4Game.java \
	connectX/src/IGameBoard.java \
	connectX/src/GameBoardMem.java \


#
# MAIN is a variable with the name of the file containing the main method
#

MAIN = Connect4Game



classes: $(CLASSES:.java=.class)


test:
	$(JC) $(JFLAGS) $ connectX/src/*.java
	javac -cp .:/Users/DToro/.m2/repository/junit/junit/4.12/junit-4.12.jar connectX/src/TestIGameboard.java

runtest:
	cd connectX/src && java -cp /usr/share/java/junit-4.12.jar org.junit.runner.JUnitCore TestIGameboard


# Next two lines contain a target for running the program


run:
	$(JVM) $(MAIN)


# this line is to remove all unneeded files

clean:
	$(RM) connectX/src/*.class
