# KolkoKrzyzyk
A bit enhanced tick tack toe, made with Intellij.

Runs through UDP sockets.
Start the server, then client1, then client2 and you are ready to play.
Localhost is hardcoded, so must be properly amended if to be used cross-machines. So are the ports, 4443, 4444, 4445, 4446, make sure they are free, or change them. If you want to use cross machinse, you will need 2 ports for the server and a port for each client. Check the main() of clients (ports[0] is where the server listens, ports[1] is where the client listens). 
Make sure to manually kill the server after the game is finished.

Oh, you must get five in row, horizontally, vertically or diagonally.

Adjust the size in GameServer.java, where Game object is created.
