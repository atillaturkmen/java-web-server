# java-web-server
- Multihreaded TCP server implemented in Java with java.net package. 
- Every connection is handled in a different thread. 
- The server listens to the client connection and writes incoming data into a file. 
- A binary semaphore is used to prevent data races while writing into the file between threads.

## Outputs

### Clients

![Screenshot (117)](https://user-images.githubusercontent.com/59166549/202149068-96591b92-7428-417c-bc6f-48613de043ac.png)

### Server

#### Server logs
```
/127.0.0.1:49160 opened a connection in thread ServerThread@2e8b50b9
/127.0.0.1:49160 sent: hello
/127.0.0.1:49161 opened a connection in thread ServerThread@26372370
/127.0.0.1:49161 sent: hello
/127.0.0.1:49161 closed the connection.
/127.0.0.1:49160 sent: hello 2
/127.0.0.1:49160 closed the connection.
```
#### File output
```
/127.0.0.1:49160 hello
/127.0.0.1:49161 hello
/127.0.0.1:49160 hello 2
```
