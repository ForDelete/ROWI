import "./App.css";
import "./index";
import React,{useCallback,useState} from "react";
import {ipAddress} from "./App";
import {Box, Text} from "grommet";

async function requestRegisterUser
(
	name="",
	login="",
	password="",
	setId:Function,
	setName:Function,
	setPassword:Function,
	setLogin:Function,
	registered:boolean,
	setRegistered:Function
)
{
	let alreadyHas=false;
	await fetch
	(
		`http://${ipAddress}:8080/hasUser/login=${login}`,
		{
		}
	).then
	(
		(response)=>
		{
			if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
			return response.text();
		}
	).then((has)=>
	{
		alreadyHas=has==="true"
	});

	if(alreadyHas)
	{
		alert(`The'${login}' login is already in use!`);
		setName("");
		setPassword("");
		setLogin("");
		return;
	}
	await fetch(`http://${ipAddress}:8080/addUser?name=${name}&login=${login}&password=${password}`);
	await getID(login,password);
	window.location.reload();
}



const setId=(id:string)=>localStorage.setItem("id",id);

async function getID(login:string,password:string)
{
	fetch
	(
		`http://${ipAddress}:8080/user?login=${login}&password=${password}`
	).then
	(
		(response) => {
			if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
			return response.json();
		}
	).then((text) => {
		console.log(text);
		setId(text.id)
	});
}

async function requestLoginUser(login="",password="",setId:Function,setLogin:Function,setPassword:Function)
{
	let alreadyHas=false;
	await fetch
	(
		`http://${ipAddress}:8080/hasUser/login=${login}`,
		{
		}
	).then
	(
		(response)=>
		{
			if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
			return response.text();
		}
	).then((has)=>
	{
		alreadyHas=has==="true"
	});
	if(!alreadyHas)
	{
		alert(`There is no user with '${login}' login!`);
		setLogin("");
		setPassword("");
		window.location.reload();
		return;
	}
	await getID(login,password);
	window.location.reload();
}

export function RegisterPage({setId}:{setId:Function})
{
	const [name,setName]=useState("");
	const [login,setLogin]=useState("");
	const [password,setPassword]=useState("");
	const [registered,setRegistered]=useState(false);
	return (
		<Box className={"RegisterPage"} direction={"row"} flex={"grow"}>
			<Box align={"start"}>
				<Text>Please register or log in to start use our service!</Text>
				<div>Name<input required={true} type={"text"} value={name} onChange={e=>setName(e.target.value)}/></div>
				<div>Login<input required={true} type={"text"} onChange={e=>setLogin(e.target.value)}/></div>
				<div>Password<input required={true} type={"text"} onChange={e=>setPassword(e.target.value)}/></div>
				<button onClick={()=>requestRegisterUser(name,login,password,setId,setName,setPassword,setLogin,registered,setRegistered)}>REGISTER</button>
			</Box>
			<Box align={"end"}>
				<div>Login<input required={true} type={"text"} onChange={e=>setLogin(e.target.value)}/></div>
				<div>Password<input required={true} type={"text"} onChange={e=>setPassword(e.target.value)}/></div>
				<button onClick={()=>requestLoginUser(login,password,setId,setLogin,setPassword)}>LOG IN</button>
			</Box>
		</Box>
	);
}