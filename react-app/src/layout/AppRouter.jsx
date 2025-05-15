import { Route, Routes } from "react-router-dom"
import Home from "../components/home"
import Notice from "../components/Notice"
import Qna from "../components/Qna"
import Detail from "../components/detail"
import Add from "../components/Add"
import Update from "../components/Update"
import List from "../components/List"
import SignUp from "../components/SignUp"
import SignIn from "../components/SignIn"

export default function AppRouter(){
    return(
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/notice/">
                <Route path="list" element={<List/>}></Route>
                <Route path="detail/:boardNum" element={<Detail/>}></Route>
                <Route path="add" element={<Add/>}></Route>
                <Route path="update" element={<Update/>}></Route>
            </Route>
            <Route path="/qna/list" element={<Qna/>}></Route>
            <Route path="/notice/detail" element={<Detail/>}></Route>
            <Route path="/user/">
                <Route path="join" element={<SignUp/>}></Route>
                <Route path="login" element={<SignIn/>}></Route>
            </Route>
        </Routes>
    )
}