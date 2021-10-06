# File Encoder

### Command
````shell
java reza.encoder.FileEncoder [source-file] [encoding-method] [server-address]
````


### Parameters


__source-file__ : The file that needs to be encoded and decoded. File must be provided with extension.


__encoding-method__: values: base64, character

__server-address__: provide server address and port. (optional)

server-address must be provided as __IP:PORT__

If server address is not provided default IP and PORT is used to connect.

__127.0.0.1:3000__ is the default address for decoder server.

provides the type of encoding that can be applied.


### Example
* To encode a file named cat.jpg use the following command

```shell
java reza.encoder.FileEncoder cat.jpg cat_out base64 127.0.0.1:3000
```
or if you want to use the default ip and port use the following
```shell
java reza.encoder.FileEncoder cat.jpg cat_out base64
```