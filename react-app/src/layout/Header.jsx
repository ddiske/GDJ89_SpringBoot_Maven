import { Link } from "react-router-dom";

function Header() {
    return (
        <>
            <h1>Header</h1>
            <div>
                <Link to='Home'>Home</Link>
                <Link to='/Notice'>Notice</Link>
                <Link to='/Qna'>Qna</Link>
                <Link to='/Join'>Join</Link>
            </div>
        </>
    )
}

export default Header;