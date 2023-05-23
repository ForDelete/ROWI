import {useState} from "react";

import "../EventCardList/EventCardList.css";//Стили

import EventCard from "../EventCard/EventCard.jsx";//Другие объекты
//import CardsFromJSON from "../../state/CardsFromJSON.json"



function EventCardList() {
    const [count, setCount] = useState(0)//Состояние

    //Логика сбора карточек
    let listOfEventCards = [];
     function getEventCards(){
        //let request = await ky.post('http://localhost:8080/').json();//Для БД*
        return JSON.parse("[{\n" +
            "\n" +
            "  \"name\": \"name1\",\n" +
            "  \"description\":\"description1\",\n" +
            "  \"cost\": \"100\",\n" +
            "  \"datetime\": \"date and time1\",\n" +
            "  \"logo\": \"logo1\"\n" +
            "},\n" +
            "  {\n" +
            "    \"name\": \"name2\",\n" +
            "    \"description\":\"description2\",\n" +
            "    \"cost\": \"200\",\n" +
            "    \"datetime\": \"date and time2\",\n" +
            "    \"logo\": \"logo2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"name3\",\n" +
            "    \"description\":\"description3\",\n" +
            "    \"cost\": \"300\",\n" +
            "    \"datetime\": \"date and time3\",\n" +
            "    \"logo\": \"logo3\"\n" +
            "  }]\n");
    }
    //let z=[]
    listOfEventCards = getEventCards();
    // getEventCards().then(r=>listOfEventCards.push(r))
    //z=getEventCards();
    console.log(listOfEventCards)
    //Вывод
    return (
        <>
            { listOfEventCards &&
                <div>{listOfEventCards?.map(r => {
                    return <EventCard name={r.name} description={r.description} cost={r.cost} datetime={r.datetime} logo={r.logo}/>
                })}</div>
            }
        </>
    )
}
export default EventCardList