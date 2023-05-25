import {Box, Button, Form, FormField, Select} from "grommet";
import {useState} from "react";
import {ipAddress} from "./App";
import {Chatroom} from "./ChatLobby";

export const DelegateButton=({chatId}:{chatId:number|null})=>
{
    const [speciality,setSpeciality]=useState("");
    const [complexity,setComplexity]=useState("");

    function delegateToManager(chatId:number|null,speciality: string,complexity:string)
    {
        fetch
        (
            `http://${ipAddress}:8080/setSpeciality?chat_id=${chatId}&speciality=${speciality}&laci=${complexity}`
        ).then
        (
            (response)=>
            {
                if(!response.ok) throw new Error(`HTTP error: ${response.status}`);
            }
        ).then
        (
            ()=>
            {
                localStorage.removeItem("chat");
            }
        )
    }

    return (
        <Box>
            <Form>
                <FormField label="Отдел" htmlFor="select">
                    <Select
                        id="speciality"
                        placeholder="Укажите отдел"
                        options={[0,1,2,3]}
                        value={speciality}
                        onChange={({option})=>setSpeciality(option)}
                    />
                </FormField>
                <FormField label="Сложность" htmlFor="complexity">
                    <Select
                        id="complexity"
                        placeholder="Укажите сложность"
                        options={[0,1,2,3]}
                        value={complexity}
                        onChange={({option})=>setComplexity(option)}
                    />
                </FormField>
                <Button label={"Отправить"} onClick={()=>delegateToManager(chatId,speciality,complexity)}></Button>
            </Form>
        </Box>
    );
}