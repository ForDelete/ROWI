import {Box,Card,CardBody,Text} from "grommet";
import React,{useEffect,useState} from "react";
import {Chatroom} from "./ChatLobby";


export function CommonChatrooms()
{
    const [chatrooms,setChatrooms]=useState(new Array<Chatroom>());
    useEffect(()=>
              {
                  fetch
                  (
                      `http://192.168.43.37:8080/freechats`
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
              },[]);
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
                                             localStorage.setItem("chat",JSON.stringify(chatroom));
                                         }
                                     }>
                                 <CardBody><Text>{chatroom.chatName}</Text></CardBody>
                             </Card>
                     )
        }
    </Box>
    )
}