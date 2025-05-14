import { useRef, useState } from "react"
import { Navigate, useNavigate } from "react-router-dom"

// 작성자, 제목, 내용, 첨부파일3개 고정
export default function Add() {

    const navigate = useNavigate()

    // const b = useRef({username:"", title:"", contents:""})
    const username = useRef("")
    const title = useRef("")
    const contents = useRef("")
    const [files, setFiles] = useState({
        userName : "",
        boardTitle : "",
        boardContents : "",
    })

    function changeInput(e) {

        setFiles((prevState)=>({
            ...prevState, // 전개
            [e.target.name]:e.target.value
        }))
    }

    function click(e) {
        e.preventDefault();
        let f = new FormData(e.target)
        fetch("http://localhost:81/notice/add", {
            method : "POST",
            body : f
        })
        .then(r=>r.json())
        .then(r=>{
            if(r > 0) {
                navigate("/notice/list")
            }
        })
    }

    function click2() {

        console.log(files)

        let params = new FormData()
        params.append("userName", username.current.value)
        params.append("boardTitle", title.current.value)
        params.append("boardContents", contents.current.value)
        params.append("attaches", document.getElementById())


        // Server에 Get메서드 외의 요청시 CORS(Method) 허용 Spring Security
        fetch("http://localhost:81/notice/add", {
            method : "post",
            body : params
        })
        .then(r=>r.json())
        .then(r=>{
            console.log(r)
        })
    }

    return(
        <>
            <h1>Add</h1>
            <div>
                <form onSubmit={click}>
                    <div>
                        <input type="text" placeholder="Username" name="userName" onBlur={changeInput} ref={username}/>
                    </div>
                    <div>
                        <input type="text" placeholder="Title" name="boardTitle" onBlur={changeInput} ref={title}/>
                    </div>
                    <div>
                        <textarea id="" placeholder="Contents" name="boardContents" onBlur={changeInput} ref={contents}></textarea>
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