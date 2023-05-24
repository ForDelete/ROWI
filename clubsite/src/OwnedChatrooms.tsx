import {Box,Card,CardBody,Text}   from "grommet";
import React,{useEffect,useState} from "react";
import {Chatroom}                 from "./ChatLobby";
import {ipAddress}                from "./App";


export function OwnedChatrooms({userId,setActiveChat}:{userId:number|null,setActiveChat:Function})
{
    const [chatrooms,setChatrooms]=useState(new Array<Chatroom>());
    useEffect(()=>
              {
                  if(!userId) return;
                  fetch
                  (
                      `http://${ipAddress}/chatrooms/user=${userId}`
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
              },[userId]);

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
                                             }
                                         }>
                                     <CardBody><Text>{chatroom.chatName}</Text></CardBody>
                                 </Card>
                         )
            }
        </Box>
    )
}