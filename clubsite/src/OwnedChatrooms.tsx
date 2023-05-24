import {Box,Card,CardBody,Text} from "grommet";
import React,{} from "react";
import {Chatroom} from "./ChatLobby";


export function OwnedChatrooms({userId,setActiveChat}:{userId:number|null,setActiveChat:Function})
{
    // const [chatrooms,setChatrooms]=useState(new Array<Chatroom>());
    // useEffect(()=>
    //           {
    //               if(!userId) return;
    //               fetch
    //               (
    //                   `http://192.168.43.37:8080/chatrooms/user=${userId}`
    //               ).then
    //                (
    //                    (response)=>
    //                    {
    //                        if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
    //                        return response.json();
    //                    }
    //                ).then
    //                (
    //                    (json:Chatroom[])=>
    //                    {
    //                        setChatrooms(json);
    //                    }
    //                );
    //           },[userId]);

    let chatrooms=new Array<Chatroom>();

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