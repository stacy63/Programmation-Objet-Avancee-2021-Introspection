# define a makefile variable for the java compiler
#
JCC = javac

J= java

CC = g++

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

CFLAGS= -Wall -O

TARGET= ./out/production/Convertisseur

#Rules
make-java:
	$(JCC) $(JFLAGS) -d $(TARGET) ./src/utils/ConvertisseurUtil.java  ./src/main/Main.java 

run-java:
	$(J) -cp $(TARGET) main/Main Etudiant EtudiantCPP 

make-cpp:
	$(CC) -o EtudiantCPP.o -c EtudiantCPP.hpp $(CFLAGS)


# To start over from scratch, type 'make clean'.
# Removes all .class files, so that the next make rebuilds them
#
clean:
	$(RM) *.class