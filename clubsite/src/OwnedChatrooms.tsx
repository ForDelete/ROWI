import {Box, Card, CardBody, CardFooter, Text} from "grommet";
import React,{useEffect,useState} from "react";
import {Chatroom}                 from "./ChatLobby";
import {ipAddress}                from "./App";


export function OwnedChatrooms({userId,isManager,setActiveChat}:{userId:string|null,isManager:boolean,setActiveChat:Function})
{
    const [chatrooms,setChatrooms]=useState(new Array<Chatroom>());

    fetch
    (
      `http://${ipAddress}:8080/chatrooms/${isManager?"employee":"user"}=${userId}`
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
    );

    return (
        <Box>
            {
                chatrooms.map
                         (
                             (chatroom,index)=>
                                 <Card
                                     key={index}
                                     pad="small"
                                     gap="medium"
                                     background="light-4"
                                     onClick=
                                         {
                                             ()=>
                                             {
                                                 setActiveChat(chatroom);
                                             }
                                         }>
                                     <CardBody><Text>{chatroom.chatName}</Text></CardBody>
                                     {/*<CardFooter><Text>X</Text></CardFooter>*/}
                                 </Card>
                         )
            }
        </Box>
    )
}