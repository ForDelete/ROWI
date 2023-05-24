import React,{useEffect,useState}         from "react";
import {Box,Button,Header,Text,TextInput} from "grommet";
import {Chatroom}                         from "./ChatLobby";

interface Message
{
	chatRoom:Chatroom,
	id:number,
	message:string,
	sender_id:any;
	time:string
}

export const Chat=({id,name,userId,isManager}:{id:number,name:string,userId:number|null,isManager:boolean})=>
{
	const [messages,setMessages]=useState(new Array<Message>());
	const [input,setInput]=useState("");
	if(id!==0)
	{
		fetch
		(
			`http://192.168.43.37:8080/chat/id=${id}`
		).then
		 (
			 (response)=>
			 {
				 if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
				 return response.json();
			 }
		 ).then
		 (
			 (json:Message[])=>
			 {
				 setMessages(json);
			 }
		 );
	}

	return (
		<Box>
			<Header>
				<Text>{name}</Text>
			</Header>
			<Box>
				{
					messages.map
					        (
						        (message,index)=>
						        {
							        return (
								        <Box key={index} direction={"row"}>
									        <Text>{message.message}</Text>
									        <Text>{JSON.stringify(message.sender_id)}</Text>
									        <Text>[{message.time}]</Text>
								        </Box>
							        )
						        }
					        )
				}
			</Box>
			<Box direction={"row"}>
				<input type={"text"} onChange={(e)=>setInput(e.target.value)}/>
				<Button onClick={()=>
				{
					fetch
					(
						`http://192.168.43.37:8080/addMessage?chatRoom=${id}&sender=${id}&message=${input}`
					);
				}}>SEND</Button>
			</Box>
		</Box>
	);
};