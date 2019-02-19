const {ccclass, property} = cc._decorator;

@ccclass
export default class Helloworld extends cc.Component {

    @property(cc.Label)
    label: cc.Label = null;

    @property
    text: string = 'hello';

    @property(cc.Button)
    btnWsConnect: cc.Button = null;

    socketOpen: boolean;
    ws: WebSocket;

    start() {
        // init logic
        this.label.string = this.text;
    }

    onLoad() {
        // this.btnWsConnect.

    }

    wsOpen() {
        this.ws = new WebSocket("ws://192.168.8.140:7002/websocket");
        this.ws.onopen = (event) => {
            this.socketOpen = true
            console.log("Send Text WS was opened.");
            this.ws.onmessage = (event) => {
                console.log("response text msg: " + event.data);
            };
            this.ws.onerror = (event) => {
                console.log("Send Text fired an error");
            };
            this.ws.onclose = (event) => {
                console.log("WebSocket instance closed.");
            };
        };
    }

    wsSend() {
        this.socketWrap(() => {
            this.ws.send(JSON.stringify({
                data: "demo"
            }))
        })
    }

    socketWrap(callback) {
        if (this.socketOpen) {
            callback()
        } else {
            console.log('网络已断开')
        }
    }

    createRoom() {
        this.socketWrap(() => {
            this.ws.send(JSON.stringify({
                data: "创建房间"
            }))
        })
        cc.director.loadScene('room')
    }
}
