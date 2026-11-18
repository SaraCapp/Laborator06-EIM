
import socket

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

server_socket.bind(("0.0.0.0", 2000))
server_socket.listen(1)

print("Server pornit!")

while True:
    connection, address = server_socket.accept()
    print("Client conectat:", address)
    connection.sendall(b"hello\n")
    connection.close()
