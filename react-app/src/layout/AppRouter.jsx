import { Route, Routes } from "react-router-dom"
import Home from "../components/home"
import Notice from "../components/Notice"
import Qna from "../components/Qna"
import Detail from "../components/detail"

export default function AppRouter(){
    return(
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/notice/">
                <Route path="list" element={<Notice/>}></Route>
                <Route path="detail/:boardNum" element={<Detail/>}></Route>
            </Route>
            <Route path="/qna/list" element={<Qna/>}></Route>
            <Route path="/notice/detail" element={<Detail/>}></Route>
        </Routes>
    )
}