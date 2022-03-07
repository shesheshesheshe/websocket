import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StompService } from './stomp.service';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'Isp129WebSocketClient';
  con: Boolean=true;
  discon: Boolean=false;
  greetings: String[] = [];
  vehicleForm = new FormGroup({
    name: new FormControl(null),
  });

  constructor(private http: HttpClient, private stompService: StompService) {
    this.greetings = [];
  }

  ngOnInit(): void {
    this.stompService.subscribe(
        '/topic/greetings', 
        (message: any) => {
            this.greetings.push(JSON.parse(message.body).content);
            console.log(this.greetings);
        });
  }

  connect() {
    console.log("connecting in angular");
    this.stompService.subscribe(
      '/topic/greetings', 
      (message: any) => {
          console.log(JSON.parse(message.body).content);
          this.greetings.push(JSON.parse(message.body).content);
        });
    this.setConnected(true);
  }

  disconnect() {
    this.stompService.disconnect();
    console.log("Disconnected");
    this.setConnected(false);
  
  }


  get playingValue(){
    return this.greetings;
  }

  set playingValue(playing){
      this.greetings = playing;
  }

  onSubmit() {
    this.stompService.send(this.vehicleForm.get('name')!.value);
  }

  setConnected(connected: Boolean) {
    if (connected) {
      this.con = true;
      this.discon = false;
    } else {
      this.con = false;
      this.discon = true;
    }
  }
}
