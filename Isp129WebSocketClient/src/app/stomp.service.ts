import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({ // can be injected everywhere
    providedIn: 'root', // only instance of this service in this entire application
})
export class StompService {
    private connecting: boolean = false;

    // configure a socket through the module SockJS
//     socket = new SockJS('http://localhost:8010/gs-guide-websocket');
    socket = new SockJS('http://127.0.0.1:8020/');
    stompClient = Stomp.over(this.socket);
    msg = [];

    disconnect():void {
        this.connecting = false;
        this.stompClient.disconnect(function() {
            alert( "See you next time!" ) ;
       })
    }

    send(name: String): void {
        this.stompClient.send(
            "/app/hello",
            {},
            JSON.stringify({name}));
    }

    // this method will be used when we want to apply the StompService
    subscribe(topic: string, callback: any): void {
        this.stompClient.connect(
            {},
            () => {this.stompClient.subscribe(topic, callback);
        });
    }

}
