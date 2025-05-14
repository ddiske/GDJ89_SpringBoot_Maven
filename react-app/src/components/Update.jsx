import { useLocation, useNavigate } from "react-router-dom"

export default function Update() {

    const location = useLocation()
    const detail = location.state.detail

    const navigate = useNavigate()

    function submit(e) {
        e.preventDefault()

        let p = new FormData(e.target);

        fetch("http://localhost:81/notice/update", {
            method : "POST",
            body : p
        })
        .then(r=>r.json())
        .then(r=>{
            if(r > 0) {
                alert("Updated")
                navigate("/notice/detail", {state : {detail : location.state.detail}})
            }
        })
    }

    return(
        <>
            <h1>Update</h1>
            <div>
                <form onSubmit={submit}>
                    <input type="hidden" defaultValue={detail.boardNum} name="boardNum" />
                    <div>
                        <input type="text" placeholder={detail.userName} name="userName"/>
                    </div>
                    <div>
                        <input type="text" placeholder={detail.boardTitle} name="boardTitle"/>
                    </div>
                    <div>
                        <textarea id="" placeholder={detail.boardContents} name="boardContents"></textarea>
                    </div>
                    <div>
                        <input type= "file" name="attaches" />
                        <input type= "file" name="attaches"/>
                        <input type= "file" name="attaches"/>
                    </div>
                    <div>
                        <button >confirm</button>
                    </div>
                </form>
            </div>
        </>
    )
}