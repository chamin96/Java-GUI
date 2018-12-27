# CO225:Software Construction Project II : [Auction server]

Implementation of a server which can be used by clients to bid for items in a stock exchange.

## Instructions to run the server
### Using Terminal
- First you have to compile the files in the directory ```"src/com/foxploit/"```
```
$Javac <FileName>.java
```
- Next, run the Main file using java
```
$Java <Main>
```
- That's it, the server should be started now.

### Or, using IntelliJ IDEA
- you can simply open this repository in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
- Run Main.java file and you will have the server running.

## Instructions to the clients
### Use [```netcat```](https://en.wikipedia.org/wiki/Netcat) to connect to the server via localhost port  `2000` in linux terminal
```
$nc <ip address of the server> 2000
```
- You can now bid on the auction server.

Under [MIT Licence](https://github.com/luk3Sky/CO225-Project-II-Auction_Server/blob/master/LICENSE)
