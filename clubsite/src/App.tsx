import React, {useEffect, useState} from 'react';
import './App.css';
import {RegisterPage} from "./RegisterPage";
import {LoginPage} from "./LoginPage";
import {ChatClient}          from "./ChatClient";
import {ChatLobby} from "./ChatLobby";
import {Simulate} from "react-dom/test-utils";
import load=Simulate.load;
import {render} from "@testing-library/react";

let showLogin=true;

export let ipAddress="192.168.43.37";//192.168.43.37

const App=()=>
{
    const [id,setId]=useState(localStorage.getItem("id"));
    return id==null
        ?<RegisterPage setId={setId}/>
        :<ChatLobby id={id}/>;
}

export default App;