import React from 'react';
import App from "./App";
import {createRoot} from "react-dom/client";

let socket=new WebSocket("ws://192.168.43.37:8080");

const rootElement=document.getElementById("root");
if(!rootElement) throw new Error('Failed to find the root element');
const root=createRoot(rootElement);
root.render(<React.StrictMode><App socket={socket}/></React.StrictMode>);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
//reportWebVitals();

/*
fetch
(
    `http://192.168.43.37:8080/addUser?name=${name}&login=${login}&password=${password}`,
    {
        method:"POST",
        mode:"no-cors",
        headers:
            {
                'Access-Control-Allow-Origin':'*',
                'Content-Type':'application/json;charset=utf-8'
            }
    }
).then
 (
     (response)=>
     {
         if(!response.ok)
         {
             throw new Error(`HTTP error: ${response.status}`);
         }
         return response.text();
     }
 )
 .then((text)=>
       {
           console.log(text)
       })
 .catch((error)=>
        {
            alert(`Could not fetch verse: ${error}`);
        });
* */