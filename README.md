# gRPC server using zio-gRPC

Sample project streaming data taken from:

https://randomuser.me/api/?results=10&inc=name,gender,nat&noinfo

## How to run:

Just execute `sbt run` and the server will start listening on port `8980`.

Now you can go to (zio-rpc-client)[https://github.com/DanielBlanco/zio-grpc-client]
and run it or you can install https://github.com/fullstorydev/grpcurl
and execute the following:

```
grpcurl -plaintext localhost:8980 list
grpcurl -plaintext localhost:8980 user.UserService/Find
```

----

See https://github.com/DanielBlanco/zio-grpc-client

