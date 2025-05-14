import { useRef } from "react"
import { Link, useLocation, useNavigate, useParams, useSearchParams } from "react-router-dom"

export default function Detail() {
    // 파라미터 : URL/파라미터값/파라미터값
    // useParam (파라미터 데이터를 가져 올 때)
    // const num = useParams();
    
    // 쿼리스트링 : URL?이름=값&이름=값
    // useSearchParams (쿼리 스트링)
    // const [searchParams, setSearchParams] = useSearchParams()
    // console.log(searchParams.get("boardNum"))

    // state : Link 태그의 state 이용용
    // useLocation (쿼리 스트링)
    // hash : 주소의 #으로 시작하는 문자열 뒤의 값
    // pathName : 현재 주소의 경로
    // search : ?를 포함한 쿼리 스트링
    // state : 페이지 이동 시 임의로 입력 가능한 상태 값
    // key : location 객체의 고유 값, 페이지가 변경될 때마다 고유의 값 생성
    const location = useLocation()
    const detail = location.state.detail

    // fetch(`http://localhost:81/notice/detail?boardNum=${detail.boardNum}`)
    // .then(r=>r.json())
    // .then(r=>{
    //     vo.current = r
    // })

    // useNavigate(url, option)
    // state : 
    // replace : boolean : 뒤로 가기 방지(true)
    // relative : route -> 절대경로, path -> 상대경로
    const navigate = useNavigate()

    function del() {
        let p = new URLSearchParams()
        p.append("boardNum", detail.boardNum)


        fetch("http://localhost:81/notice/delete", {
            method : "POST",
            body : p
        })
        .then(r=>r.json())
        .then(r=>{
            console.log(r)
            if(r > 0) {
                alert("Deleted")
                navigate("/notice/list")
            }
        })
    }

    return (
        <>
            <h1>Detail</h1>
            <h3>{detail.boardNum}</h3>
            <h3>{detail.boardTitle}</h3>
            <h3>{detail.boardContents}</h3>
            <h3>{detail.boardDate}</h3>
            <h3>{detail.boardHit}</h3>
            <h3>{detail.username}</h3>
            <hr />
            <button onClick={del}>Delete</button>
            <Link to="/notice/update" state={{detail:detail}}>Update</Link>
        </>
    )
}