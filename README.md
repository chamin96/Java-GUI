# CO225:Software Construction Project II : [Auction server]

Implementation of a server which can be used by clients to bid for items in a stock exchange.

## Instructions to run the server
### Using Terminal
- First you have to compile the Main.java file in the directory ```"src/com/foxploit/"```
```
$Javac Main.java
```
- Next, run the Main file using java
```
$Java Main
```
- That's it, the server should be started now.

### Or, using IntelliJ IDEA
- you can simply open this repository in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
- Run the project and you will have the server running.

## Instructions to the clients
### Use [```netcat```](https://en.wikipedia.org/wiki/Netcat) to connect to the server via localhost port  `2000` in linux terminal
```
$nc <ip address of the server> 2000
```
- You can now bid on the auction server.
- When the following message appears, enter the username that you will be used.
```
Welcome to the Auction!
Enter Username:
```
- Next enter the symbol for the security that you want to bid.
```
Enter symbol that you're bidding :
```
- If the symbol for the security is authorized, you can bid now.
```
You are authorised to bid
Enter your bid:
```

Under [MIT Licence](https://github.com/luk3Sky/CO225-Project-II-Auction_Server/blob/master/LICENSE)
