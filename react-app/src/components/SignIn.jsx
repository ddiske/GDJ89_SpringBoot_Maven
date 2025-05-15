import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import { useRef } from 'react';
import { useNavigate } from 'react-router-dom';

export default function SignIn() {

    const username = useRef("")
    const password = useRef("")

    const nav = useNavigate();

    function signIn() {
        const params = new URLSearchParams()
        params.append("username", username.current.value)
        params.append("password", password.current.value)

        fetch("http://localhost:81/user/login", {
            method : "POST",
            body : params
        })
        .then(r=>r.headers)
        .then(r=>{
            // session
            window.sessionStorage.setItem("AccessToken", r.get("AccessToken"))

            // local
            window.localStorage.setItem("RefreshToken", r.get("RefreshToken"))
            
            nav("/");
        })
        .catch(e=>{
            console.log(e)
        })
    }

  return (
    <>
        <Box
        component="form"
        sx={{ '& > :not(style)': { m: 1, width: '25ch' } }}
        noValidate
        autoComplete="off"
        >
        <TextField id="outlined-basic" label="Outlined" variant="outlined" inputRef={username}/>
        </Box>
        <Box
        component="form"
        sx={{ '& > :not(style)': { m: 1, width: '25ch' } }}
        noValidate
        autoComplete="off"
        >
        <TextField id="outlined-basic" label="Outlined" variant="outlined" inputRef={password}/>
        </Box>
        <Button variant="outlined" onClick={signIn}>Outlined</Button>
    </>
  );
}
