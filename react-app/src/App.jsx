import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import Notice from './components/Notice'
import { BrowserRouter } from 'react-router-dom'
import Header from './layout/Header'
import AppRouter from './layout/AppRouter'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
      <Header></Header>
      <AppRouter></AppRouter>
      
     </BrowserRouter>
    </>
  )
}

export default App
