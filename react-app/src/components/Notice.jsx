import { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";

function Notice() {

    const [result, setResult] = useState({list:[], pager:""})
    const [page, setPage] = useState(1)
    const [search, setSearch] = useState("")
    const [flag, setFlag] = useState(false)

    useEffect(()=>{
        
        let params = new URLSearchParams();
        params.append('page', page)
        params.append('search', search)

        fetch(`http://localhost:81/notice/list?${params}`)
        .then(r=>r.json())
        .then(r=>{
            setResult(r)
        })
    }, [flag])
    
    function makeNum() {
        let p = [];
        let e = <button onClick={pageClick} data-page-num={result.pager.startBlock-1}>이전</button>
        p.push(e)
        for(let i = result.pager.startBlock; i <= result.pager.endBlock; i++) {
            e = <button onClick={pageClick} data-page-num={i}>{i}</button>
            p.push(e)
        }
        e = <button onClick={pageClick} data-page-num={result.pager.endBlock+1}>다음</button>
        p.push(e)
        return p;
    }

    function pageClick(e) {
        setPage(e.target.getAttribute("data-page-num"))
        setFlag(!flag)
    }

    function searchClick() {
        setSearch(v.current.value)
        setPage(1)
        setFlag(!flag)
    }

    const v = useRef("")

    return (
        <>
            <h1>Notice</h1>
            <div>
                <input type="text" ref={v}/><button onClick={searchClick}>검색</button>
            </div>
            {
                result.list.map(l=>
                    <li key={l.boardNum}><Link to={`/notice/detail?boardNum=${l.boardNum}`} state={{detail:l}}>{l.boardTitle}</Link></li>
                )
            }

            <hr />

            {
                makeNum()
            }

        </>
    )
}

export default Notice;