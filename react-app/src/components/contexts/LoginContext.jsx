import { Children, createContext, useContext } from "react";

const loginStateContext = createContext()

export const LoginStateProvider = ({children}) =>{
    const [isLogin, setIsLogin] = useState(false);
}

const login = () => {
    setIsLogin(true);
}

const logout = () => {
    setIsLogin(false);
}

return (
    <loginStateContext.Provider value={{isLogin, login, logout}}>
        {children}
    </loginStateContext.Provider>
)