import {Box, Button, Grommet} from "grommet";
import React, {useEffect, useState} from "react";
import {Chat} from "./Chat";
import {OwnedChatrooms} from "./OwnedChatrooms";
import {CommonChatrooms} from "./CommonChatrooms";
import {ipAddress} from "./App";
import {DelegateButton} from "./DelegateButton";

export class Chatroom
{
    chatName:string="";
    current_employee:any|null={};
    id:number|null=null;
    speciality:number=0;
    user_id:number|null=0;
}

//const chats=await got.get("http://192.168.43.37:8080/chatrooms");//.json<Chatroom[]>();

export const ChatLobby=({id}:{id:string|null})=>
{
    let [chat,setChat]=useState(new Chatroom());
    let [isManager,setIsManager]=useState(false);

    useEffect(()=>
              {
                  fetch
                  (
                      `http://${ipAddress}:8080/isManager/id=${id}`
                  ).then
                   (
                       (response)=>
                       {
                           if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
                           return response.text();
                       }
                   ).then
                   (
                       (text)=>
                       {
                           setIsManager(text==="true");
                       }
                   );
              },[]);

    return (
        <Grommet full={true}>
            <Box direction={"row"} align={"center"} justify={"between"} margin={"50px"}>
                <Box align={"start"}>
                    <OwnedChatrooms userId={id} isManager={isManager} setActiveChat={setChat}/>
                </Box>
                {
                    chat!==null
                        ?<Chat id={chat.id} name={chat.chatName} userId={id} isManager={isManager}/>
                        :<Box/>
                }
                <Box align={"end"}>
                    {isManager?<CommonChatrooms id={id} setActiveChat={setChat}/>:<Box/>}
                </Box>
                <Box><Button onClick={
                    ()=> {
                        localStorage.removeItem("id");
                        window.location.reload();
                    }
                }>LOG OUT</Button></Box>
                {chat!==null&&isManager?<DelegateButton chatId={chat.id}/>:<Box/>}
            </Box>
        </Grommet>
    );
}