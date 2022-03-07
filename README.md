# ISP129-WebSocket

## Structure
Spring boot as the server
Angular as the client
Nginx as the proxy between the client and the server
The client subscribes the server througth WebSocket

## Step1. Start the server
run Isp129WebSocketServerApplication

## Step2. Start the client
cd to ISP129-WebSocket-Server in the terminal and `npm start`

## Step3. Configure the proxy (make sure that Nginx is installed)
in the terminal:
1. `sudo nginx`
2. copy nginx.conf and paste all to `vim /user/local/etc/nginx/nginx.conf`
3. `sudo nginx` again

Now, you can see that the client http://localhost:4200/ connects to the server http://localhost:8010 successfully through the WebSockt
Also, http://localhost:8020 shows that the proxy is working successfully
