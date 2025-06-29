# TuringMachine

Inside the Turing.txt file we are going to input our initial state, final states and the list of transitions. For the program to work, the file has to be formatted this way:

q0  <-- One initial state

q3  <-- One or more final states

q0 B a R q1  <-- current state, read symbol, written symbol, direction, next state

q1 B b R q2

q2 B B R q3

After we finish going through the list of transitions, if the final state that we've reached is part of the final states list, then the word used as input is a valid word accepted by the Machine.
