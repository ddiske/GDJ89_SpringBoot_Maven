import { useRef } from "react"
import { useLocation, useParams } from "react-router-dom"

export default function Detail() {
    // 파라미터 : URL/파라미터값/파라미터값
    const num = useParams();
    
    // 쿼리스트링 : URL?이름=값&이름=값
    // const location = useLocation()
    // const detail = location.state.detail
    // const vo = useRef({})

    fetch(`http://localhost:81/notice/detail?boardNum=${detail.boardNum}`)
    .then(r=>r.json())
    .then(r=>{
        vo.current = r
    })

    return (
        <>
            <h1>Detail</h1>
            <h3>{vo.current.boardTitle}</h3>
            <h3>{vo.current.boardContents}</h3>
            <h3>{vo.current.boardHit}</h3>
            <h3>{vo.current.boardDate}</h3>
            <h3>{vo.current.username}</h3>
        </>
    )
}