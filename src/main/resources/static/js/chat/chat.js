const websocket = new WebSocket("/ws/chat")
const send = document.getElementById("send")
const message = document.getElementById("message")
const receiver = document.getElementById("receiver")
const chatBody = document.getElementById("chat-body")
const sender = document.getElementById("sender")


//webSocket 연결이 되었을 때
websocket.onopen=()=>{
    let m = new Message();
    m.body = "님이 입장했습니다";
    // websocket.send(JSON.stringify(m))
}

//websocket으로 메세지를 수신 했을 때
websocket.onmessage=(m)=>{
    //
    let mes = JSON.parse(m.data)
    let start = mes.sender
    let re = receiver.value; // 현재 채팅하는 상대방
    if(re != start && start != sender.value) {
        return;
    }
    let r = makeData(mes);
    chatBody.append(r)
    console.log(m)
}

//webSocket연결이 종료 되었을 때
websocket.onclose=()=>{
    websocket.send("님이 나갔습니다")
}

//개발자가 메세시 송신 할 때
send.addEventListener("click", ()=>{
    let m = message.value
    let offset = 1000 * 60 * 60 * 9
    let mes = new Message();
    mes.sender = sender.value
    mes.body = m
    mes.receiver = receiver.value;
    mes.date = new Date((new Date()).getTime()+offset).toISOString().replace("T", " ").split('.')[0]
    mes.status = "1";

    websocket.send(JSON.stringify(mes))
    message.value = "";
})

//websocke error 발생시
// websocket.onerror=()=>{

// }

websocket.onerror=webSocketError;

function webSocketError(){

}



// ----------------------------

class Message {
    sender="";
    body="";
    receiver="";
    date="";
    status="0"; // 0은 전체 1은 1:1
    roomNum="";
}


// Modal = btn-circle
const tbl = document.getElementById("dataTable")
const cbody = document.getElementById("chat-body")

tbl.addEventListener("click", (e)=>{
    if(e.target.classList.contains("btn-circle")){
        chatBody.innerHTML = "";
        receiver.value = e.target.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.innerText;
        fetch(`/chat/room?receiver=${receiver.value}&sender=${sender.value}`)
        .then(r=>r.json())
        .then(r=>{
            r.forEach(e => {
                let d = makeData(e);
                chatBody.append(d);
            });
        })
    }

})

function makeData(data) {
    const div = document.createElement("div")
    div.innerText = data.body
    return div;
}