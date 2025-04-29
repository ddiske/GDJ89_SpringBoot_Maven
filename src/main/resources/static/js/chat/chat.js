const send = document.getElementById("send")
const message = document.getElementById("message")
const receiver = document.getElementById("receiver")
const chatBody = document.getElementById("chat-body")
const sender = document.getElementById("sender")

const websocket = new WebSocket("/ws/chat")


// memo
try {
    const messages = document.getElementById("messages")
    messages.addEventListener("click", (e)=>{
        if(e.target.classList.contains("mes")) {
            let ac = document.getElementById("alertCount")
            ac.innerText = Number(ac.innerText) - 1;
            e.target.remove();
        }
    })
    
    const memoSend = document.getElementById("memoSend")
    const memoContents = document.getElementById("memoContents")
    const memoReceiver = document.getElementById("memoReceiver")

    memoSend.addEventListener("click", ()=>{
        let offset = 1000 * 60 * 60 * 9
        let m = new Message();
        m.body = memoContents.value
        m.receiver = memoReceiver.value
        m.status = "3";
        m.date = new Date((new Date()).getTime()+offset).toISOString().replace("T", " ").split('.')[0]

        websocket.send(JSON.stringify(m))

    })

} catch (error) {
    
}

// chat
try {
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
        let status = mes.status;
        if(status == "3") {
            
            makeMemo(mes)
            let ac = document.getElementById("alertCount")
            ac.style = ""
            ac.innerText = Number(ac.innerText) + 1;
            
            return;
        }

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
} catch (error) {
    
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



function makeData(data) {
    const div = document.createElement("div")
    div.innerText = data.body
    return div;
}

// memo output

function makeMemo(data) {
    let a = document.createElement("a")
    a.classList.add("dropdown-item", "d-flex", "align-items-center", "mes")
    a.href = "#"
    let div = document.createElement("div")
    div.classList.add("dropdown-list-image", "mr-3")
    let img = document.createElement("img")
    img.classList.add("rounded-circle")
    img.src = "/img/undraw_profile_1.svg"
    img.alt = "..."
    div.appendChild(img)
    let div2 = document.createElement("div")
    div2.classList.add("status-indicator", "bg-success")
    div.appendChild(div2)
    a.appendChild(div);

    div = document.createElement("div")
    div.classList.add("font-weight-bold")
    div2 = document.createElement("div")
    div2.classList.add("text-truncate")
    div2.innerText = data.body
    div.appendChild(div2);
    div2 = document.createElement("div")
    div2.classList.add("small", "text-gray-500")
    div2.innerText = data.sender + " " + data.date
    div.appendChild(div2)
    a.appendChild(div)

    document.getElementById("memoAdd").before(a)

}

