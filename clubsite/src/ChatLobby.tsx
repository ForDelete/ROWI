import {Box,Grommet}              from "grommet";
import React,{useEffect,useState} from "react";
import {Chat}                     from "./Chat";
import {OwnedChatrooms} from "./OwnedChatrooms";
import {CommonChatrooms} from "./CommonChatrooms";

export class Chatroom
{
    chatName:string="";
    current_employee:any|null={};
    id:number=0;
    speciality:number=0;
    user_id:number|null=0;
}

//const chats=await got.get("http://192.168.43.37:8080/chatrooms");//.json<Chatroom[]>();

export const ChatLobby=({id}:{id:number})=>
{
    let [chat,setChat]=useState(new Chatroom());
    let [isManager,setIsManager]=useState(false);


    useEffect(()=>
              {
                  if(!chat.user_id)
                  {
                      setIsManager(false);
                      return;
                  }
                  fetch
                  (
                      `http://192.168.43.37:8080/isManager/id=${chat.user_id}`
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
              },[chat.user_id]);

    return (
        <Grommet full={true}>
            <Box direction={"row"} align={"center"} justify={"between"} margin={"50px"}>
                <Box align={"start"}>
                    <OwnedChatrooms userId={chat.user_id} setActiveChat={setChat}/>
                </Box>
                <Chat id={chat.id} userId={chat.user_id} isManager={isManager}/>
                <Box align={"end"}>
                    {isManager?<CommonChatrooms/>:<Box/>}
                </Box>
            </Box>
        </Grommet>
    );
}

export const onMore=()=><ChatLobby id={0}/>;

onMore.storyName='onMore';