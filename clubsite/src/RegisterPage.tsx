import "./App.css";
import "./index";
import React,{useCallback,useState} from "react";

const requestRegisterUser=(name="",login="",password="",goToChats:Function)=>
{
	if(!isRegistered(login))
	{
		let id;
		fetch
		(
			`http://192.168.43.37:8080/addUser?name=${name}&login=${login}&password=${password}`,
			{
				//method:"POST",
				// mode:"no-cors",
				// headers:
				//     {
				//         'Access-Control-Allow-Origin':'*',
				//         'Content-Type':'application/json;charset=utf-8'
				//     }
			}
		).then
		 (
			 (response)=>
			 {
				 if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
			 }
		 );

		// setTimeout
		// (
		//     ()=>
		//     {
		//         localStorage.setItem("id",getID(login,password));
		//         goToChats();
		//     },5000
		// );
	}
	else
	{
		console.log("HAS")
		goToChats();
	}
};

const isRegistered=(login:string)=>
{
	let registered=false;
	let user=fetch
	(
		`http://192.168.43.37:8080/hasUser?login=${login}`,
		{
			method:"GET"
		}
	).then
	 (
		 (response)=>
		 {
			 if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
			 return response.text();
		 }
	 ).finally((has="false")=>registered=has==="true");
	return registered;
}

const setId=(id:string)=>localStorage.setItem("id",id);

const getID=async(login:string,password:string)=>
	await fetch
	(
		`http://192.168.43.37:8080/user?login=${login}&password=${password}`
	).then
	 (
		 (response)=>
		 {
			 if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
			 return response.json();
		 }
	 ).then((text)=>setId(text.id));

export function RegisterPage({socket,goToChats}:{socket:WebSocket,goToChats:Function})
{
	const [name,setName]=useState("");
	const [login,setLogin]=useState("");
	const [password,setPassword]=useState("");
	return <div className={"RegisterPage"}>
		<div>Please register to start use our service!</div>
		<div>
			<div>Name<input type={"text"} value={name} onChange={e=>setName(e.target.value)}/></div>
			<div>Login<input type={"text"} onChange={e=>setLogin(e.target.value)}/></div>
			<div>Password<input type={"text"} onChange={e=>setPassword(e.target.value)}/></div>
			<button onClick={()=>requestRegisterUser(name,login,password,goToChats)}>REGISTER</button>
		</div>
	</div>;
}