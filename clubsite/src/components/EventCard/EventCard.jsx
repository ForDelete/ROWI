import {useState} from "react";
import "../EventCard/EventCard.css";

function EventCard({name, description, cost, datetime, logo}) {
    const [count, setCount] = useState(0)


    return (<div className="EventCard">{name + " " + description + " " + cost + " " + datetime + " " + logo}</div>)
}
export default EventCard



// Общий блок{
//     -----Блок контента{
//         ----------Изображение;
//         ----------Название;
//         ----------Время проведения;
//         ----------Цена;
//         ----------}
//     -----Блок с кнопками "подробнее" и "Билеты";
//     -----}