import SockJS from "sockjs-client";

function Footer() {

    const loginState = useLoginStateContext();
    

    function getSocket() {
        console.log("연결 시도")
        const socket = new SockJS(`/ws/chat?t=Bearer ${sessionStorage.getItem("AccessToken")}`)//WebSocket("/ws/chat")

        socket.onopen = function() {
            console.log("연결 성공 : ", socket)
        }

        socket.onmessage = function(e) {
            console.log("수신 : ", e.data)
        }

        socket.onclose = function() {
            console.log("연결 종료 : ", socket)
        }

        socket.onerror = function() {
            console.log("오류 발생 : ", socket)
        }

    }

    return (
        <>
            <hr />
            <h1>Footer</h1>
        </>
    )
}

export default Footer;