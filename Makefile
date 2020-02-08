BLD = build/
SRC = src/

RM = rm
JAVA = java -cp build Start
JAVAC = javac -d build
MAIN = build.Start


make:
	$(JAVAC) $(SRC)*.java

clean:
	$(RM) $(BLD)*.class

run:
	$(JAVA) $(MAIN)