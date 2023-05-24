import React from 'react';
import './App.css';
import {RegisterPage} from "./RegisterPage";
import {LoginPage} from "./LoginPage";
import {ChatClient}          from "./ChatClient";
import {ChatLobby} from "./ChatLobby";
import {Simulate} from "react-dom/test-utils";
import load=Simulate.load;

let showLogin=true;

export let ipAddress="192.168.43.1";

const App=({socket}:{socket:WebSocket})=>
{
    function goToChats()
    {
        showLogin=false;
    }

    let n=localStorage.getItem("id");
    let id=n==null?0:Number.parseInt(n);

    return (//<RegisterPage socket={socket} goToChats={goToChats}/>//(//showLogin
           //?(id==null?<RegisterPage socket={socket} goToChats={goToChats}/>:<LoginPage socket={socket}/>)
           // //:
           <ChatLobby id={id}/>);
}


export default App;