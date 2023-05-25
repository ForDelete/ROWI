import {Box,Card,CardBody,Text} from "grommet";
import React,{useEffect,useState} from "react";
import {Chatroom} from "./ChatLobby";
import {ipAddress} from "./App";


export function CommonChatrooms({id,setActiveChat}:{id:string|null,setActiveChat:Function})
{
    const [chatrooms,setChatrooms]=useState(new Array<Chatroom>());

    fetch
    (
      `http://${ipAddress}:8080/freechats/employee=${id}`
    ).then
    (
       (response)=>
       {
           if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
           return response.json();
       }
    ).then
    (
       (json:Chatroom[])=>
       {
           setChatrooms(json);
       }
    )
    return (
        <Box>
        {
            chatrooms.map
                     (
                         chatroom=>
                             <Card
                                 pad="small"
                                 gap="medium"
                                 background="light-4"
                                 onClick=
                                     {
                                         ()=>
                                         {
                                             setActiveChat(chatroom);
                                             fetch
                                             (
                                                 `http://${ipAddress}:8080/setEmployee?chat_id=${chatroom.id}&employee=${id}`
                                             )
                                         }
                                     }>
                                 <CardBody><Text>{chatroom.chatName}</Text></CardBody>
                             </Card>
                     )
        }
    </Box>
    )
}