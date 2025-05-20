import React, {useState, useEffect} from 'react'
import axios from 'axios';
import './Clock.css'

export default function Clock({ 
  isLoggedIn, 
  onLogout, 
  onLoginClick, 
  alarm, 
  addAlarm, 
  checkAlarms, 
  alarmPopup, 
  closePopup  }) {
    const cities = [
        { name: "New York", timezone: "America/New_York" },
        { name: "London", timezone: "Europe/London" },
        { name: "Paris", timezone: "Europe/Paris" },
        { name: "Seoul", timezone: "Asia/Seoul" },
        { name: "Sydney", timezone: "Australia/Sydney" },
        { name: "Dubai", timezone: "Asia/Dubai" },
        { name: "Istanbul", timezone: "Europe/Istanbul" },
        { name: "Los Angeles", timezone: "America/Los_Angeles" }
    ];

    const [selectedCity, setSelectedCity] = useState(cities[0].timezone);
    const [datetime, setDatetime] = useState(null);
    const [alarmTime, setAlarmTime] = useState('');

    useEffect(() => {
        const updateClock = () => {
            const options = {
                timeZone: selectedCity,
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit',
                hour12: false
            };
            const formatter = new Intl.DateTimeFormat([], options);
            const dateTimeString = formatter.format(new Date());
            setDatetime(dateTimeString);
        };

        updateClock();
        const intervalId = setInterval(updateClock, 1000); // 매초 업데이트

        return () => clearInterval(intervalId);
    }, [selectedCity]);

    const handleCityChange = (event) => {
        setSelectedCity(event.target.value);
    };

    const handleAddAlarm = async (e) => {
        e.preventDefault();

        if(alarmTime) {
            try{
                const response = await axios.post("http://localhost:8080/api/v1/")        
            }catch(e){

            }
            addAlarm(alarmTime);
            setAlarmTime('');
        }else{
            alert('Please set both time and message');
        }
    };

    const handleLogout = () => {
        onLogout();
    };

    return (
        <div className="clock-container">
            <div className="header">
                {isLoggedIn ? (
                    <button className="logout-button" onClick={onLogout}>
                        Logout
                    </button>
                     ) : (
                    <button className="login-button" onClick={onLoginClick}>
                        Login
                    </button>
                )}
            </div>
            <h2>Select a City:</h2>
            <select className="city-select" value={selectedCity} onChange={handleCityChange}>
                {cities.map((city) => (
                    <option key={city.timezone} value={city.timezone}>
                        {city.name}
                    </option>
                ))}
            </select>
            <h1>{datetime || 'Loading...'}</h1>

                {isLoggedIn && (
                    <div className="alarm-container">
                    <h2>Set an Alarm</h2>
                    <input
                        type="time"
                        value={alarmTime}
                        onChange={(e) => setAlarmTime(e.target.value)}
                    />
                    <button onClick={handleAddAlarm}>Add Alarm</button>

                    <ul>
                        {alarm.map((a, index) => (
                        <li key={index}>
                            {a.time}
                        </li>
                        ))}
                    </ul>
                    </div>
                )}

                {alarmPopup && (
                    <div className="alarm-popup">
                        <p>{alarmPopup}</p>
                        <button onClick={closePopup}>Close</button>
                    </div>
                )}
        </div>
    );
}
