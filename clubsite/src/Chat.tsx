import React, {useEffect, useState} from "react";
import {Box, Button, Header, Text, TextInput} from "grommet";
import {Chatroom} from "./ChatLobby";
import {ipAddress} from "./App";

interface Message {
    chatRoom: Chatroom,
    id: number,
    message: string,
    sender_id: any;
    time: string
}

export const Chat = ({id, name, userId, isManager}: {
    id: number | null,
    name: string,
    userId: string | null,
    isManager: boolean
}) => {
    const [messages, setMessages] = useState(new Array<Message>());
    const [input, setInput] = useState("");
    const [tname, setTName] = useState("");
    if (id !== null) {
        fetch
        (
            `http://${ipAddress}:8080/chat/id=${id}`
        ).then
        (
            (response) => {
                if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
                return response.json();
            }
        ).then
        (
            (json: Message[]) => {
                setMessages(json);
            }
        );
    }

    return (
        <Box>
            <Text style={{paddingBottom:"10px"}}>{name}</Text>
            <Box>
                {
                    messages.map
                    (
                        (message, index) => {
                            return (
                                <Box key={index} direction={"row"}>
                                    [<Text style={{fontWeight:"bold"}}>{message.sender_id.name}</Text>]:
                                    <Text style={{paddingLeft: "10px",paddingRight:"10px"}}>{message.message}</Text>
                                    <Text style={{fontSize:"15px",color:"green"}}>{message.time}</Text>
                                </Box>
                            )
                        }
                    )
                }
            </Box>
            {
                id == null && !isManager
                    ? <Box>
                        <Text>Название чата</Text>
                        <input type={"text"} onChange={(e) => setTName(e.target.value)}/>
                    </Box>
                    : <Box></Box>
            }
            <br></br>
            <Box direction={"row"}>
                {id!==null?<input type={"text"} onChange={(e) => setInput(e.target.value)}/>:<Box/>}
                <Button onClick={() => {
                    if (id !== null) {
                        fetch
                        (
                            `http://${ipAddress}:8080/addMessage?chat=${id}&sender=${userId}&message=${input}`
                        );
                    } else {
                        fetch
                        (
                            `http://${ipAddress}:8080/addChatroom?chatName=${tname}&user_id=${userId}`
                        );
                    }
                }
                }>SEND</Button>
            </Box>
        </Box>
    );
};