import { Link } from "react-router-dom";

function Header() {
    return (
        <>
            <h1>Header</h1>
            <div>
                <Link to='/'>Home</Link>
                <Link to='/notice/list'>Notice</Link>
                <Link to='/qna/list'>Qna</Link>
                {/* <Link to='/Join'>Join</Link> */}
            </div>
        </>
    )
}

export default Header;