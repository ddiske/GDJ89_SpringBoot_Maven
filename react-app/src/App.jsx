import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import Notice from './components/Notice'
import { BrowserRouter } from 'react-router-dom'
import Header from './layout/Header'
import AppRouter from './layout/AppRouter'
import { Base_URL } from './components/contexts/UrlContext'
import Footer from './layout/Footer'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Base_URL.Provider value="http://localhost:81/">
          <Header></Header>

          <AppRouter></AppRouter>

          <Footer></Footer>
        
        </Base_URL.Provider>
      </BrowserRouter>
    </>
  )
}

export default App
